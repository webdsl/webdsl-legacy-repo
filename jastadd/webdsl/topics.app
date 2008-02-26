module topics

imports users

section dataModel

  entity Topic {
    name    :: String
    content :: WikiText
    authors -> Set<User> (inverse=User.topics)
  }
  
  extend entity User {  
    topics -> Set<Topic>
  }