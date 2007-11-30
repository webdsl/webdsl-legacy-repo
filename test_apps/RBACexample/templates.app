module templates

section RBAC templates

  define main() {
    div("outersidebar") {
      sidebar()
    }
    div("outerbody") {
      div("menubar") {
        menu()
      }
      body()
      footer()
    }
  }
  define footer(){}
  define menu(){}
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
        return home();
      }
      action logout()
      {
        securityContext.principal := null;
        securityContext.loggedIn := false;
        return home();
      }
    }
    div("loggedIn")
    {
      form
      {
        list
        {
          for(r:Role in securityContext.principal.rolesList)
          {
            listitem { action(r.name,activate(r)) }
          }
        }
        action activate(r:Role)
        {
          activateRole(r);
          return home();
        }
      }
    }
  }