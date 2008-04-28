module workflows/bid

section data model

  entity Review {
    paper             -> Paper (inverse=Paper.reviews)
    author            -> User (inverse=User.authoredReviews)
    acceptance        -> AcceptanceClassification
    expertise         -> Expertise
    relevance         -> Relevance
    committeeComments :: Text
    summary           :: Text
    evaluation        :: Text
  }
  
  extend entity Paper {
    reviews        <> Set<Review>
    reviewers      -> Set<User> (inverse=User.authoredReviews)
  }
  
  extend entity User {
    authoredReviews -> Set<Paper>
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
    who { securityContext.principal = review.author }
    when { !review.finalizeReview.performed }
    view {
      derive operationPage from review
    }
  }
  
  operation finalizeReview(review : Review) {
    who { securityContext.principal = review.author }
    when { !review.finalizeReview.performed }
  }