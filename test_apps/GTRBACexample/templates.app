module templates

section RBAC templates
  define main() 
  {
    block("top") {
      top()
    }

    block("body") {
      block("left_innerbody") {
        sidebar()
      }
      block("main_innerbody") {
        body()
      }
    }

    block("footer") {
      footer()
    }
  }

  define footer(){}
  define top() {
    block("header") {}
    block("menubar") { 
      menubar
      {
        themenu()
      }
    }
  }

  define themenu()
  {    
    for(d:Document)
    {
      menu
      {
        menuheader{ navigate(viewDocument(d)){output(d.title)} }
        menuitem{ navigate(viewDocument(d)) { "view" } }
        menuitem{ navigate(editDocument(d)) { "edit" } }
      }
    }
    
    for(u:User)
    {
      menu
      {
        menuheader{navigate(viewUser(u)) { output(u.name) }}
        menuitem{ navigate(editUserRoles(u)) { "edit roles" } }
      }
    }
    menu{menuheader { navigate(createDocument()){output("create new document")} } }
    
    for(r:Role)
    {
      menu
      {
        menuheader{navigate(role(r)) { output(r.name) }}
        menuitem{ navigate(editRolePermissions(r)) { "edit role permissions" } }
        menuitem{ navigate(changeRoleStatus(r)) { "edit role status" } }
      }
    }
  }
  define body(){}
  define sidebar()
  {
    form
    {
      "Welcome " output(securityContext.principal.name)
      list
      {
        listitem { navigate(home()){output("home")} }

        listitem { action("logout",logout()) }

        for(u:User)
        {
          listitem { action(u.name,login(u)) }
        }
      }

      action login(u:User)
      {
        userLogin(u);
        return home();
      }
      action logout()
      {
        userLogout();
        return home();
      }
    }

    roleActivation()
    
  }