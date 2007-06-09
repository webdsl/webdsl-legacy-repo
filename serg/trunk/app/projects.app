module app/projects

section domain.

  ResearchProject {
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

 