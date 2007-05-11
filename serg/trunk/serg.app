application org.webdsl.serg

description

  This application organizes information relevant for a 
  research group, including people, publications, students,
  projects, etc.

end

section people. 

  User {
    username :: String (name, unique)
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
    email     :: Email
    homepage  :: URL
    photo     :: Image
    birthdate :: Date
    address   <> Address
    user      -> User
  }
  
  query Person p {
    publications := Publication pub where pub.authors.has(p)
    projects := ResearchProject proj where proj.members.has(p)
  }
  
section people pages.

  template main {
    include menu
    include sidebar
    include body
    include footer
  }
  
  template menu {
    image("/serg/layout/serg-logo-color-smaller.png")
  }
  
  template footer {
    text("generated with")
    navigate("Stratego/XT", http://www.strategoxt.org)
  }

  // overrides the default view page
  
  template personSidebar(Person p) {
    list {
      listitem(navigate(p.name, viewPerson(p)))
      listitem(navigate("Publications", publications(p)))
      listitem(
        "Projects"
           list(ResearchProject pr : p.projects) {
             listitem(navigate(pr.name, viewResearchProject(pr)))
           }
      listitem(navigate("Edit", editPerson(p)))
    }
  }
  
  page viewPerson(Person p) { 
  
    include main
    
    template sidebar { include personSidebar(p) }
    
    template body {
      header("Homepage of " p.name)
      // how should level be indicated? or should headers be associated
      // with the content they are heading?
    
      image(p.photo, width=180)
      // photo should be right-aligned; leave this to CSS?
      // variable number of properties
    
      header("Coordinates")
    
      table(
        row("homepage", p.homepage) // this is an implicit navigate; i.e., view of URL is a link (?)
        row("email",    p.email) // same here
        row("address",  table(
                          row(p.address.street)
                          row(p.address.city)
                        )
        row("phone", p.address.phone)
      )
    
      header("Recent Publications")
    
      publicationsForYear(p, 2007)
    }

  }

  page personPublications(Person p) instantiating mainTemplate
  {
    sidebar { personSidebar(p) }
    body {
      header("Publications by " + p.name)
      publicationsPage(pers.publications)
    }
  }
  
  page publicationsForYear(Person pers, Int year) {
    publicationsPage(pers.publications where p.year == year)
  }
  
  page publicationsPage(List<Publication> ps) {
    list(Publication p : ps) {
      listitem(
         for(Person a : pub.authors) {text(a.name) text(", ")}
         text(".")
         navigate(viewPublication(pub, text(pub.title))
      )
    }
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