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

  define homesidebar() {
    list { listitem{ navigate("Home", home()) } }
  }
  
  define sidebar() {
    list { 
       listitem { login() }
       listitem { logout() }
       listitem { navigate(home()) { "Home" } }
       div("loggedIn"){
       listitem { navigate(editUser(securityContext.principal)){"Edit Profile"} }
       listitem { navigate(friends(securityContext.principal)){"Friends"} }
       listitem { navigate(groups(securityContext.principal)){"Groups"} }
       }
    	 listitem { navigate(register()) { "New Member" } }
    }
  }
  
  define footer() {
    "generated with "
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

