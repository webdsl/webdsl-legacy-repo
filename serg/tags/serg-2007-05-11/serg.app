application org.webdsl.serg

description

  This application organizes information relevant for a 
  research group, including people, publications, students,
  projects, etc.

end

section people. 

  User {
    username :: String (name, unique)
    email    :: Email  (unique)
    password :: Secret
    person   <> Person
  }
  
  Address {
    street :: String
    city   :: String
    phone  :: String
  }

  Person {
    fullname  :: String (name)
    homepage  :: URL
    photo     :: Image
    birthdate :: Date
    address   <> Address
    user      -> User
  }

section publications.
    
  Publication {
    title       :: String (name)
    authors     -> List<Person>
    year        :: Int // use Year defined type
    pubabstract :: Text // note: abstract is a reserved word in java!
    projects    -> Set<ResearchProject>
    pdf         :: URL
  }
    
  TechnicalReport : Publication {
    number     :: Int
 // code       :: String := "TUD-SERG-" + year + "-" + number
    document   :: Text // should be Document or PDF or similar
    preprintof -> Publication
  }
  
section projects.

  ResearchProject {
    fullname     :: String
    acronym      :: String (name)
    members      -> Set<Person>
    proposal     -> Publication
    publications -> Set<Publication>
  }

section init database .

  action initDB {
  
    Address Mekelweg4 := 
      Address {
        street := "Mekelweg"
        city   := "Delft"
        phone  := "015"
      };
      
    Address Ordina := 
      Address {
        street := "Ringwade 1"
        city   := "Nieuwegein"
        phone  := "030"
      };
      
    Person EelcoVisser :=
      Person {
        fullname := "Eelco Visser" 
        address  := Mekelweg4
        homepage := "http://www.eelcovisser.net"
        photo    := "http://static.flickr.com/56/141569082_372ea07ea9_m.jpg"
      }; 
      
    EelcoVisser.user :=
      User {
        username := "EelcoVisser"
        email    := "visser@acm.org"
        password := "foo"
        person   := EelcoVisser
      };
      
    Person ArieVanDeursen :=
      Person {
        fullname := "Arie van Deursen" 
        address  := Mekelweg4
        homepage := "http://www.st.ewi.tudelft.nl/~arie/"
        photo    := "http://www.st.ewi.tudelft.nl/~arie/pictures/arie-in-delft-klein.jpg"
      };
      
    Person JosWarmer :=
      Person {
        fullname := "Jos Warmer" 
        address  := Ordina
        homepage := "http://www.klasse.nl/who/cv-jos.html"
        photo    := "http://www.klasse.nl/who/images/jos.gif"
      };
           
    ResearchProject MoDSE :=
      ResearchProject {
        fullname := "Model-Driven Software Evolution"
        acronym  := "MoDSE"
        members  := {EelcoVisser, ArieVanDeursen, JosWarmer}
      };
      
    Publication GTTSE07 := 
      Publication {
        title := "Domain-Specific Language Engineering"
        authors := [EelcoVisser]
        year := 2007
        pubabstract := "The goal of domain-specific languages (DSLs) is to increase the productivity of software engineers by abstracting from low-level boilerplate code. Introduction of DSLs in the software development process requires a smooth workflow for the production of DSLs themselves. This tutorial gives an overview of all aspects of DSL engineering: domain analysis, language design, syntax definition, code generation, deployment, and evolution, discussing research challenges on the way. The concepts are illustrated with DSLs for web applications built using several DSLs for DSL engineering: SDF for syntax definition, Stratego/XT for code generation, and Nix for software deployment."
        projects := {MoDSE}
      };
      
    Publication MoDSE07 := 
      Publication {
        title       := "Model-Driven Software Evolution: A Research Agenda"
        authors     := [ArieVanDeursen, JosWarmer, EelcoVisser]
        year        := 2007
        pubabstract := ""
        projects    := {MoDSE}
      };
    
    MoDSE.proposal := MoDSE07;
    MoDSE.publications := {MoDSE07, GTTSE07};
     
    MoDSE.persist();

  }