module datamodel/conference

section data model conference

  entity Conference {
    name             :: String (name)
    callforpapers    :: Text
    abstractDeadline :: DateTime
    paperDeadline    :: DateTime
    chairs           -> Set<User> 
    pc               -> Committee
    papers           -> Set<Paper>
  }

  extend entity User {
    registered      :: Bool
  }