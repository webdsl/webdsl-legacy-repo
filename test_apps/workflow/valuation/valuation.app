application org.webdsl.valuation

description {
  A workflow example for a personal development plan workflow
}

imports templates
imports data
imports stringselectdata
imports init
imports entitypages
imports ac
imports valuationpages
imports valuationrequestviewpages
imports valuationprocedures
imports layout
imports style
  
section pages

  define body() {}

  define page home() {
    main()
    define body() {
      header { "Integrated Property Services" }
      
      par {
        if (securityContext.principal != null) { 
          text("Welcome ")
          output(securityContext.principal) 
        } else {
          text("Login als: ")
/*          list {
            listitem{ navigate(loginAs(userValuer1)){"Valuer 1"} }
            listitem{ navigate(loginAs(userValuer2)){"Valuer 2"} }
            listitem{ navigate(loginAs(userAdmin)){"Admin"} }
            listitem{ navigate(loginAs(userManager)){"Manager"} }
          }
*/        }
      }
      
      bookValuationTasks()
    }
  }
  
  define page unbooked() {
    main()
    define body() {
      header { "Unbooked Valuations" }
      bookValuationTasks()
    }
  }
  
  define page loginAs(u : User) {
    init {
      securityContext.principal := u;
      securityContext.loggedIn := true;
      goto home();
    }
    main()
    define body() {
      section {text("Logged in as ") output(u)}
    }
  }
  
  define page viewValuationRequestFull(v : ValuationRequest) {
    derive viewPage from v
  }
  
