module accesscontrol

section RBAC AccessControl

  extend entity User
  {
    roles :: Set<ProjectRole>
  }

  extend session securityContext
  {
    activeProjectRole -> ProjectRole
  }

  entity Role
  {
    name :: String
  }
  
  entity ProjectRole
  {
    role -> Role
    project -> Project
    description :: String (name) := project.name + role.name
  }
  
  globals
  {
    function retrieveProjectRole(p:Project,r:Role):ProjectRole
    {
      var proles : List<ProjectRole> :=
        select pr from ProjectRole as pr
        where (pr._role = ~r) and (pr._project = ~p);
      
      for (pr : ProjectRole in proles ) {
        if (pr.role=r && pr.project=p) {
          return pr;
        }
      }
      
      var newpr : ProjectRole := ProjectRole
      {
        role := r
        project := p
      };
      newpr.save();
      return newpr;
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
  }

  access control rules
  {
    predicate mayViewDocument (pr:ProjectRole,d:Document) {
      (viewer = pr.role || editor = pr.role) && d.project = pr.project
    }

    predicate mayEditDocument (pr:ProjectRole,d:Document) {
      editor = pr.role && d.project = pr.project
    }

    predicate mayEditRoles(pr:ProjectRole,p:Project) {
      admin = pr.role && p = pr.project
    }

    principal is User with credentials name

    pointcut openSections()
    {
      page home(),
      template sidebar(),
      template roleActivation(),
      page viewUser(*),
      page user(*),
      page project(*),
      page projectRole(*),
      page role(*)
    }

    
    rules pointcut openSections()
    {
      true
    }



    rules page viewDocument(d:Document)
    {
      mayViewDocument(securityContext.activeProjectRole,d)
    }

    rules page createDocument()
    {
      securityContext.activeProjectRole.role = editor
      rules action save(d:Document)
      {
        mayEditDocument(securityContext.activeProjectRole,d)
      }
    }

    rules page editDocument(d:Document)
    {
      mayEditDocument(securityContext.activeProjectRole,d)
    }


    rules function activateRole(pr:ProjectRole)
    {
      pr in securityContext.principal.roles
    }
    
    rules page editUserRoles(u:User)
    {
      mayEditRoles(securityContext.activeProjectRole,securityContext.activeProjectRole.project)
      rules action addRole(u:User,r:Role,p:Project)
      {
        mayEditRoles(securityContext.activeProjectRole,p) 
        && !(retrieveProjectRole(p,r) in u.roles)
      }
      rules action removeRole(u:User,r:Role,p:Project)
      {
        mayEditRoles(securityContext.activeProjectRole,p) 
        && retrieveProjectRole(p,r) in u.roles
      }
    }
  }


  globals
  {  
    function activateRole(pr:ProjectRole):Int
    {
      securityContext.activeProjectRole := pr;
      return 0;
    }
    
    function userLogin(u:User):Int
    {
      securityContext.principal := u;
      securityContext.loggedIn := true;
      securityContext.activeProjectRole := null;
      return 0;
    }
    
    function userLogout():Int
    {
      securityContext.principal := null;
      securityContext.loggedIn := false;
      securityContext.activeProjectRole := null;
      return 0;
    }
  }
  
  
  // interface
  define roleActivation()
  {
    form
    {
      list
      {
        for(r:ProjectRole in securityContext.principal.rolesList)
        {
          listitem { actionLink(r.project.name + r.role.name,activate(r )) }         
        }
      }
      action activate(pr:ProjectRole)
      {
        activateRole(pr); //infer from rules on the function
      }
    }
  }
  
  define page editUserRoles(u:User)
  {      
    action addRole(u:User,r:Role,p:Project)
    {
      u.roles.add(retrieveProjectRole(p,r));
    }
    action removeRole(u:User,r:Role,p:Project)
    {
      u.roles.remove(retrieveProjectRole(p,r));
    }
    main()
    define body()
    {
      form
      {
        par()
        {
          "User: "
          output(u) 
        }
        for(project:Project)
        {
          par()
          {
            "Project: "
            output(project.name)
  
            for(role:Role) // where not retrieveProjectRole(r,p) in u.projectroles)
            {
              par(){actionLink("disable " + role.name + " role",removeRole(u,role,project))}
              par(){actionLink("enable " + role.name + " role",addRole(u,role,project))}
            }
          }
        }
      }
    }
  }
