module data

section access control data model

  entity User {
    username        :: String
    name            :: String
    email           :: Email(id)
    password        :: Secret
    authoredPapers  -> Set<Paper>
    authoredReviews -> Set<Review>
    isAdmin         :: Bool
    roles           -> Set<ConferenceRole>
    tasks           -> Set<Task> (inverse=Task.assignees)
    registered      :: Bool
  }

  enum UserRole {
    chairRole("Chair"), 
    pcRole("PC Member"), 
    authorRole("Author")
  }

  entity ConferenceRole {
    conference -> Conference
    role       -> UserRole
  }

  extend session securityContext {
    activeRoles -> Set<ConferenceRole>
  }

  globals {
    function getConferenceRole(c : Conference, r : UserRole) : ConferenceRole {
      var cr : ConferenceRole;
      var conferenceRoles : List<ConferenceRole> :=
            select cr from ConferenceRole as cr
            where (cr._conference = ~c) and (cr._role = ~r);
      if(conferenceRoles.length = 0) {
        cr := ConferenceRole { conference := c role := r };
        cr.persist();
      } else {
        for(crole : ConferenceRole in conferenceRoles) { // HACK!
          cr := crole;
        }
      }
      return cr;
    }

    function assignTask(t : Task, u : User) : Task {
      u.tasks.add(t);
      // Send email about it
      return t;
    }

    function calculateAccepts(p : Paper) : Int {
      var accepts : Int := 0;
      for(r : Review in p.reviewsList where r.classification = championClass || r.classification = acceptClass) {
        accepts := accepts + 1;
      }
      return accepts;
    }
  }

  entity Task {
    assignees -> Set<User> 
    completed :: Bool  
  }

section conference data model

  entity Conference {
    name               :: String
    submissionDeadline :: DateTime
    chairs             -> Set<User>
    pc                 -> Set<User>
    papers             -> Set<Paper>
    stage              -> ConferenceStage
    pcInvites          -> Set<PcInvite>
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

section reviews

  enum ConferenceStage {
    assemblePC("Assemling the PC"),
    acceptingPapers("Accepting papers"),
    bidOnPapers("Bidding on papers"),
    decideReviewAssignment("Decide on who will review which papers"),
    reviewing("Reviewing"),
    decideOnAcceptance("Decide on acceptance of papers"),
    submitFinalPapers("Results known, submit final papers"),
    conferenceCompleted("Ready for the actual conference")
  }

  enum Classification {
    championClass("Champion"),
    acceptClass("Accept"), 
    rejectClass("Reject"), 
    seriousProblemsClass("Reject, has serious problems")
  }

  enum PaperClassification {
    acceptedClassification("Accepted"),
    rejectedClassification("Rejected")
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

