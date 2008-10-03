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
  
  define topmenu() {
    menubar {
      menu {
        menuheader { navigate(home()) { "Home" } }
      }
      menu {
        menuheader { "User" }
        if (securityContext.principal == null) {
          menuitem { navigate(signin()) { "Sign in" } }
        } else {
          menuitem { navigate(signout()) { "Sign out" } }
        }
        menuitem { navigate(loginAs(userRuben)){"Login as Ruben"} }
        menuitem { navigate(loginAs(userLiming)){"Login as Liming"} }
      }
      menu {
        menuheader { navigate(allValuationRequest()) { "Valuation Requests" } }
        menuitem { navigate(newValuationRequest()) { "New Valuation Request" } }
      }
      if (securityContext.principal != null) {
        menu {
          menuheader { "My Valuations" }
          for (v : Valuation) {
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
      row {navigate(allValuation()){"All Valuations"}}
      row {navigate(newValuationRequest()){"New ValuationRequest"}}
    }
  }

  define valuationRequestSidebar(r : ValuationRequest) {
    propertyInfoSidebar(r)
    horizontalspacer
    valuationRequestDetailsSidebar(r)
    horizontalspacer
    valuationDetailsSidebar(r)
    
    contextSidebar()
  }
  
  define propertyInfoSidebar(r : ValuationRequest) {
    section {
      header {
        block{output(r.address)}
        block{output(r.suburb + " " + r.state.name + " " + r.postCode.name)}
      }
      for (v: Valuation in r.valuations) { // actually only one. TODO: integrate so there is only one entity??
        block{navigate(valuation(v)){"Valuation No: " text(v.number)}}
        for (i: Invoice in v.invoices) { // mostly only one
          block{navigate(invoice(i)){"Invoice No: " text(i.number)}}
        }
      }
    }
  }
  
  /* Valuation Request Details Sidebar */
  define valuationRequestDetailsSidebar(r : ValuationRequest) {
    if (canEditValuationRequest(r)) {
      requestEditSidebar(r)
    } else { 
      if (canViewValuationRequest(r)) {
        requestViewSidebar(r)
      }
    }
  }
  
  define requestEditSidebar(r : ValuationRequest) {
    block{header{text("Request Details")}}
    block { list {
      listitem{ navigate(editValuationRequest(r)){"Request Details"} }
      listitem{ navigate(editValuationRequestBooking(r)){"Booking Details"} }
      listitem{ navigate(editValuationRequestQuote(r)){"Quote Details"} }
    } }
  }

  define requestViewSidebar(r : ValuationRequest) {
    block{header{text("Request Details")}}
    block { list {
      listitem{ navigate(valuationRequest(r)){"Request Details"} }
      listitem{ navigate(valuationRequestBooking(r)){"Booking Details"} }
      listitem{ navigate(valuationRequestQuote(r)){"Quote Details"} }
    } }
  }

  /* Valuation Sidebar */
  define valuationDetailsSidebar(r : ValuationRequest) {
    for (v: Valuation in r.valuations) { // actually only one. 
      if (canEditValuation(v)) {
        valuationEditSidebar(v)
      } else { 
        if (canViewValuation(v)) {
          valuationViewSidebar(v)
        }
      }
    }
  }
  
  define valuationEditSidebar(v : Valuation) {
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
  
  define valuationViewSidebar(v : Valuation) {
    block{header{text("Valuation Details")}}
    block{
      list {
        listitem{ navigate(valuation(v)){"Property Summary"} }
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
