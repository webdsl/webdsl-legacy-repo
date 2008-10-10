module valuationprocedures

section procedures

  procedure bookValuation(v : ValuationRequest) {
    who { securityContext.principal != null && securityContext.principal.hasBookingRights() }
    when { v.status != null && v.status.name == "Request Received" }
    view {
      main()
      define local body() {
        header{text("Book Valuation")}
        form {
          table {
            row {
              block("datawidth") {
                group("Booking") {
                  groupitem { label("Valuer") { input(v.valuer) } }
                  groupitem { label("Date") { input(v.bookingDate) } }
                  groupitem { label("Time") { input(v.bookingTime) } }
                  groupitem { label("Contact"){ input(v.bookingContact) } }
                  groupitem { label("Phone"){ input(v.bookingPhone) } }
                  groupitem { label("Notes") { input(v.bookingNotes) } } 
                }
              }
              row { action("Book Valuation", do()) }
            }
          }
        }
      }
    }
    do { 
      v.status := initValuationRequestStatus2; 
      v.persist();
      v.persist();
    }
  }
  
  function unbookedValuationsExist() : Bool {
    var unbookedValuations : Bool := false;
    for (v : ValuationRequest) {
      if (v.bookValuation != null && v.bookValuation.isEnabled) {
        unbookedValuations := true;
      }
    }
    return unbookedValuations;
  }
  
  function viewBookValuationLink(v : ValuationRequest) : Bool {
    return (
      valuationRequestHasProcedures(v) && 
      securityContext.principal != null && 
      securityContext.principal.hasBookingRights() && 
      v.bookValuation != null && 
      v.bookValuation.isEnabled
    );
  }
  
  define bookValuationTasks() {
    if (securityContext.principal != null && securityContext.principal.hasBookingRights() && unbookedValuationsExist()) {
      par {
        section("Booking tasks") {
          list {
            for (v : ValuationRequest) {
              if (valuationRequestHasProcedures(v) && v.bookValuation != null && v.bookValuation.isEnabled) {    
                listitem{ navigate(bookValuation(v)) { 
                  text("Book ") text(v.fullAddress) 
                } }
              }
            }
          }
        }
      }
    }
  }  