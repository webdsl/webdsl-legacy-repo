module templates

section templates

  define main() {
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
	    text("WebWorkFlow tests")
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
    text("Nothing here unless you redefine sidebar()")
  }

  define valuationRequestSidebar(r : ValuationRequest) {
    propertyInfoSidebar(r)
    requestSidebar(r)
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
        horizontalspacer
        if (canEditValuation(v)) {
          propertyInfoEdit(v)
        } else { 
          if (canViewValuation(v)) {
            propertyInfoView(v)
          }
        }
      }
    }
  }
  
  define propertyInfoEdit(v : Valuation) {
    block{header{text("Request Details")}}
    block { list {
      listitem{ navigate(editValuationRequestDetails(v.valuationRequest)){"Request Details"} }
      listitem{ navigate(editValuationRequestBooking(v.valuationRequest)){"Booking Details"} }
      listitem{ navigate(editValuationRequestQuote(v.valuationRequest)){"Quote Details"} }
    } }
    horizontalspacer
    block{header{text("Valuation Details")}}
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

  define propertyInfoView(v : Valuation) {
    block{header{text("Request Details")}}
    block { list {
      listitem{ navigate(valuationRequestDetails(v.valuationRequest)){"Request Details"} }
      listitem{ navigate(valuationRequestBooking(v.valuationRequest)){"Booking Details"} }
      listitem{ navigate(valuationRequestQuote(v.valuationRequest)){"Quote Details"} }
    } }
    horizontalspacer
    block{header{text("Valuation Details")}}
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

  define requestSidebar(r : ValuationRequest) {
    
  }
  
  define valuationDetailsSidebar(r : ValuationRequest) {
    
  }
  
  define contextSidebar() {
  }

section basic page elements.
  
  define footer() {
    "generated with "
    navigate("WebDSL", url("http://www.webdsl.org")) " and "
    navigate("Stratego/XT", url("http://www.strategoxt.org"))
  }
