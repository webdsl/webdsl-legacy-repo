module submitpapers

section initialization

  entity ConferenceDeadlinePassedTask : ConferenceTask {
    name       :: String := "Mark submission deadline as passed for " + this.conference.name
  }
  globals {
    function initAcceptingPapers(c : Conference)  : Conference { 
      c.stage := acceptingPapers;
      // For now we're going to assign a submission deadline task to the conference chairs
      var t : ConferenceDeadlinePassedTask := ConferenceDeadlinePassedTask { conference := c };
      for(u : User in c.chairsList) {
        assignTask(t, u);
      }
      return c;
    }
  }

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
    rules page conferenceDeadlinePassedTask(t : ConferenceDeadlinePassedTask) {
      t in securityContext.principal.tasks
    }
  }

  define page conferenceDeadlinePassedTask(t : ConferenceDeadlinePassedTask) {
    title{"Conference deadline passed"}
    main()
    define contextSidebar() {
      conferenceSidebar(t.conference)
    }
    define body() {
      header{"Deadline"}
      form {
        action("Submission deadline passed", deadline()) 
        action deadline() {
          t.completed := true;
          initBids(t.conference);
          return conference(t.conference);
        }
      }
    }
  }
