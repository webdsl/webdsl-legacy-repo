module valuationrequesteditpages

section pages

  define page editValuationRequestDetails(object : ValuationRequest) {
    define sidebar() {
      valuationRequestSidebar(object)
    }
    derive editPage from object
  }

  define page editValuationRequestBooking(object : ValuationRequest) {
    define sidebar() {
      valuationRequestSidebar(object)
    }
    derive editPage from object
  }

  define page editValuationRequestQuote(object : ValuationRequest) {
    define sidebar() {
      valuationRequestSidebar(object)
    }
    derive editPage from object
  }
