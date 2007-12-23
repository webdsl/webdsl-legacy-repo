module templates/main

section main template

  define mainWDO() 
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
  
  define topWDO() {
    block("header") {}
    block("menubar") { 
      applicationMenubar()
    }
  }
  
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
    section{header{navigate(home()){"WebDSL"}}}
  }

  define homeSidebar() {
    list { 
      listitem{ navigate(home()){"Home"} }
      //listitem { currentUser() }
    }
  }
  
  define sidebar() {
    logo()
    homeSidebar()
    contextSidebar()
    applicationSidebar()
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