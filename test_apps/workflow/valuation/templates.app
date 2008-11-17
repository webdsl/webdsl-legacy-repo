module templates

section templates

  define main() {
    title{"Integrated Property Services - Valuation"}
    top()
    topmenu()
    sidebar()
    body()
    footer()
  }
  
  define top() {
    block("logos") {
      image("/images/webdsl_logo_small.png")
      image("/images/webdsl_logo_text.png")
    }
    block("text") {
	    text("Integrated Property Services - Valuation")
	  }
  }
  
  function userHasValuations() : Bool {
    for (v : ValuationRequest) {
      if (v.valuer != null && v.valuer.user != null && v.valuer.user == securityContext.principal) {
        return true;
      }
    }
    return false;
  }
  
  define topmenu() {
    menubar {
      menu {
        menuheader { navigate(home()) { "Home" } }
      }
      menu {
        menuheader { 
          if (securityContext.principal != null) {
            navigate(url("#")){"User: " text(securityContext.principal.name)}
          } 
          if (securityContext.principal == null) { navigate(url("#")){text("User")} }
        }
        if (securityContext.principal == null) {
          menuitem { navigate(signin()) { "Sign in" } }
        } else {
          menuitem { navigate(signout()) { "Sign out" } }
        }
        menuitem { navigate(loginAs(userRuben)){"Login as Ruben"} }
        menuitem { navigate(loginAs(userLiming)){"Login as Liming"} }
        menuitem { navigate(loginAs(userAdmin)){"Login as Admin"} }
        menuitem { navigate(loginAs(userManager)){"Login as Manager"} }
      }
      menu {
        menuheader { navigate(allValuationRequest()) { "Valuation Requests" } }
        menuitem { navigate(newValuation()) { "New Valuation Request" } }
      }
      if (securityContext.principal != null && userHasValuations()) {
        menu {
          menuheader { "My Valuations" }
          for (v : ValuationRequest) {
            if (v.valuer != null && v.valuer.user != null && v.valuer.user == securityContext.principal) {
              menuitem { navigate(editValuationProperty(v)){ text(v.name) } }
            }
          }
        }
      }
      if (securityContext.principal != null && securityContext.principal.hasBookingRights()) {
        menu {
          menuheader { navigate(unbooked()){"Unbooked"} }
          if (unbookedValuations()) {
            for (v : ValuationRequest) {
              if (valuationRequestHasProcedures(v) && v.bookValuation != null && v.bookValuation.isEnabled) {    
                menuitem { navigate(bookValuation(v)) { 
                  text("Book ") text(v.fullAddress) 
                } }
              }
            }
          }
        }
      }
    }
  }
  
section sidebar

  define sidebar() {
    table {
      row {navigate(allValuationRequest()){"All Valuation Request"}}
      row {navigate(newValuation()){"New ValuationRequest"}}
    }
  }

  define valuationRequestSidebar(v : ValuationRequest) {
    propertyInfoSidebar(v)
    valuationRequestDetailsSidebar(v)
    valuationDetailsSidebar(v)
    
    contextSidebar()
  }
  
  define propertyInfoSidebar(v : ValuationRequest) {
    section {
      header {
        block{output(v.address)}
        block{output(v.suburb + " " + v.state.name + " " + v.postCode.name)}
      }
      block("small") {
        navigate(valuationProperty(v)){"Valuation No: " text(v.valuationNumber)}
        for (i: Invoice in v.invoices) { 
          block{navigate(invoice(i)){"Invoice No: " text(i.number)}}
        }
        if (viewBookValuationLink(v)) 
        {
          par {
            text("This valuation has not yet been booked")
            navigate(bookValuation(v)) { text("Book valuation") }
          }
        }
      }
    }
  }
  
  /* Valuation Request Details Sidebar */
  define valuationRequestDetailsSidebar(v : ValuationRequest) {
    if (canEditValuationRequest(v) && valuationRequestHasEditRequestProcedures(v)) {
      horizontalspacer
      requestEditSidebar(v)
    } else { 
      if (canViewValuationRequest(v)) {
        horizontalspacer
        requestViewSidebar(v)
      }
    }
  }
  
  define requestEditSidebar(v : ValuationRequest) {
    block{header{text("Request Details")}}
    block { 
      list {
        listitem{ navigate(editValuationRequestDetails(v)){"Request Details"} }
        listitem{ navigate(editValuationRequestBooking(v)){"Booking Details"} }
        listitem{ navigate(editValuationRequestQuote(v)){"Quote Details"} }
      } 
    }
  }

  define requestViewSidebar(v : ValuationRequest) {
    block{header{text("Request Details")}}
    text("Read only")
    block { list {
      listitem{ navigate(valuationRequest(v)){"Request Details"} }
      listitem{ navigate(valuationRequestBooking(v)){"Booking Details"} }
      listitem{ navigate(valuationRequestQuote(v)){"Quote Details"} }
    } }
  }

  /* Valuation Sidebar */
  define valuationDetailsSidebar(v : ValuationRequest) {
    if (canEditValuation(v) && valuationRequestHasEditValuationProcedures(v)) {
      horizontalspacer
      valuationEditSidebar(v)
    } else { 
      if (canViewValuation(v)) {
        horizontalspacer
        valuationViewSidebar(v)
      }
    }
  }
  
  // pre: canEditValuation(v)
  define valuationEditSidebar(v : ValuationRequest) {
    block{header{text("Edit Valuation Details")}}
    block{
      list {
        listitem{ navigate(editValuationProperty(v)){"Property Summary"} }
        listitem{ navigate(editValuationMainBuilding(v)){"Main Building"} }
        listitem{ navigate(editValuationRisk(v)){"Risk Analysis"} }
        listitem{ navigate(editValuationLand(v)){"Land"} }
        listitem{ navigate(editValuationSales(v)){"Sales Evidence"} }
        if (v.finalizeValuation != null && v.finalizeValuation.isEnabled) {navigatebutton(finalizeValuation(v), "Finalize")}
        if (v.approveValuation != null && v.approveValuation.isEnabled && canApproveValuations()) {navigatebutton(approveValuation(v), "Approve")}
        if (v.sendValuation != null && v.sendValuation.isEnabled && canSendValuations()) {navigatebutton(sendValuation(v), "Mark as Sent")}
      }
    }
  }
  
  define valuationViewSidebar(v : ValuationRequest) {
    block{header{text("Valuation Details")}}
    text("Read only")
    block{
      list {
        listitem{ navigate(valuationProperty(v)){"Property Summary"} }
        listitem{ navigate(valuationMainBuilding(v)){"Main Building"} }
        listitem{ navigate(valuationRisk(v)){"Risk Analysis"} }
        listitem{ navigate(valuationLand(v)){"Land"} }
        listitem{ navigate(valuationSales(v)){"Sales Evidence"} }
        if (v.approveValuation != null && v.approveValuation.isEnabled && canApproveValuations()) {navigatebutton(approveValuation(v), "Approve")}
        if (v.sendValuation != null && v.sendValuation.isEnabled && canSendValuations()) {navigatebutton(sendValuation(v), "Mark as Sent")}
      }
    }
  }
  
  define contextSidebar() {
  }

section basic page elements.
  
  define footer() {
    " Generated with "
    navigate("WebDSL", url("http://www.webdsl.org")) " and "
    navigate("Stratego/XT", url("http://www.strategoxt.org"))
  }
