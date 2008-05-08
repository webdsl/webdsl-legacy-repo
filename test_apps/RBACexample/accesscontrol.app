module accesscontrol

section RBAC AccessControl

  extend entity User
  {
    roles :: Set<Role>
  }

  extend session securityContext
  {
    activeRole -> Role
  }

  entity Role
  {
    name :: String
  }

  globals
  {
    var admin : Role := Role
    {
      name := "Administrator"
    };
    var editor : Role := Role
    {
      name := "Editor"
    };
    var viewer : Role := Role
    {
      name := "Viewer"
    };
  }

  access control rules
  {
    predicate mayViewDocument (r:Role) {
      r = viewer || r = editor
    }

    predicate mayEditDocument (r:Role) {
      r = editor
    }

    predicate mayEditRoles(r:Role) {
      r = admin
    }

    principal is User with credentials name

    pointcut openSections()
    {
      page home(),
      template sidebar(),
      template roleActivation(),
      template *(*),
      function *(*)
      //,function userLogin(*),
     // function userLogout()
    }
    
    rules pointcut openSections()
    {
      true
    }



    rules page viewDocument(*)
    {
      mayViewDocument(securityContext.activeRole)
    }

    rules page createDocument()
    {
      mayEditDocument(securityContext.activeRole)
    }

    rules page editDocument(*)
    {
      mayEditDocument(securityContext.activeRole)
    }

    rules page editUserRoles(*)
    {
      mayEditRoles(securityContext.activeRole)
    }
    
    rules page viewUser(u:User)
    {
      u=securityContext.principal||mayEditRoles(securityContext.activeRole)
    }


    rules function activateRole(r11:Role)
    {
      r11 in securityContext.principal.roles
    }
    
    rule template roleActivation()
    {
      true
      rule action activate(r11:Role)
      {
        r11 in securityContext.principal.roles
      }
    }
  }
section global funcs

globals
{
  function activateRole(r:Role):Int
  {
    securityContext.activeRole := r;
    return 0;
  }
  
  function userLogin(u:User):Int
  {
    securityContext.principal := u;
    securityContext.loggedIn := true;
    securityContext.activeRole := null;
    return 0;
  }
  
  function userLogout():Int
  {
    securityContext.principal := null;
    securityContext.loggedIn := false;
    securityContext.activeRole := null;
    return 0;
  }
}


//administration
  define page editUserRoles(u:User)
  {

    action save(u:User)
    {
      u.save();
      return home();
    }

    main()
    define body()
    {
      form
      {
        input(u.roles)

        action("save",save(u))
      }

    }
  }
  

// interface
  define roleActivation()
  {
    form
    {
      list
      {
        for(r:Role)// in securityContext.principal.rolesList)
        {
          listitem { actionLink(r.name,activate(r)) }
        }
      }
      action activate(r:Role)
      {
        activateRole(r); //infer from rules on the function
      }
    }
  }