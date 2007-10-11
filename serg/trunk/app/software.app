module software

description {
  Meta-data about software products and releases.
}

section domain

  entity SoftwareProduct {
    name        :: String
    description :: Text
    releases    -> List<SoftwareRelease>
    lead        -> Person
    developers  -> List<Person>
    licence     -> License
  }
  
  entity SoftwareRelease {
    product      -> SoftwareProduct
    version      :: String (name)
    url          :: URL
    released     :: Date
    changes      :: Text
    contributors -> List<Person>
  }

  entity License {
    acronym :: String (name)
    text    :: Text
  }
  
section pages
