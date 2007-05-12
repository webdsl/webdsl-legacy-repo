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

  define main {
    menu()
    sidebar()
    include body
    include footer
  }
  
  define menu {
    image("/serg/layout/serg-logo-color-smaller.png")
  }
  
  define footer {
    text{"generated with"}
    navigate("Stratego/XT", http://www.strategoxt.org)
  }

  // overrides the default view page
  
  define personSidebar(Person p) {
    list {
      listitem{navigate(p.name, viewPerson(p))}
      
      listitem{navigate("Publications", publications(p))}
      
      listitem {
        "Projects"
           list { for(ResearchProject pr : p.projects) {
             listitem(navigate(pr.name, viewResearchProject(pr)))
           }}
      }

      listitem{navigate("Edit", editPerson(p))}
    }
  }
  
  page viewPerson(Person p) {
  
    include main
    
    title{"Homepage of " p.name}
    
    define sidebar { include personSidebar(p) }
    
    define body {
      header{"Homepage of " p.name}
      // how should level be indicated? or should headers be associated
      // with the content they are heading?
    
      image(p.photo){width=180}
      // photo should be right-aligned; leave this to CSS?
      // variable number of properties
    
      header{"Coordinates"}
    
      table {
        row{"homepage", p.homepage} // this is an implicit navigate; i.e., view of URL is a link (?)
        row{"email",    p.email} // same here
        row{"address",  table{
                          row{p.address.street}
                          row{p.address.city}
                        }
        row{"phone", p.address.phone}
      }
    
      header{"Recent Publications"}
    
      publicationsForYear(p, 2007)
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
  
section publication pages.

  page personPublications(Person p)
  {
    main
    
    title{"Publications by " + p.name}
    
    define sidebar { personSidebar(p) }
    
    define body {
      header("Publications by " + p.name)
      publicationsPage(pers.publications)
    }
  }
  
  define publicationsForYear(Person pers, Int year) {
    publicationsPage(pers.publications where p.year == year)
  }

  define publicationsPage(List<Publication> ps) {
    list{ for(Publication p : ps) {
      listitem(
         for(Person a : pub.authors) {text(a.name) text(", ")}
         text(".")
         navigate(text(pub.title), viewPublication(pub))
      )
    }
  }
  
  page viewPublication(Publication pub) {
  
    include main
    
    define sidebar {}
    
    define body {
      table {
        row("title",       input(pub.title))
        row("year",        input(pub.year))
        row("pubabstract", input(pub.pubabstract))
        row("pdf",         input(pub.pdf))
        
        row("authors",     list(pub.authors))
        
        row("add author",  select(Person p where not pub.authors.has(p), pub.authors))
        
        row("projects      list(pub.projects))
        
        row("add project", select(ResearchProject rp where not pub.projects.has(rp))

      }
      action("Delete", pub.delete, viewPublication(pub))
      action("Cancel", nop, viewPublication(pub)) 
    }
    
  }
  
  page editPublication(Publication pub) {
  
    include main
    
    define sidebar {}
    
    define body {
      table {
        row("title",       input(pub.title))
        row("year",        input(pub.year))
        row("pubabstract", input(pub.pubabstract))
        row("pdf",         input(pub.pdf))
        
        row("authors",     list(pub.authors))
        
        row("add author",  select(Person p where not pub.authors.has(p), pub.authors))
        
        row("projects      list(pub.projects))
        
        row("add project", select(ResearchProject rp where not pub.projects.has(rp))

      }
      action("Save", pub.save, viewPublication(pub))
      action("Cancel", nop, viewPublication(pub)) 
    }
    
  }
  
  // note:
  // view(p) (where p is a Publication)
  // expands to a navigation(p.name, viewPublication(p))
  // and 
  // view(expand(p)) (or something similar) expands to
  // table { row("title", view(p.title)) ... }
  // does we can define the effort (recursive expansion) currently
  // defined in both view and edit for CRUD as a model-to-model 
  // transformation
  
  
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