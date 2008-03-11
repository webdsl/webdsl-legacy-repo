module data

section access control data model

entity User {
  username        :: String (id)
  name            :: String
  email           :: Email
  password        :: Secret
  authoredPapers  -> Set<Paper>
  authoredReviews -> Set<Paper>
  isAdmin         :: Bool
  roles           -> Set<ConferenceRole>
  activeRoles     -> Set<ConferenceRole>
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

entity PCInvite {
  name :: String
  email :: Email
  conference -> Conference
  accepted :: Bool
  reason :: Text
}

section conference data model

entity Conference {
  name               :: String
  submissionDeadline :: DateTime
  chairs             -> Set<User>
  pc                 -> Set<User>
  papers             -> Set<Paper>
  stage              -> ConferenceStage
  invites            -> Set<PCInvite>
}

entity Paper {
  conference -> Conference (inverse=Conference.papers)
  title      :: String (name)
  abstract   :: Text
  authors    -> Set<User> (inverse=User.authoredPapers)
  reviewers  -> Set<User>
  reviews    <> Set<Review>
}

section reviews

enum ConferenceStage {
  assemblePC("Assemling the PC"),
  acceptingPapers("Accepting papers"),
  reviewing("Reviewing")
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
  classification    -> Classification
  expertise         -> Expertise
  relevance         -> Relevance
  committeeComments :: Text
  summary           :: Text
  evaluation        :: Text
}

