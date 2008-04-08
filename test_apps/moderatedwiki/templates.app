module templates

section main template.

 define main() {
    block("top") {
      top()
    }

    block("body") {
      block("left_innerbody") {
        sidebar()
      }
      block("main_innerbody") {
        body()
      }
    }

    block("footer") {
      footer()
    }
  }
  
  define top() {
    block("header") {}
    block("menubar") {
      menubar {
        conferencesMenu()
      }
    }
  }

section basic page elements.

  define sidebar() {
    list {
      contextSidebar()
    }
    
    list {
    }
  }
  
  define contextSidebar() { }
  
  define footer() {
    "generated with "
    navigate("WebDSL", url("http://www.webdsl.org")) " and "
    navigate("Stratego/XT", url("http://www.strategoxt.org"))
  }
  
section menus.

  define conferencesMenu() {
    form {
      menu {
        menuheader { navigate(home()) { "Home" } }
      }
      menu {
        menuheader { "Pages" }
        for(p : Page where p.version > 0 || (adminRole in securityContext.principal.roles) || (moderatorRole in securityContext.principal.roles)) {
          menuitem { output(p) }
        }
        menuitem { navigate(newPage()) { "New page" } }
      }
      menu {
        menuheader { "User" }
        if(securityContext.loggedIn) {
          menuitem { actionLink("Sign Off", signoff()) }
          action signoff() {
            securityContext.loggedIn := false;
            securityContext.principal := null;
            return home();
          }
        }
        if(!securityContext.loggedIn) {
          menuitem { navigate(signin()) { "Sign in" } }
          menuitem { navigate(register()) { "Register" } }
        }
      }
    }
  }

  define page error(msg : String) {
    main()
    title{"Error"}
    define body() {
      header{"Error"}
      output(msg)
    }
  }

  define page message(msg : String) {
    main()
    title{"Message"}
    define body() {
      header{"Message"}
      output(msg)
    }
  }

