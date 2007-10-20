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

  globals {

    var hb9130        : Room := Room { building := "HB" floor := "09" number := "130" };
    var bordewijkzaal : Room := Room { building := "HB" floor := "19" number := "130" };
    var bibliotheek   : Room := Room { building := "HB" floor := "08" number := "120" };
  
  }

section colloquium

  entity Presentation {
    colloquium -> Colloquium (inverse=Colloquium.presentations)
    speaker    -> Person (inverse=Person.presentations)
    title      :: String (name)
    abstract   :: Text
    date       :: Date // better date support needed
    time       :: String // better time support needed
    duration   :: String // time
    room       :: Room
    status     :: PresentationStatus
  }

  extend entity Person {
    presentations -> Set<Presentation> (inverse=Presentation.speaker)
  } 

  entity PresentationStatus {
    status :: String
  }

  globals {

    var proposed  : PresentationStatus := PresentationStatus { status := "proposed" };
    var tentative : PresentationStatus := PresentationStatus { status := "tentative" };
    var confirmed : PresentationStatus := PresentationStatus { status := "confirmed" };

  }

  entity Colloquium {
    acronym       :: String (name)
    fullname      :: String
    description   :: Text
    presentations -> Set<Presentation> (inverse=Presentation.colloquium)
    moderator     -> Person (inverseSlave=Person.moderates)
  }

  extend entity Person {
    moderates -> Set<Colloquium>
  }

  // how to model a subcolloquium (e.g., MoDSE part of SERG)
