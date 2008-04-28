module datamodel/conference

section data model conference

  entity Conference {
    name               :: String
    submissionDeadline :: DateTime
    chairs             -> Set<User> 
    pcMembers          -> Set<User>
    papers             -> Set<Paper>
  }

  extend entity User {
    registered      :: Bool
  }
  
  entity Nix { 
    nix :: Int
  }