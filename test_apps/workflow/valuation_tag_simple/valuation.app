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
imports valuationprocedures
imports layout
imports style
//imports testsidebar
      
  
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
  
  define page initTest() {
    init {
      securityContext.principal := userAdmin;
      securityContext.loggedIn := true;
      initValuation1.bookValuation := BookValuationProcedureStatus{};
      initValuation1.bookValuation.v := initValuation1;
      initValuation1.bookValuation.persist();
      initValuation1.persist();
      initValuation1.bookValuation.enable();
      
      initValuation2.bookValuation := BookValuationProcedureStatus{};
      initValuation2.bookValuation.v := initValuation1;
      initValuation2.bookValuation.persist();
      initValuation2.persist();
      initValuation2.bookValuation.enable();
    }
    main()
    define body() {
      section { navigate(valuation(initValuation1)){"Ga naar de OBrien street"} }
    }
  }
  