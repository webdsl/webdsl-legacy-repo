module workflows/bid

section data model

  entity Review {
    paper             -> Paper (inverse=Paper.reviews)
    reviewer          -> User (inverse=User.authoredReviews)
    acceptance        -> AcceptanceClassification
    expertise         -> Expertise
    relevance         -> Relevance
    committeeComments :: Text
    summary           :: Text
    evaluation        :: Text
  }
  
  extend entity Paper {
    reviews        <> Set<Review>
  }
  
  extend entity User {
    authoredReviews -> Set<Review>
  }
  
  enum AcceptanceClassification {
    championClass("Champion"),
    acceptClass("Accept"), 
    rejectClass("Reject"), 
    seriousProblemsClass("Reject, has serious problems")
  }

  enum Expertise {
    expertExpertise("Expert"),
    knowledgeableExpertise("Knowledgeable"),
    noExpertExpertise("No expert")
  }

  enum Relevance {
    significantlyRelevant("Significantly relevant"),
    relevant("Relevant"),
    marginallyRelevant("Marginally relevant")
  }

/*
section review

  procedure review(r : Review) {
    who { 
      principal = r.reviewer 
    }
    process {
      submitReview(r);
      (viewReviews(r.paper) |OR| reviseReview(r) |OR| commentReviews(r.paper))*;
      finalizeReview(r)
    }
  }

  procedure submitReview(review : Review) {
    who { 
      securityContext.principal = review.reviewer 
    }
    view {
      derive procedurePage from review 
         for (acceptance, expertise, relevance, committeeComments, summary, evaluation)
    }
  }
  
  procedure viewReviews(review : Review) {
    who { securityContext.principal = review.reviewer }
    view {
      for (r : Review in review.papers.reviews ) {
        derive viewPage from r
           for (acceptance, expertise, relevance, committeeComments, summary, evaluation)
      }
    }
  }
  
  procedure reviseReview(review : Review) {
    who { 
      securityContext.principal = review.reviewer 
    }
    view {
      derive procedurePage from review 
         for (acceptance, expertise, relevance, committeeComments, summary, evaluation)
    }
  }
  
  procedure finalizeReview(review : Review) {
    who { securityContext.principal = review.reviewer }
  }
*/