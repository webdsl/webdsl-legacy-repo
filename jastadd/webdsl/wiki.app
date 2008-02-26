module wiki

section dataModel

  entity User {
    username :: String
    password :: Secret
    topics   -> Set<Topic>
    email    :: Email
  }
  
  entity Topic {
    name    :: String
    content :: WikiText
  }
  
  extend entity Topic {
    acl -> ACL
  }
  
  entity ACL {
    viewers -> Set<User>
    editors -> Set<User>
  }