application test

section datamodel

  entity User {
    authoredPapers  -> Set<Paper> (inverse=Paper.authors)
  }

  entity Paper {
    title          :: String (name)
    abstract       :: Text
    authors        -> Set<User> (inverse=User.authoredPapers)
    accepted       :: Bool
    final          :: Bool
  }