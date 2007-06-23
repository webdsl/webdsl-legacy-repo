module software

description

  Meta-data about software products and releases.

end

section domain.

  SoftwareProduct {
    name        :: String
    description :: Text
    releases    -> List<SoftwareRelease>
    lead        -> Person
    developers  -> List<Person>
    licence     -> License
  }
  
  SoftwareRelease {
    product      -> SoftwareProduct
    version      :: String (name)
    url          :: URL
    released     :: Date
    changes      :: Text
    contributors -> List<Person>
  }

  License {
    acronym :: String (name)
    text    :: Text
  }
  
section pages.