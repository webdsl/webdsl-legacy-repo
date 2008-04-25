module datamodel/accesscontrol

section data model access control

  entity User {
    username        :: String
    name            :: String
    email           :: Email (id)
    password        :: Secret
    isAdmin         :: Bool
  }

/*  
  enum UserRole {
    chairRole("Chair"), 
    pcRole("PC Member"), 
    authorRole("Author"),
    reviewerRole("Reviewer")
  }

  entity ConferenceRole {
    conference -> Conference
    role       -> UserRole
    name       :: String := role.name + " in " + conference.name
  }
  
  entity ConferenceTask {
    assignees -> Set<User> 
    completed :: Bool  
    conference -> Conference
  }
*/
