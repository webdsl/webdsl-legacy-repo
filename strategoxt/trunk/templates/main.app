module templates/main

section strategoxt template

  define main()
  {
    block("twikiViewPage") {
      block("fullPage") {
        block("twikiMiddleContainer") {
          block("twikiLeftBar") {
            block("twikiLeftBarContents") {
              sidebar()
            }
          }
          block("twikiMain") {
            block("menubar") {
              applicationMenubar()
            }
            block("twikiTopic") {
              body()
            }
          }
        }
      }
    }
  }

section basic page elements

  define logo() {
    //navigate(home()){output(config.logo)}
    "Insert logo here"
  }

  define sidebar() {
    logo()
    output(config.sidebar.content)
  }
  
  define applicationSidebar() { }
  
  define footer() {
    block("footer") {
      block("left_footer") {
        navigate(home()) { "About WebDSL" }
      }
      block("right_footer") {
        "generated from "
        navigate("WebDSL", url("http://www.webdsl.org"))
        " with "
        navigate("Stratego/XT", url("http://www.strategoxt.org"))
      }
    }
  }
  
  define contextSidebar() { }
  
section menus

  define wikiMenuItems() { }
  
  define applicationMenubar()
  {
    menubar {
      //menu { 
      //  menuheader{ navigate(news()){"News"} }
      //  newsMenu()
      //}
      wikiMenu()
      //blogMenu()
      //forumMenu()
      //issuesMenu()
      //presentationsMenu()
      menu { 
        menuheader{ navigate(users()){"Users"} }
        for(b : User in config.usersList) {
          menuitem{ output(b) }
        }
      }
      currentUser()
      adminMenu()
    }
  }
  
  define adminMenu() 
  {
    menu { 
      menuheader{ "Admin" }
      menuitem{ navigate(configuration(config)){"Configuration"} }
      menuitem{ navigate(editConfiguration(config)){"Edit Configuration"} }
      menuitem{ navigate(pendingRegistrations()){"Pending Registrations"} }
    }
  }
  
  access control rules {
    rules template adminMenu() {
      securityContext.loggedIn
      // todo: check that principal has admin rights
    }
  }