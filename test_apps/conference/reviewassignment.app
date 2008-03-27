module reviewassignment

section data model

  entity DecideReviewAssignmentTask : Task {
    conference -> Conference
    name       :: String := "Decide on review assignment for " + conference.name
  }

section business logic

  globals {
    function startReviewAssignment(c : Conference) : Conference {
      var r : Review;
      for(p : Paper in c.papers) {
        // Create 3 reviews for each
        r := Review { paper := p, completed := false };
        r.persist();
        r := Review { paper := p, completed := false };
        r.persist();
        r := Review { paper := p, completed := false };
        r.persist();
      }
      var t : DecideReviewAssignmentTask := DecideReviewAssignmentTask { conference := c };
      for(u : User in c.chairsList) {
        assignTask(t, u);
      }
      return c;
    }
  }

section pages

  access control rules {
    rules page decideReviewAssignmentTask(t : DecideReviewAssignmentTask) {
      t in securityContext.principal.tasks && !t.completed
    }
  }
  define page decideReviewAssignmentTask(t : DecideReviewAssignmentTask) {
    title{"Decide on review assignment"}
    main()
    define contextSidebar() {
      conferenceSidebar(t.conference)
    }
    define body() {
      header{"PC Member preferences"}
      for(u : User in t.conference.pcList) {
        section {
          header{output(u)}
          list {
            for(b : Bid in u.bidsList) {
              listitem {
                output(b.paper) " - " output(b.category)
              }
            }
          }
        }
      }
      header{"Assign"}
      form {
        table {
          for(p : Paper in t.conference.papersList) {
            row { 
              output(p)
              container {
                for(r : Review in p.reviewsList) {
                  input(r.author)
                }
              }
            }
          }
        }
        action("Assign", assign())
        action("Assign and let the reviewing begin!", assignAndReview())
        action assign() {
          return message("Assignments saved");
        }
        action assignAndReview() {
          t.completed := true;
          t.conference.stage := reviewing;
          initReviews(t.conference);
          return message("Assignments saved");
        }
      }
    }
  }
