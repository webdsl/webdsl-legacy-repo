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
      
      section {
        text("Welcome ")
        if (securityContext.principal != null) { 
          output(securityContext.principal) 
          block {text("Heeft booking rights: ") output(securityContext.principal.hasBookingRights())}
        }
        
      }
      
      section {
        if (securityContext.principal == null) { 
          text("Login als: ")
          list {
            listitem{ navigate(loginAs(userRuben)){"Ruben"} }
            listitem{ navigate(loginAs(userLiming)){"Liming"} }
            listitem{ navigate(loginAs(userAdmin)){"Admin"} }
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
  
  define page initTest() {
    init {
      securityContext.principal := userAdmin;
      securityContext.loggedIn := true;
      initValuation1.bookValuation := BookValuationProcedureStatus{};
      initValuation1.bookValuation.v := initValuation1;
      initValuation1.bookValuation.persist();
      initValuation1.persist();
      initValuation1.bookValuation.enable();
    }
    main()
    define body() {
      section { navigate(valuation(initValuation1)){"Ga naar de OBrien street"} }
    }
  }
  