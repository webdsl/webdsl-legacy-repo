module workflows/bid

section data model 
  entity Bid {
    reviewer -> User (inverse=User.bids)
    paper -> Paper
    category -> BidCategory
    name :: String := reviewer.name + " on " + paper.title
  }
  
  extend entity User {
    bids -> Set<Bid>
  }

  extend entity Conference {
    bids -> Set<Bid>
  }

  enum BidCategory {
    highBid("I really want to review this paper"),
    lowBid("I can review this paper"),
    conflictBid("I have a conflict with this paper"),
    blockBid("I definitely don't want to review this paper"),
    dontCareBid("Don't care")
  }

operations bid

  workflow bidWorkflow(bid : Bid) {
    done { bid.doBid.performed }
  }
  
  operation doBid(bid : Bid) {
    who { securityContext.principal = bid.reviewer }
    when { !bid.paper.conference.conferenceWorkflow.performed && bid.bidWorkflow.started && !bid.doBid.performed }
    view {
      // todo: paper weergeven
      derive operationPage from bid for (category)
    }
  }
