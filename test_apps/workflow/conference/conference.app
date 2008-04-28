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


section main

  define page home() {
    main()
    define body() {
      section() {
        header(){"Home page"}
      }
    }
  }
  
  define page klusConference() {
    main()
    define body() {
      var conference : Conference := Conference{}
      derive createPage from conference for (name, chairs)
    }
  }
  
  define page conference(c : Conference) {
    main()
    define contextSidebar() {
      conferenceOperations(c)
    }
    define body() {
      header{"Conference " output(c)}
      table {
        derive viewRows from c
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
