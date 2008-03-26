module bid

description {
  Pages and stuff for the bidding on papers.
}

section data model

  extend entity User {
    bids -> Set<Bid>
  }

  entity Bid {
    paper -> Paper
    category -> BidCategory
  }

  entity BidTask : Task {
    conference -> Conference
    name :: String := "Bid on papers in " + conference.name
  }

  enum BidCategory {
    highBid("I really want to review this paper"),
    lowBid("I can review this paper"),
    conflictBid("I have a conflict with this paper"),
    blockBid("I definitely don't want to review this paper"),
    dontCareBid("Don't care")
  }

section business logic

  globals {
    function initBids(c : Conference) : Conference {
      c.stage := bidOnPapers;
      for(u : User in c.pc) {
        for(p : Paper in c.papers) {
          var b : Bid := Bid { paper := p category := dontCareBid };
          b.persist();
          u.bids.add(b);
        }
        var bidTask : BidTask := BidTask { conference := c };
        bidTask.persist();
        assignTask(bidTask, u);
      }
      return c;
    }
    
    function checkForAllBiddingTasks(c : Conference) : Conference {
      for(u : User in c.pc) {
        for(t : Task in u.tasks where (!t.completed) && (t is a BidTask)) {
          return c; // There are still uncompleted BidTasks in the PC
        }
      }
      // Apparently there are no uncompleted bid tasks in the PC
      c.stage := decideReviewAssignment;
      startReviewAssignment(c);
      return c;
    }
  }

section pages

  define biddingView(c : Conference) {
    navigate(tasks(securityContext.principal)) { "See if you have some tasks left to perform" }
  }

  access control rules {
    rules page bidTask(b : BidTask) {
      securityContext.principal in b.assignees
    }
  }

  define page bidTask(b : BidTask) {
    title { "Bid on papers" }
    main()
    define contextSidebar() {
      conferenceSidebar(b.conference)
    }
    define body() {
      section {
        header{"Bid on papers in " output(b.conference) }
        form {
          table {
            for(b : Bid in securityContext.principal.bidsList) {
              row { output(b.paper) input(b.category) }
            }
          }
          action("Save biddings", saveBiddings())
          action saveBiddings() {
            b.completed := true;
            checkForAllBiddingTasks(b.conference);
            return message("Your biddings were saved. Thank you.");
          }
        }
      }
    }
  }
