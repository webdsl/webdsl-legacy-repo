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

    rules page home()
    {
      true
    }

    rules template sidebar()
    {
      true
     // rules action *(*)
     // {
    //    true
    //  }
    //  rules action activate(r:Role)
    //  {
    // /   r in securityContext.principal.roles
    //  }
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
}