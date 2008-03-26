module reviewassignment

section data model

entity DecideReviewAssignmentTask : Task {
  conference -> Conference
  name       :: String := "Decide on review assignment for " + conference.name
}

section pages

globals {
  function startReviewAssignment(c : Conference) : Conference {
    var r : Review;
    for(p : Paper in c.papers) {
      // Create 3 reviews for each
      r := Review { paper := p };
      r.persist();
      r := Review { paper := p };
      r.persist();
      r := Review { paper := p };
      r.persist();
    }
    var t : DecideReviewAssignmentTask := DecideReviewAssignmentTask { conference := c };
    for(u : User in c.pc) {
      assignTask(t, u);
    }
    return c;
  }
}


access control rules {
  rules page decideReviewAssignmentTask(t : DecideReviewAssignmentTask) {
    t in securityContext.principal.tasks
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
        return message("Assignments saved");
      }
    }
  }
}
