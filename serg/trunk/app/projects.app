module app/projects

section domain.

  entity ResearchProject {
    fullname     :: String
    acronym      :: String (name)
    description  :: Text
    members      -> Set<Person>
    proposal     -> Publication
    publications -> Set<Publication>
  }

section view.
 
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
  
section looking up projects.

  define listProjects(person : Person) {
  
    var projects : List<ResearchProject> :=
      select pr from ResearchProject as pr, Person as pers 
       where (pers.id = ~person.id) and (pers member of pr._members); 

    list { 
      for(project : ResearchProject in projects) {
        listitem { 
          navigate(viewResearchProject(project)){
            text(project.fullname) " (" text(project.acronym) ")"
          }
        }
      }
    }
  }

  define listProjectAcronyms(person : Person) {
  
    var projects : List<ResearchProject> :=
     select pr from ResearchProject as pr, Person as pers 
      where (pers.id = ~person.id) and (pers member of pr._members); 
  
    list { 
      for(pr : ResearchProject in projects) { listitem { output(pr) } }
    }
  }
 