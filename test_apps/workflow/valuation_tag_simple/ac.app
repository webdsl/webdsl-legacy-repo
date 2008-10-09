module ac

section functions

  globals {
     
  /**
   * Valuations
   */
   
    // for editing all valuations, authorization is needed
    function canEditAllValuations() : Bool {
      for (aut : Authorization where aut.right == "editValuations") {
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
      for (aut : Authorization where aut.right == "viewValuations") {
        if (aut.user == securityContext.principal) {
          return true;
        }
      }
      return (canEditAllValuations() || (securityContext.principal != null && securityContext.principal.isValuer()));
    }
    
    function canViewValuation(v : Valuation) : Bool {
      return (canViewAllValuations());// || securityContext.principal.isValuer());
    }
    
    
  /**
   * ValuationRequests
   */
     
   function canCreateValuationRequest() : Bool {
     for (aut : Authorization where aut.right == "createValuation") {
       if (aut.user == securityContext.principal) {
         return true;
       }
     }
     return false;
   }
   
    // for editing all valuation requests, authorization is needed
    function canEditAllValuationRequests() : Bool {
      for (aut : Authorization where aut.right == "editValuationRequests") {
        if (aut.user == securityContext.principal) {
          return true;
        }
      }
      return false;
    }
    
    function canEditValuationRequest(r : ValuationRequest) : Bool {
      return (canEditAllValuationRequests());
    }
    
    // valuers can view all valuationrequests
    function canViewAllValuationRequests() : Bool {
      for (aut : Authorization where aut.right == "viewValuationRequests") {
        if (aut.user == securityContext.principal) {
          return true;
        }
      }
      return (canEditAllValuationRequests() || (securityContext.principal != null && securityContext.principal.isValuer()));
    }
    
    function canViewValuationRequest(r : ValuationRequest) : Bool {
      return (canViewAllValuationRequests());
    }
  }

access control rules 
  
  pointcut valuationViewPages(v : Valuation) {
    page valuation(v),
    page valuationMainBuilding(v),
    page valuationRisk(v),
    page valuationLand(v),
    page valuationSales(v)
  }
  
  pointcut valuationEditPages(v : Valuation) {
    page editValuationProperty(v),
    page editValuationMainBuilding(v),
    page editValuationRisk(v),
    page editValuationLand(v),
    page editValuationSales(v)
  }
  
  pointcut valuationRequestViewPages(r : ValuationRequest) {
    page valuationRequest(r),
    page valuationRequestBooking(r),
    page valuationRequestQuote(r)
  }
  
  pointcut valuationRequestEditPages(r : ValuationRequest) {
    page editValuationRequest(r),
    page editValuationRequestBooking(r),
    page editValuationRequestQuote(r)
  }
  
  rule pointcut valuationViewPages(v : Valuation) {
    canViewValuation(v)
  }
  
  rule pointcut valuationEditPages(v : Valuation) {
    canEditValuation(v)
  }

  rule pointcut valuationRequestViewPages(r : ValuationRequest) {
    canViewValuationRequest(r)
  }

  rule pointcut valuationRequestEditPages(r : ValuationRequest) {
    canEditValuationRequest(r)
  }

  rule page newValuationRequest() {
    canCreateValuationRequest()
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
