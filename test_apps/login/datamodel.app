module datamodel

section definition

  entity User {
    username :: String(name)
    password :: Secret    
  }