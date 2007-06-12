module app/templates

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

    list {
      listitem {
        "People"
          list{ for(person : Person) { listitem { navigate(person.name, viewPerson(person)) } } }
      }
    }
    
    list {
      listitem {
        "Projects"
          list { for(project : ResearchProject) { 
            listitem { navigate(project.acronym, viewResearchProject(project)) }
          } }
      }
    }
        
    // make manage menu conditional on role of user
    list {
      listitem {
        "Manage"
        list {
          manageMenu() // depends on context
          createMenu()
          allMenu()
        }
      }
    }
    
    list {
      listitem {
        navigate(login()){"Login"}
      }
    }
    // if user is logged in show name instead

  }
  
  define manageMenu() {}
  
  define footer() {
    "generated with "
    navigate("Stratego/XT", url("http://www.strategoxt.org"))
  }
