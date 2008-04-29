module datamodel/paper
  
section data model conference paper

  extend entity User {
    authoredPapers  -> Set<Paper>
  }

  entity Paper {
    conference     -> Conference (inverse=Conference.papers)
    title          :: String (name)
    abstract       :: Text
    authors        -> Set<User> (inverse=User.authoredPapers)
    accepted       :: Bool
    final          :: Bool
  }
