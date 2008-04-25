module templates
  
section templates

  define main() {
    block("top") {
      top() 
    }
    block("body") {
      block("left_innerbody") {
        contextSidebar() 
      }
      block("main_innerbody") {
        body() 
      } 
    }
  }
  
  define contextSidebar() {}
  define body() {}

  define top() {
    block("header") {}
    block("menubar") { 
      applicationMenubar() 
    } 
  }
  
  define applicationMenubar()
  {
    menubar {
      menu { 
        menuheader{ navigate(home()){"Home"} }
      }  
      loginMenu()
      menu {
        menuheader{ "temp" }
        menuitem{ navigate(createNix()){"Create nix"} }
      }
    }
  }

  define loginMenu() {
    if (securityContext.loggedIn) {
      menu {
        menuheader{ navigate(signOut()){"Sign out"} }
      }
    }

    if (!securityContext.loggedIn) {
      menu {
        menuheader{ navigate(signIn()){"Sign in"} }
      }
    }
  }
