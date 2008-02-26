module users


section DataModel

  entity User {
    username :: String
    password :: Secret
    email    :: Email
  }