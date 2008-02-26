module users

imports topics

section DataModel

  entity User {
    username :: String
    password :: Secret
    topics   -> Set<Topic>
    email    :: Email
  }