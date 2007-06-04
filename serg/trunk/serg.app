application org.webdsl.serg

description 

  This application organizes information relevant for a 
  research group, including people, publications, students,
  projects, etc.

end

section login.

  define page login() { 
    main()
    
    define sidebar(){}
    
    define body() { 
      form {
        table {
          row{"username" input(user.username)}
          row{"password" input(user.password)}
        }
        action("Login", login())
      }
    }
    
    var user : User;
          
    action login() { 
      var users : List<User>; // := user.search();
      if true then // users.size() == 1 then
        session.user := user;
        return home();
      else
        errorMessage("Wrong username/password combination");
      end
      return home();
    }
  
  }

section setup.

  define main() {
    div("outersidebar") {
      logo()
      sidebar()
    }
    div("outerbody") {
      div("menubar") {
        menu()
      }
      body()
      footer()
    }
  }
  
  define logo() {
    navigate(home()){image("/img/serg-logo-color-smaller.png")}
  }
  
  define homesidebar() {
    list { listitem{ navigate("Home", home()) } }
  }
  
  define menu() {
    //if session.user.hasRole() then
    
      //selectMenu {
      // menuitem{"New"}
      //  menuitem{navigate("New Publication", editPublication())}
      //}
      
    //end
    list {
      listitem {
        "New"
        list {
          listitem { navigate("New User", createUser()) }
          listitem { navigate("New Person", createPerson()) }
          listitem { navigate("New Publication", createPublication()) }
        }
      }
    }
    list {
      listitem {
        "Manage"
        list {
          manageMenu() // depends on context
        }
      }
    }
    
    // menu with all people
    
    list {
      listitem {
        "People"
          for(person10 : Person) { navigate(person10.name, viewPerson(person10)) }
      }
    }
  }
  
  define manageMenu() {}
  
  define footer() {
    "generated with "
    navigate("Stratego/XT", url("http://www.strategoxt.org"))
  }

section serg home page.

  define page home() {
  
    title{"Software Engineering Research Group"}
    
    define sidebar() {
      initDatabase()
    }
    
    define body () {
    
      section { header{"Software Engineering Research Group"}
        
      section { header{"Mission"}

        "Software engineering is concerned with methods and techniques for building high quality software systems. This not only includes software construction, but also requirements analysis, design, system integration, testing, deployment, and making changes to software systems after their first release."

        "The mission of the Delft Software Engineering Research Group is"

        list {
          listitem { "to develop a deep understanding of how people build and evolve software systems;" }
          listitem { "to develop novel methods, techniques and tools that advance the way in which software is built and adjusted; and" }
          listitem { "to offer students an education that prepares them to take a leading role in complex software development projects." }
        }
      
        "Research at the Delft Software Engineering Research Group is centered around two themes, software evolution and embedded software, which are studied seperately as well as in combination in two laboratories:"

        list {
          listitem { "The Software Evolution Research Laboratory (SWERL), and" }
          listitem { "The Embedded Software Laboratory (ESL)" }
        }
      }
      
      section { header{"Publications"}
        for(pub : Publication) {
            navigate(pub.name, viewPublication(pub))
        }
      }
      
      section { header{"People"}
        for(pers : Person) {
          navigate(pers.name, viewPerson(pers))
        }
      }
      
      }

    }
    
    main()

  }

section people. 

  User {
    username :: String (name, unique)
    password :: Secret
    person   -> Person (notnull)
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
    // birthdate :: Date
    address   <> Address
    user      -> User
  }
  
//  query Person p {
//    publications := Publication pub where pub.authors.has(p)
//    projects := ResearchProject proj where proj.members.has(p)
//  }
  
