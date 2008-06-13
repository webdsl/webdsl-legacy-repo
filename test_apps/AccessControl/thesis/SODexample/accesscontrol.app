module accesscontrol

section SOD AccessControl

  extend entity User
  {
    roles :: Set<Role>
  }

  extend session securityContext
  {
    activeRoles -> Set<Role>
  }

  entity Role
  {
    name :: String
  }
  
  //static SOD
  entity ConflictingRolesSet
  {
    conflictingroles -> Set<Role>
  } 
  entity Conflicts
  {
    conflictsets -> Set<ConflictingRolesSet>
  }
  globals
  {
    var staticconflict1 : ConflictingRolesSet := ConflictingRolesSet
    {
      conflictingroles := {staticmutex1,static2}
    };
    var staticconflict2 : ConflictingRolesSet := ConflictingRolesSet
    {
      conflictingroles := {staticmutex1,static3}
    };    
    
    var staticSOD : Conflicts := Conflicts
    {
      conflictsets := {staticconflict1,staticconflict2}
    };
    
    var dynamicconflict : ConflictingRolesSet := ConflictingRolesSet
    {
      conflictingroles := {dynamicmutex1,dynamicmutex2,dynamicmutex3}
    };
    
    var dynamicSimpleSOD : Conflicts := Conflicts
    {
      conflictsets := {dynamicconflict}
    };
    
    function rolesInStaticConflict(r1:Role, r2:Role):Bool
    {
      return rolesInConflict(r1,r2,staticSOD.conflictsets);
    }
      
    function rolesInDynamicSimpleConflict(r1:Role, r2:Role):Bool
    {
      return rolesInConflict(r1,r2,dynamicSimpleSOD.conflictsets);
    }
    
    function rolesInConflict(r1:Role, r2:Role, conflictsets:Set<ConflictingRolesSet>):Bool
    {
      for(conflictset : ConflictingRolesSet in conflictsets)
      {
        if(r1 != r2 && r1 in conflictset.conflictingroles && r2 in conflictset.conflictingroles)
        {
          return true;
        }
      }
      return false;
    }    
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
    var staticmutex1 : Role := Role
    {
      name := "staticmutex1"
    };
    var static2 : Role := Role
    {
      name := "static2"
    };
    var static3 : Role := Role
    {
      name := "static3"
    };
    var dynamicmutex1 : Role := Role
    {
      name := "dynamicmutex1"
    };
    var dynamicmutex2 : Role := Role
    {
      name := "dynamicmutex2"
    };
    var dynamicmutex3 : Role := Role
    {
      name := "dynamicmutex3"
    };
    
    
  }
  
  //history and object
  extend entity Document
  {
    didFirstAction -> User
    didSecondAction -> User
  }
  globals
  {
    function doFirst(u:User,d:Document):Bool
    {
      d.didFirstAction := u;
      return true;
    }
    
    function doSecond(u:User,d:Document):Bool
    {
      d.didSecondAction := u;
      return true;
    }
  }
  
  //operational
  extend entity User
  {
    canDoFirstAction -> Bool
    canDoSecondAction -> Bool
  }
  
  access control rules
  {
    predicate canBeActivated(r:Role, roles:Set<Role>)
    {
      !(false in [isAllowedByDynamicSimpleSOD({r,ro}) for(ro:Role in roles)])
    }
  
    predicate isAllowedByStaticSOD(roles:Set<Role>)
    {
      !(true in [true in [rolesInStaticConflict(r1,r2) for(r1:Role in roles)] for(r2:Role in roles)])
    }
    
    predicate isAllowedByDynamicSimpleSOD(roles:Set<Role>)
    {
      !(true in [true in [rolesInDynamicSimpleConflict(r1,r2) for(r1:Role in roles)] for(r2:Role in roles)])
    }
    
    predicate mayViewDocument (r:Role) {
      r = viewer || r = editor
    }

    predicate mayEditDocument (r:Role) {
      r = editor
    }

    predicate mayEditRoles(r:Role,u:User) {
      r = admin 
      //static SOD
      && isAllowedByStaticSOD(u.roles)
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
    
    rules page viewDocument(*)
    {
      true in [mayViewDocument(activerole) for (activerole:Role in securityContext.activeRoles)]
    }

    rules page createDocument()
    {
      true in [mayEditDocument(activerole) for (activerole:Role in securityContext.activeRoles)]
    }

    rules page editDocument(*)
    {
      true in [mayEditDocument(activerole) for (activerole:Role in securityContext.activeRoles)]
    }

    rules page editUserRoles(u:User)
    {
      true in [mayEditRoles(activerole,u) for (activerole:Role in securityContext.activeRoles)]
    }
    
    rules page viewUser(u:User)
    {
      true
    }


    rule page roleActivation()
    {
      true
      rule action activate(r:Role)
      {
          r in securityContext.principal.roles
          && canBeActivated(r,securityContext.activeRoles)
      }
  
    }
        
    
  }

section more globals
globals
{
  function activateRole(r:Role):Int
  {
    securityContext.activeRoles.add(r);
    return 0;
  }
  
  function userLogin(u:User):Int
  {
    securityContext.principal := u;
    securityContext.loggedIn := true;
    for (ar:Role in securityContext.activeRoles )
    {
      securityContext.activeRoles.remove(ar);
    }
    return 0;
  }
  
  function userLogout():Int
  {
    securityContext.principal := null;
    securityContext.loggedIn := false;
    for (ar:Role in securityContext.activeRoles )
    {
      securityContext.activeRoles.remove(ar);
    }
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