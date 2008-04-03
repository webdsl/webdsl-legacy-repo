module decideacceptance

section data model
  
  entity AcceptPapersTask : ConferenceTask {
    name       :: String := "Decide what papers to accept for " + this.conference.name
  }

section business logic

  globals {
    function checkReadyToDecideAcceptance(c : Conference) : Conference {
      for(p : Paper in c.papersList) {
        for(r : Review in p.reviewsList where !r.completed) {
          return c; // Unfinished review
        }
      }
      // Ready to move on to decide on acceptance
      return initDecideAcceptance(c);
    }

    function initDecideAcceptance(c : Conference) : Conference {
      c.stage = decideOnAcceptance;
      // Clever algorithm to decide on default accept/rejects
      for(p : Paper in c.papersList) {
        if(calculateAccepts(p) > (p.reviews.length / 2)) {
          p.classification := acceptedClassification;
        } else {
          p.classification := rejectedClassification;
        }
      }
      // Assign acceptance task to all chairs
      var t : AcceptPapersTask := AcceptPapersTask { conference := c };
      t.persist();
      for(u : User in c.chairsList) {
        assignTask(t, u);
      }
      return c;
    }

  }

section pages

  access control rules {
    rules page acceptPapersTask(t : AcceptPapersTask) {
      t in securityContext.principal.tasks && !t.completed
    }
  }
  define page acceptPapersTask(t : AcceptPapersTask) {
    title{"Decide on acceptance of papers"}
    main()
    define contextSidebar() {
      conferenceSidebar(t.conference)
    }
    define body() {
      header{"Decide on acceptance of papers"}
      form {
        table {
          for(p : Paper in t.conference.papersList) {
            row { output(p) output(calculateAccepts(p)) "accepts" input(p.classification) }
          }
        }
        action("Save", save())
        action("Save and notify", saveAndNotify())

        action save() {
          return message("The new classification of papers has been saved.");
        }
        action saveAndNotify() {
          t.completed := true;
          notifyAuthors(t.conference);
          return message("The classifications were saved and all authors have been notified.");
        }
      }
    }
  }
