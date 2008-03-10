module templates

section main template.

 define main() 
  {
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
        applicationMenubar()
        conferenceMenu()
      }
    }
  }

  define applicationMenubar() { }

section basic page elements.

  define sidebar() {
    list {
      listitem { navigate(home()) { "Home" } }
      if(securityContext.loggedIn) {
        form {
          listitem { actionLink("Sign Off", signoff()) }
          action signoff() {
            securityContext.loggedIn := false;
            securityContext.principal := null;
            return home();
          }
        }
      }
      if(!securityContext.loggedIn) {
        listitem { navigate(signin()) { "Sign in" } }
        listitem { navigate(register()) { "Register" } }        
      }
    }
  }
  
  define footer() {
    "generated with "
    navigate("WebDSL", url("http://www.webdsl.org")) " and "
    navigate("Stratego/XT", url("http://www.strategoxt.org"))
  }
  
section menus.
  
  define conferenceMenu() {
    menu {
      menuheader { "Conference" }
      menuitem { navigate(allConference()) { "List all" } }
      menuitem { navigate(createConference()) { "Create conference"} }
    }
  }

  

