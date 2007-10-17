module datamodel

section people

  entity Person {
    firstname    :: String
    lastname     :: String
    fullname     :: String (name) := firstname + " " + lastname
    affilliation :: String
    email        :: Email
    homepage     :: URL
  }

section buildings

  entity Room {
    symbolicName :: String
    building     :: String
    floor        :: String
    number       :: String
    coordinates  :: String (name) := building + " " + floor + "." + number
  }

section colloquium

  entity Presentation {
    colloquium -> Colloquium       // can belong to more than one series?
    speaker    -> Person
    title      :: String (name)
    abstract   :: Text
    date       :: String // better date support needed
    time       :: String // better time support needed
    duration   :: String // time
    room       :: Room
    status     :: PresentationStatus
  }

  entity PresentationStatus {
    status :: String
  }

  entity Colloquium {
    acronym       :: String (name)
    fullname      :: String
    description   :: Text
    presentations -> Set<Presentation>
    moderator     -> Person
  }

  // how to model a subcolloquium (e.g., MoDSE part of SERG)
