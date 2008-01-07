module templates

section RBAC templates

  define main() {
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
  define footer(){navigate("WebDSL", url("http://www.webdsl.org"))}
  define top()
  {
    menubar {
      menu {
        menuheader{"View Documents"}
        menuspacer{}
        for(d:Document)
        {
          menuitem{navigate(document(d)){output(d.title)}}
        }
      }
      menu {
        menuheader{"Edit Documents"}
        for(d:Document)
        {
          menuitem{navigate(editDocument(d)){output(d.title)}}
        }
      }
      menu {
        menuheader{navigate(formwithtemplates()){"Form with templates"}}
      }
      menu {
        menuheader{navigate(formwithfor()){"Form with for loop"}}
      }
      menu {
        menuheader{navigate(formwithforall()){"Form with for all"}}
      }
      menu {
        menuheader{navigate(formwithfunctioncall()){"Form with function call"}}
      }
      menu {
        menuheader{"Edit Documents navigate in for"}
        menuitem
        {
          for(d:Document)
          {
            navigate(editDocument(d)){output(d.title)}
          }
        }
      }
      //menu {
      //  menuheader{"Edit Documents navigate in nested for"}
      //  menuitem
      //  {
      //    for(d:Document)
      //    {
      //      for(d1:Document in thedoc.doclist)
      //      {
      //        if(d=d1)
      //        {
      //          navigate(editDocument(d)){output(d.title)}
      //        }    
      //      }
      //    }
      //  }
      //}
    } 
  }
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
      
      listitem { navigate(stress()){"stress test"} }
    }
  }