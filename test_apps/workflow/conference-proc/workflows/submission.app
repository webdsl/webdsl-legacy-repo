module submission

section pages

  access control rules {
    rules page paper(p : Paper) {
      securityContext.principal in p.conference.pc || securityContext.principal in p.conference.chairs
    }
  }
  
  define page paper(p : Paper) {
    title {output(p.title)}
    main()
    define contextSidebar() {
      conferenceSidebar(p.conference)
    }
    define body() {
      header{output(p.title)}
      table {
        row { "Authors:" output(p.authors) }

      }
      paperReviews(p)
    }
  }

  access control rules {
    rules template paperReviews(p : Paper) {
      securityContext.principal in p.conference.chairs
    }
  }
  
  define paperReviews(p : Paper) {
    section {
      header{"Reviews"}
      for(r : Review in p.reviewsList where r.completed) {
        section {
          header{"Review"}
          table {
            row { "Classification:" output(r.classification) }
            row { "Expertise:" output(r.expertise) }
            row { "Relevance:" output(r.relevance) }
            row { "Summary:" output(r.summary) }
            row { "PC Comments:" output(r.committeeComments) }
            row { "Evaluation:" output(r.evaluation) }
          }
        }
      }
    }
  }

section paper submission

  procedure submitAbstract(c : Conference) {
    when { c.abstractDeadline < now() }
  }
  
  procedure submitPaper(p : Paper) {
    when { c.paperDeadline < now()
    
  c.finalizePc.performed && !c.stopAcceptingPapers.performed }
    view {
      var paper : Paper := Paper{}
      title{"Submit paper"}
      main()
      define contextSidebar() {
        conferenceProcedures(c)
      }
      define body () {
        section(){
          header{"Submit paper"}
          form {
            table {
              derive editRows from paper for (title, abstract, authors)
            }
            action("Save", do()){}
            action("Cancel", cancel()){}
          }
        }
        action cancel ( )
        {
          return home();
        }
      }
    }
    do {
      paper.conference := c;
      paper.save();
    }
  }