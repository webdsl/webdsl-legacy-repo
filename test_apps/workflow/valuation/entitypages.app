module entitypages

section user pages

  define page user(object : User) { derive viewPage from object }
  
section value pages
  define page valuationRequestStatus(object : ValuationRequestStatus) { derive viewPage from object }
  define page invoice(object : Invoice) { derive viewPage from object }
  define page salesEvidence (object : SalesEvidence) { derive viewPage from object }
  define page valuer (object : Valuer) { derive viewPage from object }
  define page progressValuation (object : ProgressValuation) { derive viewPage from object }
  define page allComparisonValue() {
    main()
    define body() {
      for(c : ComparisonValue) {
        text(c.name)
        text(" ")
      }
    }
  }
  define page allInvoice() {
    main()
    define body() {
      for(i : Invoice) {
        output(i)
        text(" ")
      }
    }
  }
  define page client(object : Client) { derive viewPage from object }
  define page broker(object : Broker) { derive viewPage from object }
  