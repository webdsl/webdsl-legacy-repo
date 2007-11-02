application org.webdsl.socialnetwork

description {
   This application is a simple social network.
}

imports authentication
imports accesscontrol
imports templates
imports init
imports datamodel

section home.

  define page home() {
    main()

    define body() {
      title{"Login Example"}
 
      section {
        header{"All members"}
        list { 
          for(u : User) 
          { 
            listitem{ navigate(viewUser(u)) { output(u.name) } } 
          } 
        }
      }
      
    }
  }
  
        