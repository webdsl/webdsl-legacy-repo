module paper

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
