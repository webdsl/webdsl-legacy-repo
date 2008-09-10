module templates

section templates

  define main() {
    top()
    topmenu()
    sidebar()
    body()
    footer()
  }
  
  define top() {
    block("logos") {
      image("/images/webdsl_logo_small.png")
      image("/images/webdsl_logo_text.png")
    }
    block("text") {
	    text("WebWorkFlow tests")
	  }
  }
  
  define topmenu() {
      menubar {
        menu {
          menuheader { navigate(home()) { "Home" } }
        }
        menu {
          menuheader { "User" }
          menuitem { navigate(signin()) { "Sign in" } }
          menuitem { navigate(allTasks()) { "Tasks" } }
        }
      }
  }

section basic page elements.

  define sidebar() {
    contextSidebar()
  }
  
  define contextSidebar() {
    header { "Side menu" }
    menuitem { navigate(allTasks()) { "Tasks" } }
  }
  
  define footer() {
    "generated with "
    navigate("WebDSL", url("http://www.webdsl.org")) " and "
    navigate("Stratego/XT", url("http://www.strategoxt.org"))
  }
