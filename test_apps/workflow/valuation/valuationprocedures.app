module valuationprocedures

section procedures

  procedure bookValuation(v : Valuation) {
    who { securityContext.principal != null && securityContext.principal.hasBookingRights() }
    when { v.valuationRequest.status != null && v.valuationRequest.status.name == "Request Received" }
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
                  groupitem { label("Date") { input(v.valuationRequest.bookingDate) } }
                  groupitem { label("Time") { input(v.valuationRequest.bookingTime) } }
                  groupitem { label("Contact"){ input(v.valuationRequest.bookingContact) } }
                  groupitem { label("Phone"){ input(v.valuationRequest.bookingPhone) } }
                  groupitem { label("Notes") { input(v.valuationRequest.bookingNotes) } } 
                }
              }
              row { action("Book Valuation", do()) }
            }
          }
        }
      }
    }
    do { 
      v.valuationRequest.status := initValuationRequestStatus2; 
      v.valuationRequest.persist();
      v.persist();
    }
  }
  
  function unbookedValuationsExist() : Bool {
    var unbookedValuations : Bool := false;
    for (v : Valuation) {
      if (v.bookValuation != null && v.bookValuation.isEnabled) {
        unbookedValuations := true;
      }
    }
    return unbookedValuations;
  }
  
  define bookValuationTasks() {
    if (securityContext.principal != null && securityContext.principal.hasBookingRights() && unbookedValuationsExist()) {
      par {
        section("Booking tasks") {
          list {
            for (valuation : Valuation) {
              if (valuationHasProcedures(valuation) && valuation.bookValuation != null && valuation.bookValuation.isEnabled) {    
                listitem{ navigate(bookValuation(valuation)) { 
                  text("Book ") text(valuation.valuationRequest.fullAddress) 
                } }
              }
            }
          }
        }
      }
    }
  }  