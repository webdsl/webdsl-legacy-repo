application com.example.conference

description {
  Conference application
}

imports datamodel
imports templates
imports utils
imports initialize
imports accesscontrol
imports workflows/conference
imports workflows/pcinvitation


section main

  define page home() {
    main()
    define body() {
      section() {
        header(){"Home page"}
        form {
          action("Start new conference", start())
          action start() {
            c : Conference := startNewConferenceWorkflow();
            return c;
          }
        }
      }
    }
  }
  

  
/*
operations conference creation

  operation conferences(n: Nix) {
    who { securityContext.principal.isAdmin }
    when { true }
    view {
      title{"Create conference"}
      main()
      define contextSidebar() {
        for (c : Conference) {
          menuitem{output(c)}
        }
      }
      define body() {
        navigate(createConference()){"Create a conference"}
      }
    }
  }
  */
