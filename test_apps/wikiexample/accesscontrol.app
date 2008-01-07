module accesscontrol

section wiki AccessControl

  access control rules
  {
    principal is User with credentials name

    predicate mayEditPages()
    {
      securityContext.loggedIn
    }

    pointcut openSections()
    {
      page home(),
      template sidebar(),
      page user(*),
      page editUser(*),
      page createUser(),
      page page(*)
    }

    rules pointcut openSections()
    {
      true
    }
    
    pointcut pageEditing(p:Page)
    {
      page editPage(p),
      page createPage()
    }
    
    rules pointcut pageEditing(p:Page)
    {
      mayEditPages()
    }
    
  }
  
  
  globals
  {  
    
    function userLogin(u:User):Int
    {
      securityContext.principal := u;
      securityContext.loggedIn := true;

      return 0;
    }
    
    function userLogout():Int
    {
      securityContext.principal := null;
      securityContext.loggedIn := false;
    
      return 0;
    }
  }