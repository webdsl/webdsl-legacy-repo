module wiki/access-control

section wiki

  access control rules 
  {
    rules page home() {
      true
    }
  
    rules page page(*) {
      true
    }
    
    rules page wiki() {
      true
    }

    rules page wikiIndex() {
      true
    }
    
    rules page pageDiff(*) {
      securityContext.loggedIn
    }
    
    rules page diff(*) {
      securityContext.loggedIn
    }
    
    rules page editPageDiff(*) {
      securityContext.loggedIn
    }
    
    rules page editPage(*) {
      securityContext.loggedIn
    }
        
    rules page newPage() {
      true
      // securityContext.loggedIn
    }

    rules page createPage() {
      true
      // securityContext.loggedIn
    }

    rules template pageOperationsMenuItems(p : Page) {
      securityContext.loggedIn
    }

  }
