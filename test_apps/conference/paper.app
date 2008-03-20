module paper

section pages

access control rules {
  rules page submitPaper(c : Conference) {
    c.stage = acceptingPapers && securityContext.loggedIn
  }
}
define page submitPaper(c : Conference) {
  title{"Submit paper"}
  main()
  define contextSidebar() {
    conferenceSidebar(c)
  }
  define body() {
    header{"Submit paper to " output(c.name) }
    form {
      var paper : Paper := Paper { }
      table {
        row { "Title: " input(paper.title) }
        row { "Abstract: " input(paper.abstract) }
        row { "Authors: " input(paper.authors) }
      }
      action("Submit", submitPaper())

      action submitPaper() {
        paper.conference := c;
        paper.persist();
        for(author : User in paper.authorsList) {
          author.roles.add(getConferenceRole(c, authorRole));
        }
        return message("Your paper has been submitted succesfully.");
      }
    }
  }
}

access control rules {
  rules template submittedPapers(c : Conference) {
    securityContext.principal.isAdmin || securityContext.principal in c.chairs || securityContext.principal in c.pc
  }

  rules template transitionToReviewStage(c : Conference) {
    securityContext.principal.isAdmin || securityContext.principal in c.chairs

  }
}

define acceptingPapersView(c : Conference) {
  section {
    header {"Program comittee"}
    list {
      for(u : User in c.pcList) {
        listitem { output(u) }
      }
    }
  }
  submittedPapers(c)
  transitionToReviewStage(c)
}

define submittedPapers(c : Conference) {
  section {
    header {"Submitted papers"}
    "Current submitted papers:"
    list {
      for(p : Paper in c.papersList) {
        listitem { output(p) }
      }
    }
  }
}

define transitionToReviewStage(c : Conference) {
  section {
    header{"Deadline"}
    form {
      action("Submission deadline passed", deadline()) 
      action deadline() {
        c.stage := bidOnPapers;
        createBids(c);
        return conference(c);
      }
    }
  }
}
