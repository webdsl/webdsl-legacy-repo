module accesscontrol

section GTRBAC AccessControl

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
    name :: String (name)
    status -> RoleStatus
    viewableDocuments -> Set<Document>
    editableDocuments -> Set<Document>
    mayEditUserRoleAssignments -> Bool
    mayEditRolePermissionAssignments -> Bool
    mayChangeRoleStatus -> Bool
  }
  
  entity RoleStatus
  {
    description :: String (name)
  }
  
  globals
  {
    var enabled : RoleStatus := RoleStatus
    {
      description := "enabled"
    };    
    var disabled : RoleStatus := RoleStatus
    {
      description := "disabled"
    };
  }

  globals
  {
    var admin : Role := Role
    {
      name := "Administrator"
      mayEditUserRoleAssignments := true
      mayEditRolePermissionAssignments := true
      mayChangeRoleStatus := true
    };
    var editor : Role := Role
    {
      name := "Editor"
      viewableDocuments := {d0,d1,d3}
      editableDocuments := {d0,d1}
      mayEditUserRoleAssignments := false
      mayEditRolePermissionAssignments := false
      mayChangeRoleStatus := false
    };
    var viewer : Role := Role
    {
      name := "Viewer"
      viewableDocuments := {d0,d1,d3}
      mayEditUserRoleAssignments := false
      mayEditRolePermissionAssignments := false
      mayChangeRoleStatus := false
    };
  }

  access control rules
  {
    predicate mayViewDocument (r:Role,d:Document) {
      d in r.viewableDocuments
    }

    predicate mayEditDocument (r:Role,d:Document) {
      d in r.editableDocuments
    }

    predicate mayEditRoles(r:Role) {
      r.mayEditUserRoleAssignments
    }
    
    predicate mayEditRolePermission(r:Role)
    {
      r.mayEditRolePermissionAssignments
    }
    
    predicate mayChangeRoleStatus(r:Role)
    {
      r.mayChangeRoleStatus
    }

    principal is User with credentials name

    pointcut openSections()
    {
      page home(),
      template sidebar(),
      template roleActivation(),
      page role(*)
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
      true
    }

    rules page editDocument(d:Document)
    {
      mayEditDocument(securityContext.activeRole,d)
    }

    rules page editUserRoles(*)
    {
      mayEditRoles(securityContext.activeRole)
    }
    
    rules page editRolePermissions(*)
    {
      mayEditRolePermission(securityContext.activeRole)
    }
    
    rules page changeRoleStatus(*)
    {
      mayChangeRoleStatus(securityContext.activeRole)
    }
    
    rules page viewUser(u:User)
    {
      true
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
  
  define page editRolePermissions(r:Role)
  {

    action save(r:Role)
    {
      r.save();
      return home();
    }

    main()
    define body()
    {
      form
      {
        header{"Edit role permissions"}
        table
        {
          row{"Role name: " output(r.name)}
          row{"Viewable documents: " input(r.viewableDocuments)}
          row{"Editable Documents: " input(r.editableDocuments)}
          row{"May edit user-role assignments: " input(r.mayEditUserRoleAssignments)}
          row{"May edit role-permission assignments: " input(r.mayEditRolePermissionAssignments)}
          row{"May change role status: " input(r.mayChangeRoleStatus)}
        }
        action("save",save(r))
      }

    }
  }  
  
  define page changeRoleStatus(r:Role)
  {

    action save(r:Role)
    {
      r.save();
      return home();
    }

    main()
    define body()
    {
      form
      {
        header{"Edit role status"}
        table
        {
          row{"Role name: " output(r.name)}
          row{"Status: " input(r.status)}
        }
        action("save",save(r))
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