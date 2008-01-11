module accesscontrol

section RBAC with role hierarchies

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
    seniorRoles -> Set<Role>
    juniorRoles -> Set<Role>
    viewEditPermission -> Set<Document>
  }

  globals
  {
    var admin : Role := Role
    {
      name := "Administrator"
      viewEditPermission := {d0}
    };
    var editor : Role := Role
    {
      name := "Editor"
    };
    var viewer1 : Role := Role
    {
      name := "Viewer1"
      seniorRoles := {viewer2}
      viewEditPermission := {d1}
    };
    var viewer2 : Role := Role
    {
      name := "Viewer2"
      seniorRoles := {viewer3}
      juniorRoles := {viewer1}
      viewEditPermission := {d2}
    };
    var viewer3 : Role := Role
    {
      name := "Viewer3"
      juniorRoles := {viewer2}
      viewEditPermission := {d3}
    };    
  }

  access control rules
  {
    predicate mayViewDocument (r:Role,d:Document) {
      d in r.viewEditPermission || true in [mayViewDocument(role,d) for (role: Role in r.juniorRoles)]
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
      template roleActivation()
      //,function userLogin(*),
     // function userLogout()
    }
    
    rules pointcut openSections()
    {
      true
    }



    rules page viewDocument(d:Document)
    {
      mayViewDocument(securityContext.activeRole,d)
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


    rules function activateRole(r:Role)
    {
      r in securityContext.principal.roles
    }
  }


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