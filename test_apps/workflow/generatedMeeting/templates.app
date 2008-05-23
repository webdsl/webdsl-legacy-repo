module templates

section templates

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
  }
  
  define top() {
    block("menubar") {
      menubar {
        menu {
          menuheader { navigate(home()) { "Home" } }
        }
        if (!securityContext.loggedIn) { 
          menu {
            menuheader { navigate(signin()) { "Sign in" } }
          }
        }
        if (securityContext.loggedIn) { 
          menu {
            menuheader { output(securityContext.principal) }
            menuitem { signoff() }
            menuitem { navigate(allTasks()) { "Tasks" } }
          }
        }
      }
    }
  }

section basic page elements.

  define sidebar() {
    contextSidebar()
  }
  
  define contextSidebar() { }
  
  define footer() {
    "generated with "
    navigate("WebDSL", url("http://www.webdsl.org")) " and "
    navigate("Stratego/XT", url("http://www.strategoxt.org"))
  }
