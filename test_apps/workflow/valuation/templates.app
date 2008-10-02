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
        menuitem { navigate(signin()) { "Sign in" } }
      }
      menu {
        menuheader { navigate(allValuationRequest()) { "Valuation Requests" } }
        menuitem { navigate(newValuationRequest()) { "New Valuation Request" } }
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
    table {
      row { output(r.name) }
      for (v: Valuation in r.valuations) {
        row { navigate(valuation(v)){"Valuation No: " text(v.number)} }
        for (i: Invoice in v.invoices) {
          row { navigate(invoice(i)){"Invoice No: " text(i.number)} }
        }
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
