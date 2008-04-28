module datamodel/accesscontrol

section data model access control

  entity User {
    username        :: String
    name            :: String
    email           :: Email (id)
    password        :: Secret
    isAdmin         :: Bool
  }
