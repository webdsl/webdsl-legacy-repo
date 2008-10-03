application org.webdsl.testProcess


description {
  A workflow example for a personal development plan workflow
}

imports templates
imports data
imports init
imports entitypages
imports ac
imports valuationpages
imports valuationviewpages
imports valuationeditpages
imports valuationrequestviewpages
imports valuationrequesteditpages
imports layout
imports style
//imports testsidebar
      
  
section pages

  define body() {}

  define page home() {
    main()
    define body() {
      header { "Integrated Property Services" }
      
      section {
        text("Welcome ")
        if (securityContext.principal != null) { output(securityContext.principal) }
      }
      
      section {
        if (securityContext.principal == null) { 
          text("Login als: ")
          list {
            listitem{ navigate(loginAs(userRuben)){"Ruben"} }
            listitem{ navigate(loginAs(userLiming)){"Liming"} }
          }
        }        
      }
    }
  }
  
  define page loginAs(u : User) {
    init {
      securityContext.principal := u;
      securityContext.loggedIn := true;
    }
    main()
    define body() {
      section {text("Logged in as ") output(u)}
    }
  }
  