module app/people

section users.

  entity User {
    username  :: String (name, notempty)
    password  :: Secret (notempty,minlength(6))
    firstname :: String (notempty)
    lastname  :: String
    telephone :: String
    email     :: Email (email)
  }