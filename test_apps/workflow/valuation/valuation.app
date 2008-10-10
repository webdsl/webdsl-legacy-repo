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
imports valuationrequestviewpages
imports valuationrequesteditpages
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
          list {
            listitem{ navigate(loginAs(userRuben)){"Ruben"} }
            listitem{ navigate(loginAs(userLiming)){"Liming"} }
            listitem{ navigate(loginAs(userAdmin)){"Admin"} }
            listitem{ navigate(loginAs(userManager)){"Login as Manager"} }
          }          
        }
      }
      
      bookValuationTasks()
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
  
  init {
    initValuationRequest1.bookValuation := BookValuationProcedureStatus{};
    initValuationRequest1.bookValuation.v := initValuationRequest1;
    initValuationRequest1.bookValuation.persist();
    initValuationRequest1.persist();
    initValuationRequest1.bookValuation.enable();
    
    initValuationRequest2.bookValuation := BookValuationProcedureStatus{};
    initValuationRequest2.bookValuation.v := initValuationRequest2;
    initValuationRequest2.bookValuation.persist();
    initValuationRequest2.persist();
    initValuationRequest2.bookValuation.enable();
  }
  