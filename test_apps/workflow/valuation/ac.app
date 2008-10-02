module ac

section functions

  globals {
     
  /**
   * Valuations
   */
   
    // for editing all valuations, authorization is needed
    function canEditAllValuations() : Bool {
      for (aut : Authorization where aut.right == "allValuations") {
        if (aut.user == securityContext.principal) {
          return true;
        }
      }
      return false;
    }

    // valuers can only edit their own valuations
    function canEditValuation(v : Valuation) : Bool {
      return (canEditAllValuations() || (v.valuer != null && v.valuer.user == securityContext.principal));
    }
    
    // valuers can view all valuations
    function canViewAllValuations() : Bool {
      return (securityContext.principal != null && securityContext.principal.isValuer());
    }
    
    function canViewValuation(v : Valuation) : Bool {
      return (canViewAllValuations());// || securityContext.principal.isValuer());
    }
    
    
  /**
   * ValuationRequests
   */
     
    // for editing all valuation requests, authorization is needed
    function canEditAllValuationRequests() : Bool {
      for (aut : Authorization where aut.right == "allValuationRequests") {
        if (aut.user == securityContext.principal) {
          return true;
        }
      }
      return false;
    }
    
    function canEditValuationRequest(r : ValuationRequest) : Bool {
      return (canEditAllValuationRequests());
    }
  }

access control rules 
  
  pointcut valuationEditPages(v : Valuation) {
    page editValuationProperty(v),
    page editValuationMainBuilding(v),
    page editValuationRisk(v),
    page editValuationLand(v),
    page editValuationSales(v)
  }
  
  pointcut valuationRequestEditPages(r : ValuationRequest) {
    page editValuationRequestDetails(r),
    page editValuationRequestBooking(r),
    page editValuationRequestQuote(r)
  }
  
  rule pointcut valuationEditPages(v : Valuation) {
    canEditValuation(v)
  }

  rule pointcut valuationRequestEditPages(r : ValuationRequest) {
    canEditValuationRequest(r)
  }

  rule page *(*) {
    true
  }
  rule action *(*) {
    true
  }
  rule template *(*) {
    true
  }

section pages

  principal is User with credentials username, password

  define page signin() {
    main()
    title{"Log in"}
    define body() {
      var username : String;
      var password : Secret;
      form { 
        table {
          row{ "Username: " input(username) }
          row{ "Password: " input(password) }
          row{ action("Sign in", dosignin()) "" }
        }
        action dosignin() {
          for (us : User where us.username == username) {
            if (us.password.check(password)) {
              securityContext.principal := us;
              securityContext.loggedIn := true;
              return home();
            }
          }
          securityContext.loggedIn := false;
          return error("Wrong combination of username and password");
        }
      }
    }
  }
  
  define page signout() {
    init {
      securityContext.principal := null;
      securityContext.loggedIn := false;
    }
    main()
    define body() {
      header{"Signed out"}
      text("You have been signed out.")
    }
  }
  
  define page error(msg : String) {
    main()
    title{"Error"}
    define body() {
      header{"Error"}
      output(msg)
    }
  }

  define page message(msg : String) {
    main()
    title{"Message"}
    define body() {
      header{"Message"}
      section() {
        output(msg)
      }
    }
  }
