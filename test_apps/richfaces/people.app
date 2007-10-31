module app/people

section users.

  entity User {
    username :: String (name, unique)
    password :: Secret
    person   -> Person (notnull)
  }
  
section persons.

  entity Person {
    fullname  :: String (name)
    email     :: Email
    user      -> User
  }
