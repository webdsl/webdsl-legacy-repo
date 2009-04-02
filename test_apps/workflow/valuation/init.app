module init

section user data


    var userValuer1 : User := User {
      username := "valuer1",
      name     := "Valuer 1",
      password := "secret"
    };
    var userValuer2 : User := User {
      username := "valuer2",
      name     := "Valuer 2",
      password := "secret"
    };
    var userAdmin : User := User {
      username := "admin",
      name     := "Administrative Employee",
      password := "secret"
    };
    var userManager : User := User {
      username := "manager",
      name     := "Manager",
      password := "secret"
    };
    
    // admin
    var initAuthorization1 : Authorization := Authorization { right := "viewValuations" user := userAdmin };
    var initAuthorization2 : Authorization := Authorization { right := "booking" user := userAdmin };
    var initAuthorization3 : Authorization := Authorization { right := "createValuation" user := userAdmin };
    var initAuthorization4 : Authorization := Authorization { right := "editValuationRequests" user := userAdmin };
    var initAuthorization9 : Authorization := Authorization { right := "sendValuations" user := userAdmin };

    // manager
    var initAuthorization5 : Authorization := Authorization { right := "editValuations" user := userManager };
    var initAuthorization6 : Authorization := Authorization { right := "createValuation" user := userManager };
    var initAuthorization7 : Authorization := Authorization { right := "booking" user := userManager };
    var initAuthorization8 : Authorization := Authorization { right := "editValuationRequests" user := userManager };
    var initAuthorization10 : Authorization := Authorization { right := "sendValuations" user := userManager };
    var initAuthorization11 : Authorization := Authorization { right := "editApprovalValuations" user := userManager };
    var initAuthorization12 : Authorization := Authorization { right := "approveValuations" user := userManager };

  
  globals {
    var initClient1 : Client := Client { name := "Topline Foods" };
    var initClient2 : Client := Client { name := "Philips" };
    var initClient3 : Client := Client { name := "Nokia" };
  }
  
  globals {
    var initBroker1 : Broker := Broker { name := "Barneveld Schuurman Makelaars" };
    var initBroker2 : Broker := Broker { name := "Jeltes ten Hoor" };
    var initBroker3 : Broker := Broker { name := "Rooseboom" };
  }
  
  globals {
    var initValuationRequestStatus1 : ValuationRequestStatus := ValuationRequestStatus { name := "Request Received" };
    var initValuationRequestStatus2 : ValuationRequestStatus := ValuationRequestStatus { name := "Booked" };
    var initValuationRequestStatus3 : ValuationRequestStatus := ValuationRequestStatus { name := "Pending" };
    var initValuationRequestStatus4 : ValuationRequestStatus := ValuationRequestStatus { name := "Awaiting Approval" };
    var initValuationRequestStatus5 : ValuationRequestStatus := ValuationRequestStatus { name := "Approved" };
    var initValuationRequestStatus6 : ValuationRequestStatus := ValuationRequestStatus { name := "Sent" };
  }
  
  globals {
    var valuer1 : Valuer := Valuer { user := userValuer1 };
    var valuer2 : Valuer := Valuer { user := userValuer2 };
  }
