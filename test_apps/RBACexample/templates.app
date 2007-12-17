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
        securityContext.principal := u;
        securityContext.loggedIn := true;
        securityContext.activeRole := null;
        return home();
      }
      action logout()
      {
        securityContext.principal := null;
        securityContext.loggedIn := false;
        securityContext.activeRole := null;
        return home();
      }
    }

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