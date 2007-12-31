module wiki/twiki

section data model for TWiki pages

  entity TwikiPage {
    key     :: String (id,name)
    content :: WikiText  
  }
  
  access control rules {
    
    rules page twikiPage(*) {
      true
    }
    
    rules page editTwikiPage(*) {
      securityContext.loggedIn
    }
    
    rules page createTwikiPage() {
      securityContext.loggedIn
    }
    
  }
  
section wiki page
  
  define page twikiPage (p : TwikiPage)
  {
    main()
    title{output(p.key)}
    define wikiOperationsMenuItems() {
      twikiPageOperationsMenuItems(p)
    }
    define body() {
      section {
        block("twikiTopicTitle") {
          header{ output(p.key) }
        }
	par{ output(p.content) }
      }
    }
  }
  
section page operations
  
  define twikiPageOperationsMenuItems(p : TwikiPage)
  {
    menuitem{ navigate(editTwikiPage(p)) { "Edit This Page" } }
    menuspacer{}
  }
  