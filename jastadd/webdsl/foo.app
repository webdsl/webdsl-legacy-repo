module foo

  entity User {
    username :: String
    password :: Secret
    topics   -> Set<Topic>
  }
  
  entity Topic {
    name    :: String
    content :: WikiText
    authors -> Set<User> (inverse=User.topic)
    foo     -> Bla
  }
  