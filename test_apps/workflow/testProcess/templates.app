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

    block("footer") {
      footer()
    }
  }
  
  define top() {
    block("header") {}
    block("menubar") {
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
