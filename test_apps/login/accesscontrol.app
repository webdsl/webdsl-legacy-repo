module accesscontrol

section ac stuff.
  access control rules 
  {
    principal is User with credentials username,password
    
    rules template div("loggedIn")
    {
      securityContext.loggedIn
    }
    
    rules template div("notLoggedIn")
    {
      !securityContext.loggedIn
    }
  
    rules template login() { 
      !securityContext.loggedIn
    }
    rules template logout() { 
      securityContext.loggedIn
    }
  
    rules page register()
    {
      true
    }
    
    rules page viewUser(*)
    {
      true
    }
    
    rules page home(*)
    {
      true
    }
  }
