module app/people

section users.

  User {
    username :: String (name, unique)
    password :: Secret
    person   -> Person (notnull)
  }
  
section persons.
  
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
    blog      -> Blog
  }
  
  //   publications := select pub from Publication as pub where ~this member of pub.authors
    
  //   rececentPublications :=
  // }
  
  //  query Person p {
  //    publications := Publication pub where pub.authors.has(p)
  //    projects := ResearchProject proj where proj.members.has(p)
  //  }

section person pages.

  define personSidebar(p : Person) {
    list {
      listitem{navigate(p.name, viewPerson(p))}
      listitem{navigate("Publications", personPublications(p))}
      listitem{navigate("Blog", viewBlog(p.blog))}
      listitem {
        "Projects"
          for(pr : ResearchProject) {
            navigate(pr.name, viewResearchProject(pr))
          }
      }
    }
  }
    
  define page viewPerson(person : Person) 
  {    
    main() 
    
    title{text(person.name)}
    
    define sidebar() { 
      personSidebar(person) 
    }
    
    define manageMenu() {
      navigate("Edit", editPerson(person))
    }
    
    define body() {
      section{
      	div("photo"){image(person.photo)}
      	
        header{text(person.name)}
    
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
        
        section{ 
          header{"Publications"}
          publicationsBy(person)
        }
        
        var projects : List<ResearchProject> :=
          select pr from ResearchProject as pr, Person as pers 
           where (pers.id = ~person.id) and (pers member of pr._members); 
        
        section { 
          header{"Projects"}
          for(project : ResearchProject in projects) {
            navigate(viewResearchProject(project)){
              text(project.fullname) " (" text(project.acronym) ")"
            }
          }
        }
        
      }
    }
  }
