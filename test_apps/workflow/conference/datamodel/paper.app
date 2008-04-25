module datamodel/paper
  
section data model conference paper

  extend entity User {
    authoredPapers  -> Set<Paper>
    authoredReviews -> Set<Review>
  }

  entity Paper {
    conference -> Conference (inverse=Conference.papers)
    title          :: String (name)
    abstract       :: Text
    authors        -> Set<User> (inverse=User.authoredPapers)
    reviewers      -> Set<User> (inverse=User.authoredReviews)
    reviews        <> Set<Review>
    classification -> PaperClassification
    final          :: Bool
  }

  enum PaperClassification {
    acceptedClassification("Accepted"),
    rejectedClassification("Rejected")
  }

  enum Classification {
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
    marginalRelevant("Marginally relevant")
  }
    
  entity Review {
    paper             -> Paper (inverse=Paper.reviews)
    author            -> User (inverse=User.authoredReviews)
    classification    -> Classification
    expertise         -> Expertise
    relevance         -> Relevance
    committeeComments :: Text
    summary           :: Text
    evaluation        :: Text
    completed         :: Bool
  }