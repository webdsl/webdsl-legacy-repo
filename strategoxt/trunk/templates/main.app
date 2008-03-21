module templates/main

section strategoxt template

  define main()
  {
    block("twikiViewPage") {
      block("fullPage") {
        block("twikiMiddleContainer") {
          block("twikiLeftBar") {
            block("twikiLeftBarContents") {
              logo()
              applicationSidebar()
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
    if (config.logo != null) { navigate(home()){output(config.logo)} }
  }

  define sidebar() {
  }
  
  define applicationSidebar() { 
    if(config.sidebar != null) { output(config.sidebar.content) }
    sidebar()
  }
  
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
      groupMenu()
      usersMenu()
      currentUser()
      thisWikiMenu()
      thisMenu()
    }
  }
  
  define thisMenu() {  } 
  
  
