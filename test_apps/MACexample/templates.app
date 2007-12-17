module templates

section MAC templates
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
    menu
    {
      menuheader{"View documents"}
    
      for(d:Document)
      {
        menuitem{ navigate(viewDocument(d)) { output(d.title) } }
      }
    }    
    menu{menuheader { navigate(createDocument()){output("new document")} }}  
    menu
    {
      menuheader{"View missions"}
      
      for(o:Mission)
      {
        menuitem{ navigate(viewMission(o)) { output(o.title) } }
      }
    }
    menu{menuheader { navigate(createMission()){output("new mission")} }}
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