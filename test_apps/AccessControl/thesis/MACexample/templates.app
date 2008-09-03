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
    //menu{menuheader { navigate(createDocument()){output("new document")} }}  
    //menu
    //{
    //  menuheader{"View missions"}
    //  
    //  for(o:Mission)
    //  {
    //    menuitem{ navigate(viewMission(o)) { output(o.title) } }
    //  }
    //}
    //menu{menuheader { navigate(createMission()){output("new mission")} }}
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
        //bugged
        //for(cl:Int in [0,1,2,3])
        //{
        //  listitem{ actionLink("Activate CL: " + output(cl), activateCL(cl)) }
        //}
        
        //quick fix to work around bugs with Int
        var do0:Document := Document{ classification := 0 };
        var do1:Document := Document{ classification := 1 };
        var do2:Document := Document{ classification := 2 };
        var do3:Document := Document{ classification := 3 };
  
        
        listitem{ actionLink("Activate CL: 0", activateCL(do0.classification)) }
        listitem{ actionLink("Activate CL: 1", activateCL(do1.classification)) }
        listitem{ actionLink("Activate CL: 2", activateCL(do2.classification)) }
        listitem{ actionLink("Activate CL: 3", activateCL(do3.classification)) }
      }
      action activateCL(i:Int)
      {
        securityContext.clearance := i;
        //return home();
      }
      action login(u:User)
      {
        securityContext.principal := u;
        securityContext.loggedIn := true;
        securityContext.clearance := null;
        //return home();
      }
      action logout()
      {
        securityContext.principal := null;
        securityContext.loggedIn := false;
        securityContext.clearance := null;
        //return home();
      }
    }
  }