section people pages.

  define personSidebar(p : Person) {
    list {
      listitem{navigate(p.name, viewPerson(p))}
      listitem{navigate("Publications", personPublications(p))}
      listitem {
        "Projects"
          for(pr : ResearchProject) {
            navigate(pr.name, viewResearchProject(pr))
          }
      }
      listitem{navigate("Edit", editPerson(p))}
    }
  }
    
  define page viewPersonMy(person : Person) 
  {    
    main() 
    
    title{"Homepage of " text(person.name)}
    
    define sidebar() { 
      personSidebar(person) 
    }
    
    define manageMenu() {
      navigate("Edit", editPerson(person))
    }
    
    define body() {
      section{
        header{"Homepage of " text(person.name)} 

      	// how should level be indicated? or should headers be associated
      	// with the content they are heading?
    
      	image(person.photo)
      	// photo should be right-aligned; leave this to CSS?
      	// variable number of properties
    
      	section{
          header{"Coordinates"}
    
          table {
            row{"homepage" navigate(url(person.homepage))}
            row{"email"    navigate(url(person.email))}
            row{"address"  table{
                             row{text(person.address.street)}
                             row{text(person.address.city)}
                           }}
            row{"phone" text(person.address.phone)}
          }
        }
    
        //section{
          //header{"Recent Publications"}
          //publicationsForYear(person, 2007)
           // better: 10 most recent publications
        //}
        
        section { header{"Publications"}
          for(pub : Publication) {
            navigate(pub.name, viewPublication(pub))
          }
        }
      }
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

  define page personPublications(p : Person) {
    title{"Publications by " text(p.name)}
    
    define sidebar() { personSidebar(p) }
    
    define body() {
      header{"Publications by " text(p.name)}
      //publicationsPage(p.publications)
    }
    main()
  }
  
  define publicationsForYear(pers : Person, year : Int) {
  }
  
     //    publicationsPage(pers.publications where p.year == year)

  define publicationsPage(publications : List<Publication>) {
    list{
      for(publication : Publication in publications) { 
        listitem{
          for(author : Person in publication.authors) {navigate(author.name, viewPerson(author)) ","}
          navigate(publication.name, viewPublication(publication))
        }
     }
    }
  }
  
  define page viewPublicationMy(pub : Publication) {
    title{"Publication " text(pub.title)}
    
    define sidebar() {
        navigate("Edit", editPublication(pub))
    }
    
    define body() {
      header{text(pub.title)}
      table {
        row{"title"       text(pub.title)}
        row{"year"        text(pub.year)}
        row{"pubabstract" text(pub.pubabstract)}
        row{"pdf"         text(pub.pdf)}
        row{"authors"     for(author : Person in pub.authors) { navigate(author.name, viewPerson(author)) } }
        row{"projects"    for(project : ResearchProject in pub.projectsList) { 
                            navigate(project.name, viewResearchProject(project)) 
                          }}
      }
      form {
        action("Delete", deletePublication(pub))
      }
    }
    
    action deletePublication(pub : Publication) {
      pub.delete();
      return home();
    }
    
    main()
  }
  
  // textlist(Person, pub.authors)}
  // textlist(ResearchProject, pub.projects)}
  //define textlist(t : Type, l : List<t>) {
  //  for(o : t in l) {
 //     navigate(o)
  //  }
 // }  

   
  define page editPublicationMy(pub : Publication) {
    title{"Edit " text(pub.title)}
    
    define sidebar() {}
    
    define body() {
       header{"Edit " text(pub.title)}
       form { 
          table {
             row { "title" input(pub.title) }
             
             //row { "authors" input(pub.authors) }
             
             
             row { "authors"     
               for(author : Person in pub.authors) {
                 navigate(author.name, viewPerson(author))
                 actionLink("[X]", removeAuthor(author))
               }
             }
             row { "" 
               select(author1 : Person, "Add Author", addAuthor(author1))
             }

             action addAuthor(author : Person) { pub.authors.add(author); }
             action removeAuthor(author : Person) { pub.authors.remove(author); }
           
             row{"year"        input(pub.year)}
             row{"pubabstract" input(pub.pubabstract)}
             row{"pdf"         input(pub.pdf)}
             
             // edit projects
             
             row { "projects"    
               for(project : ResearchProject in pub.projectsList) { 
                 navigate(project.name, viewResearchProject(project))
                 actionLink("[X]", removeProject(project))
               }
             }
             row { "" 
               select(project1 : ResearchProject, "Add Project", addProject(project1))
             }
             
             action removeProject(project : ResearchProject) { pub.projects.remove(project); }
             action addProject(project : ResearchProject) { pub.projects.add(project); } 
          }
          action("Save",   save())
          action("Cancel", cancel())
       }
    }
    
    action cancel() { return viewPublication(pub); }
    
    action save() {
      pub.save();
      return viewPublication(pub);
      
      //if pub.save() then
      //  return viewPublication(pub);
      //else
      //  return errorPage(msg);
    }
    
    main()
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
    description  :: Text
    members      -> Set<Person>
    proposal     -> Publication
    publications -> Set<Publication>
  }
  
  define page viewResearchProjectMy(project : ResearchProject) {
    title{text(project.fullname)}
    define sidebar() {}
    define body() {
      section{
        header{text(project.fullname)}
        
        text(project.description)
      
        section{header{"Members"}
          list{for(member : Person in project.membersList) {
            listitem{ navigate(member.name, viewPerson(member)) }
          }}
        }
        
        section{header{"Publications"}
          publicationsPage(project.publicationsList)
        }
      }
    }
    main()
  }

section init database .
 
 define page initDatabase() {
 
  form { action("Init Database", initDB()) }
 
  action initDB() {
  
    var Mekelweg4 : Address := 
      Address {
        street := "Mekelweg"
        city   := "Delft"
        phone  := "015"
      };
      
    var Ordina : Address := 
      Address {
        street := "Ringwade 1"
        city   := "Nieuwegein"
        phone  := "030"
      };
      
    var EelcoVisser : Person  :=
      Person {
        fullname := "Eelco Visser" 
        email    := "visser@acm.org"
        address  := Mekelweg4
        homepage := "http://www.eelcovisser.net"
        photo    := "/img/eelcovisser.jpg"
        //photo    := "http://static.flickr.com/56/141569082_372ea07ea9_m.jpg"
      }; 
      
    EelcoVisser.user :=
      User {
        username := "EelcoVisser"
        password := "foo"
        person   := EelcoVisser
      };
      
    var ArieVanDeursen : Person :=
      Person {
        fullname := "Arie van Deursen"
        email    := "A.vanDeursen@tudelft.nl"
        address  := Mekelweg4
        homepage := "http://www.st.ewi.tudelft.nl/~arie/"
        photo    := "http://www.st.ewi.tudelft.nl/~arie/pictures/arie-in-delft-klein.jpg"
      };
      
    var JosWarmer : Person :=
      Person {
        fullname := "Jos Warmer"
        email    := "jos@ordina.nl"
        address  := Ordina
        homepage := "http://www.klasse.nl/who/cv-jos.html"
        photo    := "http://www.klasse.nl/who/images/jos.gif"
      };
           
    var MoDSE : ResearchProject :=
      ResearchProject {
        fullname := "Model-Driven Software Evolution"
        acronym  := "MoDSE"
        members  := {EelcoVisser, ArieVanDeursen, JosWarmer}
        description := "The promise of model-driven engineering (MDE) is that the development and maintenance effort can be reduced by working at the model instead of the code level. Models define what is variable in a system, and code generators produce the functionality that is common in the application domain. The problem with model-driven engineering is that it can lead to a lock-in in the abstractions and generator technology adopted at project initiation. Software systems need to evolve, and systems built using model-driven approaches are no exception. What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation. In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain. While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. It is this gap that this project proposes to address. The first fundamental premise of this proposal is that evolution should be a continuous process. Software development is a continuous search for recurring patterns, which can be captured using domain-specific modeling languages. After developing a number of systems using a particular meta-model, new patterns may be recognized that can be captured in a higher-level or richer meta-model. The second premise is that reengineering of legacy systems to the model-driven paradigm should be a special case of this continuous evolution, and should be performed incrementally. The goal of this project is to develop a systematic approach to model-driven software evolution. This approach includes methods, techniques, and underlying tool support. We will develop a prototype programming environment that assists software engineers with the introduction, development, and maintenance of models and domain-specific languages."
      };
      
    var GTTSE07 : Publication := 
      Publication {
        title := "Domain-Specific Language Engineering"
        authors := [EelcoVisser]
        year := 2007
        pubabstract := "The goal of domain-specific languages (DSLs) is to increase the productivity of software engineers by abstracting from low-level boilerplate code. Introduction of DSLs in the software development process requires a smooth workflow for the production of DSLs themselves. This tutorial gives an overview of all aspects of DSL engineering: domain analysis, language design, syntax definition, code generation, deployment, and evolution, discussing research challenges on the way. The concepts are illustrated with DSLs for web applications built using several DSLs for DSL engineering: SDF for syntax definition, Stratego/XT for code generation, and Nix for software deployment."
        projects := {MoDSE}
      };
      
    var MoDSE07 : Publication := 
      Publication {
        title       := "Model-Driven Software Evolution: A Research Agenda"
        authors     := [ArieVanDeursen, JosWarmer, EelcoVisser]
        year        := 2006
        pubabstract := "Software systems need to evolve, and systems built using model-driven approaches are no exception.  What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation.  In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain.  While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. In this paper, we analyze the problems raised by the evolution of model-based software systems and identify challenges to be addressed by research in this area."
        projects    := {MoDSE}
      };
    
    MoDSE.proposal := MoDSE07;
    MoDSE.publications := {MoDSE07, GTTSE07};
     
    MoDSE.persist();

  }
 }
 