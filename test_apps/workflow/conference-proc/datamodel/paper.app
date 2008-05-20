module datamodel/paper
  
imports datamodel/user
imports datamodel/conference

section conference paper

  entity Paper {
    conference  -> Conference (inverse=Conference.papers)
    title       :: String (name)
    abstract    :: Text
    authors     -> Set<User> (inverse=User.authoredPapers)
    accepted    :: Bool
    final       :: Bool
  }

  extend entity User {
    authoredPapers  -> Set<Paper>
  }
