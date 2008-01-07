module data

section RBAC data

  entity Document
  {
    title :: String (name)
    text :: Text
    project -> Project (inverse = Project.documents)
  }

  entity User
  {
    name :: String
  }
  
  entity Project
  {
    name :: String
    documents -> Set<Document>
  }
