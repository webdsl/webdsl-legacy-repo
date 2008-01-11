module data

section conference manager data

  entity Conference
  {
    name :: String (name)
    topic :: String
    programChair -> User
    programCommittee -> Set<User>
    reviewCommittee -> Set<User>
    submissionsOpen -> Bool
    submittedPapers -> Set<Paper>
    acceptedPapers -> Set<Paper>
    rejectedPapers -> Set<Paper>
  }

  entity Paper
  {
    title :: String (name)
    authors -> Set<User>
    conference -> Conference(inverse=Conference.submittedPapers)
    assignedReviewers -> Set<User>
    reviews -> Set<Review>
    assignedPC -> Set<User>
    recommendations -> Set<Recommendation>
  }
  
  entity Review
  {
    comments :: String
    author -> User
    paper -> Paper
  }
  
  entity Recommendation
  {
    comments :: String
    author -> User
    paper -> Paper
  }
  
  entity User
  {
    name :: String (name)
    email :: Email
    papers -> Set<Paper> (inverse=Paper.authors)
    organization -> Organization
  }
  
  entity Organization
  {
    name :: String (name)
  }

  
//  define page AllReview(r:Review){main() define body() {} }
//  define page AllRecommendation(r:Recommendation){main() define body() {} }
  