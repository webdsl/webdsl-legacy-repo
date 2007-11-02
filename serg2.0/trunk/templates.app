module templates

section main template

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

section basic page elements

  define logo() {
    navigate(home()){image("/img/serg-logo-color-smaller.png")}
  }
  
  define homesidebar() {
    list { listitem{ navigate("Home", home()) } }
  }
  
  define sidebar() {
    "default sidebar"
  }
  
  define sidebar() {
    "default body"
  }
  
  define footer() {
    "generated with "
    navigate("Stratego/XT", url("http://www.strategoxt.org"))
  }
  
section menus
  
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
        
    // @acl make manage menu conditional on role of user
    list {
      listitem {
        navigate("Manage", manage())
        list {
          manageMenu()
        }
      }
    }
    
    // @acl if user is logged in show name instead
    list {
      listitem {
        navigate(login()){"Login"}
      }
    }

  }
  
section entity management

  define manageMenu() {}
  
  define page manage() {
    main()
    define sidebar() {}
    define body() {
      createMenu()
      allMenu()
    }
  }

