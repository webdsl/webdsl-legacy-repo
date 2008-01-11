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
    for(c:Conference)
    {
      menu
      {
        menuheader{ navigate(conference(c)){output(c.name)} }
        menuitem{navigate(editConference(c)){"edit"}}
        menuitem{navigate(managePapers(c)){"manage papers"}}
        menuitem { navigate(addConflict(c)){output("add conflict")} } 
        menuitem { navigate(manageConflicts(c)){output("manage conflicts")} }
      }
    }

    menu
    {
      menuheader { navigate(createConference()){output("new conference")} } 
    }
    menu
    {
      menuheader { navigate(createUser()){output("new user")} } 
    }
    menu
    {
      menuheader { navigate(submitPaper()){output("submit paper")} } 
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
    
  }