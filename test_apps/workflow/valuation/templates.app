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
        menuitem { navigate(newValuationRequest()) { "New Valuation Request" } }
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
    }
  }
  
section sidebar

  define sidebar() {
    table {
      row {navigate(allValuationRequest()){"All Valuation Request"}}
      row {navigate(newValuationRequest()){"New ValuationRequest"}}
    }
  }

  define valuationRequestSidebar(v : ValuationRequest) {
    propertyInfoSidebar(v)
    horizontalspacer
    valuationRequestDetailsSidebar(v)
    horizontalspacer
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
    if (canEditValuationRequest(v)) {
      requestEditSidebar(v)
    } else { 
      if (canViewValuationRequest(v)) {
        requestViewSidebar(v)
      }
    }
  }
  
  define requestEditSidebar(v : ValuationRequest) {
    block{header{text("Request Details")}}
    block { list {
      listitem{ navigate(editValuationRequestDetails(v)){"Request Details"} }
      listitem{ navigate(editValuationRequestBooking(v)){"Booking Details"} }
      listitem{ navigate(editValuationRequestQuote(v)){"Quote Details"} }
    } }
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
    if (canEditValuation(v)) {
      valuationEditSidebar(v)
    } else { 
      if (canViewValuation(v)) {
        valuationViewSidebar(v)
      }
    }
  }
  
  define valuationEditSidebar(v : ValuationRequest) {
    block{header{text("Edit Valuation Details")}}
    block{
      list {
        listitem{ navigate(editValuationProperty(v)){"Property Summary"} }
        listitem{ navigate(editValuationMainBuilding(v)){"Main Building"} }
        listitem{ navigate(editValuationRisk(v)){"Risk Analysis"} }
        listitem{ navigate(editValuationLand(v)){"Land"} }
        listitem{ navigate(editValuationSales(v)){"Sales Evidence"} }
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
