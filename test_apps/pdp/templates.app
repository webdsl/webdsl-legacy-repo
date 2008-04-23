module templates

section main template.

  define main() {
    div("outersidebar") {
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

section basic page elements.

  define contextSidebar() { }

  define sidebar() {
    list {
      listitem { navigate(signin()) { "Sign in" } }
      //listitem { navigate(pdpMeetingTasks()) { "PDP Tasks" } }
      //listitem { navigate(pdpMeetingStatuses()) { "PDP Statuses" } }
    }
    contextSidebar()
  }
  
  define footer() {
    "generated with "
    navigate("WebDSL", url("http://www.webdsl.org")) " and "
    navigate("Stratego/XT", url("http://www.strategoxt.org"))
  }
  
section menus.
  
  define menu() {

    
  }
  
section entity management.

  define manageMenu() {}
  
  define page manage() {
    main()
    define sidebar() {}
    define body() {
      createMenu()
      allMenu()
    }
  }

