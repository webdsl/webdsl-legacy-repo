module workflows/bid

section data model 

  entity Bid {
    reviewer -> User (inverse=User.bids)
    paper    -> Paper
    category -> BidCategory
    name     :: String := reviewer.name + " on " + paper.title
  }
  
  extend entity User {
    bids -> Set<Bid>
  }

  enum BidCategory {
    highBid("I really want to review this paper"),
    lowBid("I can review this paper"),
    conflictBid("I have a conflict with this paper"),
    blockBid("I definitely don't want to review this paper"),
    dontCareBid("Don't care")
  }

section bid

  auto procedure bidding(p : Paper) {
    do {
      var c : Conference := p.conference;
      for ( u : User in c.pc.membersList) {
        var bid : Bid := Bid {
          reviewer := u
          paper    := p
          category := dontCareBid
        };
        u.bids.add(bid);
        bid.submitBid.enable()
      }
    }
  }

  procedure submitBid(bid : Bid) {
    who { securityContext.principal = bid.reviewer }
    when { 
      bid.paper.conference.abstractDeadline.before(now())
    }
    view {
      // todo: paper weergeven
      derive procedurePage from bid for (category)
    }
  }
  
  procedure startBidding(c : Conference) {
    who { securityContext.principal in c.chairs }
    when { c.stopAcceptingPapers.performed && !c.startBidding.performed }
    do {
      for (p: Paper in c.papersList) {
        for (reviewer : User in c.pcMembersList) {
          var bid : Bid := Bid{
            paper    := p
            reviewer := reviewer
          };
          bid.bidWorkflow.start();
          c.bids.add(bid);
        }
        var review1 : Review := Review{
          paper := p
        };
        var review2 : Review := Review{
          paper := p
        };
        p.reviews.add(review1);
        p.reviews.add(review2);
      }
    }
  }