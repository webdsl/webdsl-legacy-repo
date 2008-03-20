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

globals {
  function createBids(c : Conference) : Conference {
    for(u : User in c.pc) {
      for(p : Paper in c.papers) {
        var b : Bid := Bid { paper := p category := dontCareBid };
        b.persist();
        u.bids.add(b);
      }
      // Send bidding email
      var bidTask : BidTask := BidTask { assignee := u conference := c };
      bidTask.persist();
    }
    return c;
  }
}

enum BidCategory {
  highBid("I really want to review this paper"),
  lowBid("I can review this paper"),
  conflictBid("I have a conlfict with this paper"),
  blockBid("I definitely don't want to review this paper"),
  dontCareBid("Don't care")
}

section pages

define biddingView(c : Conference) {
  navigate(tasks(securityContext.principal)) { "See if you have some tasks left to perform" }
}

access control rules {
  rules page bidTask(b : BidTask) {
    securityContext.principal = b.assignee
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
          return message("Your biddings were saved. Thank you.");
        }
      }
    }
  }
}
