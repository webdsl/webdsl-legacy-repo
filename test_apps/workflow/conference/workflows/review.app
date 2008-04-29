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

operations review

  workflow reviewWorkflow(review : Review) {
    init() {

    }
    done { review.finalizeReview.performed }
  }

  operation doReview(review : Review) {
    who { securityContext.principal = review.reviewer }
    when { !review.paper.conference.conferenceWorkflow.done && review.reviewWorkflow.started && !review.finalizeReview.performed }
    view {
      derive operationPage from review
    }
  }
  
  operation finalizeReview(review : Review) {
    who { securityContext.principal = review.reviewer }
    when { !review.paper.conference.conferenceWorkflow.done && review.reviewWorkflow.started && review.doReview.performed && !review.finalizeReview.performed }
  }