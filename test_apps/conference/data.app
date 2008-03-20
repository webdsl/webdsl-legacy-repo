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
  activeRoles     -> Set<ConferenceRole>
  tasks           -> Set<Task>
  registered      :: Bool
}

enum UserRole {
  chairRole("Chair"), 
  pcRole("PC Member"), 
  authorRole("Author"), 
  reviewerRole("User") 
}

entity ConferenceRole {
  conference -> Conference
  role       -> UserRole
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
}

entity Task {
  assignee -> User (inverse=User.tasks)
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
  title      :: String (name)
  abstract   :: Text
  authors    -> Set<User> (inverse=User.authoredPapers)
  reviewers  -> Set<User> (inverse=User.authoredReviews)
  reviews    <> Set<Review>
}

section reviews

enum ConferenceStage {
  assemblePC("Assemling the PC"),
  acceptingPapers("Accepting papers"),
  bidOnPapers("Bidding on papers"),
  reviewing("Reviewing"),
  decideOnAcceptance("Decide on acceptance of papers"),
  resultsKnown("Results are known")
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
}

