module templates

section main template.

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
  
  define top() {
    block("header") {}
    block("menubar") {
      menubar {
        conferencesMenu()
      }
    }
  }

section basic page elements.

  define sidebar() {
    list {
      contextSidebar()
    }
    
    list {
    }
  }
  
  define contextSidebar() { }
  
  define conferenceSidebar(c : Conference) {
    listitem { navigate(conference(c)) { "Conference home" } }
    listitem { navigate(submitPaper(c)) { "Submit paper" } }      
  }
  
  define footer() {
    "generated with "
    navigate("WebDSL", url("http://www.webdsl.org")) " and "
    navigate("Stratego/XT", url("http://www.strategoxt.org"))
  }
  
section menus.

  globals {
    function getTaskCount() : Int { // Far from ideal, but couldn't get it to work with a list comprehension
      var ts : Set<ConferenceTask> := Set<ConferenceTask>();
      for(t : ConferenceTask in securityContext.principal.tasks where !t.completed) {
        ts.add(t);
      }
      return ts.length;
    }
  }
  
  define conferencesMenu() {
    form {
      menu {
        menuheader { navigate(home()) { "Home" } }
      }
      menu {
        menuheader { "Conferences" }
        for(c : Conference) {
          menuitem { output(c) }
        }
        menuitem { navigate(createConference()) { "Create conference"} }
      }
      menu {
        menuheader { "User" }
        if(securityContext.loggedIn) {
          menuitem { navigate(tasks(securityContext.principal)) { "Tasks (" output(getTaskCount()) ")" } }
          menuitem { navigate(selectActiveRoles()) { "Select active roles" } }
          menuitem { actionLink("Sign Off", signoff()) }
          action signoff() {
            securityContext.loggedIn := false;
            securityContext.principal := null;
            return home();
          }
        }
        if(!securityContext.loggedIn) {
          menuitem { navigate(signin()) { "Sign in" } }
          menuitem { navigate(register()) { "Register" } }
        }
      }
    }
  }

  

