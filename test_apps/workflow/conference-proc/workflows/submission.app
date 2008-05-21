module workflows/submission

section paper submission

  // submitting a paper requires submitting an abstract first,
  // before the abstractDeadline

  procedure submitAbstract(c : Conference) {
    who { securityContext.loggedIn }
    when { c.abstractDeadline.before(now()) }
    view {
      var p : Paper := Paper{ conference := c };
      derive procedurePage from p 
         for (title, authors, abstract)
    }
    do {
      c.papers.add(p);
    }
  }
  
  procedure submitPaper(p : Paper) {
    who  { securityContext.principal in p.authors }
    when { p.conference.paperDeadline.before(now()) }
    view {
      derive procedurePage from p 
         for (view(title), view(authors), abstract, content)
    }
    do { }
  }
  
  // submitPaper can be applied any number of times (at least one)
  // before the deadline has passed

section pages

  access control rules {
    rules page paper(p : Paper) {
      securityContext.principal in p.conference.pc.members
      || securityContext.principal in p.conference.pc.chairs
    }
  }
  
section presentation
  
  define page paper(p : Paper) {
    title {output(p.title)}
    main()
    define contextSidebar() {
      conferenceSidebar(p.conference)
    }
    define body() {
      header{output(p.title)}
      table {
        derive viewRows from p for (title, authors, abstract, content)
      }
      paperReviews(p)
    }
  }

section reviews

  // maybe define a separate page for reviews

  access control rules {
    rules template paperReviews(p : Paper) {
      securityContext.principal in p.conference.pc.chairs
      // and reviewers of the paper who have submitted their review
    }
  }
  
section show reviews

  define paperReviews(p : Paper) {
    section {
      header{"Reviews"}
      for(r : Review in p.reviewsList) { // where r.completed) {
        section {
          header{"Review"}
          table {
            row { "Classification:" output(r.acceptance) }
            row { "Expertise:"      output(r.expertise) }
            row { "Relevance:"      output(r.relevance) }
            row { "Summary:"        output(r.summary) }
            row { "PC Comments:"    output(r.committeeComments) }
            row { "Evaluation:"     output(r.evaluation) }
          }
        }
      }
    }
  }

