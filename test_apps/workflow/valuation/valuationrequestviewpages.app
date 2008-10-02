module valuationrequestviewpages

section pages

  define page valuationRequest(object : ValuationRequest) { 
    define sidebar() {
      valuationRequestSidebar(object)
    }
    derive viewPage from object 
  }

  define page valuationRequestDetails(object : ValuationRequest) {
    define sidebar() {
      valuationRequestSidebar(object)
    }
    derive viewPage from object
  }

  define page valuationRequestBooking(object : ValuationRequest) {
    define sidebar() {
      valuationRequestSidebar(object)
    }
    derive viewPage from object
  }

  define page valuationRequestQuote(object : ValuationRequest) {
    define sidebar() {
      valuationRequestSidebar(object)
    }
    derive viewPage from object
  }
