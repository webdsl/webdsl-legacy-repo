module publication/data

section publication

  entity Publication {
    title    :: String
    authors  -> List<Person>
    abstract :: Text
    year     :: Year
    month    :: Month
    url      :: URL
    pages    :: String // should be pair of integers
  }

  // relation
  extend entity Publication {
    cites -> Set<Publication>
    citedBy -> Set<Publication> (inverse=Publication.cites)
  }
  
  // journal
  // inconferenceproceedings
  
  // we need a generic model for version control
  
  extend entity Publication {
    posted   :: Date
    updated  :: Date
    postedBy :: User
  }  
  
section persons

  note {    
    Persons are not necessarily users. 
    Users and Persons can be identified.
  }

  // standard modeling of names?
  // last name needed for good sorting
  
  entity Person {
    lastname    :: String
    middlenames :: String
    firstnames  :: String
  
    homepage    :: URL   (optional)
    email       :: Email (optional)
    address     <> Address // multiple addresses?
    
    publications -> Set<Publication> (inverse=Person.authors)
  }
  
section persons can be users

  // users and persons can be identified
  
  extend entity Person {
    user -> User
  }
  
  extend entity User {
    person -> Person
  }

section favourite publications

  extend entity User {
    favourites -> Set<Publication>
  }
  
  extend entity Publication {
    favouriteOf -> Set<User>
  }
  
section reviews

  entity Review {
    publication -> Publication (notnull)
    author      -> User
    content     -> WikiText
    score       :: Int      (optional)
    posted      :: DateTime // history!
    updated     :: DateTime // history!
  }
  
  
