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
          for(person10 : Person) { navigate(person10.name, viewPerson(person10)) }
      }
    }
    
    list {
      listitem {
        "Projects"
          for(project11 : ResearchProject) { 
            navigate(project11.acronym, viewResearchProject(project11))
          }
      }
    }
        
    // make manage menu conditional on role of user
    list {
      listitem {
        "Manage"
        list {
          manageMenu() // depends on context
          createMenu()
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
