module templates

section DAC templates
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
      menu{
        menuheader{navigate(viewDocument(d)) { output(d.title)}}
        menuitem{ navigate(viewDocument(d)) { "view" } }
        menuitem{ navigate(editDocument(d)) { "edit" } }
        menuitem{ navigate(editGrants(d)) { "view/edit access" } }
        menuitem{ navigate(editGrantingRights(d)) { "granting" } }
      }
    }
    menu{menuheader{navigate(createDocument()){output("create new")}}}
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
        return home();
      }
      action logout()
      {
        securityContext.principal := null;
        securityContext.loggedIn := false;
        return home();
      }
    }
  }