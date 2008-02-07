module wiki/access-control

section ac data

  extend entity Web {
    acl -> ACL
  }
  
  extend entity Page {
    acl -> ACL
  }
  
  note {
  
    - policy for creation of groups
  
    - policy for assigning members to groups
  
    - policy for assigning groups to pages and webs
  
  }
  
section acr

  globals {
    
    function containsOneOf(xs : Set<UserGroup>, ys :  Set<UserGroup>) : Bool {
      for(y : A in ys) {
        if(y in xs) { return true; }
      }
      return false;
    }
    
    // note: this should be defined using true generics
   
    function memberOf(xs : Set<UserGroup>) : Bool {
      if (xs.length = 0) { return false; }
      else {
        for(y : A in securityContext.principal.groups) {
          if(y in xs) { return true; }
        }
        return false;
      }
    }
  }
  
  access control rules
  {
    predicate mayViewWeb(w : Web) {
      memberOf(w.acl.view)
    }
    
    predicate mayEditWeb(w : Web) {
      memberOf(w.acl.edit)
    }
    
    predicate mayViewPage(p : Page) {
      memberOf(p.acl.view) || mayViewWeb(p.web)
    }
    
    predicate mayEditPage(p : Page) {
      memberOf(p.acl.view) || mayEditWeb(p.web)
    }
  }
  
  access control rules
  {
    rules page home() {
      true
    }
  
    rules page page(p : Page) {
      mayViewPage(p)
    }
    
    rules page wiki() {
      true
    }

    rules page wikiIndex() {
      true
    }
    
    rules page pageDiff(d : PageDiff) {
      mayViewPage(d.page)
    }
    
    rules page editPageDiff(d : PageDiff) {
      mayEditPage(d.page)
    }
    
    rules page editPage(p : Page) {
      mayEditPage(d.page)
    }
        
    rules page newPage() {
      securityContext.loggedIn
      // make web dependent
    }

    rules page createPage() {
      securityContext.loggedIn
      // make web dependent
    }

    rules template pageOperationsMenuItems(p : Page) {
      securityContext.loggedIn
    }

  }
