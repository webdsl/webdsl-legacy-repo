application org.webdsl.testProcess


description {
  A workflow example for a personal development plan workflow
}

imports templates
imports data
imports init
imports entitypages
imports ac
imports valuationpages
imports valuationviewpages
imports valuationeditpages
imports valuationrequestviewpages
imports valuationrequesteditpages
imports layout
imports style
//imports testsidebar
      
  
section pages

  define body() {}

  define page home() {
    main()
    define body() {
      header { "Integrated Property Services" }
      
      section {
        text("Welcome")
      }
      
      group {
        navigate(allValuation()){"All Valuations"}
        navigate(newValuationRequest()) {"New ValuationRequest"}
      }
    }
  }
  
  define page testValuationRequest(r: ValuationRequest) {
//    text("Valuation: ") output(r.valuation)
  }

  define page testValuation(v: Valuation) {
    text("ValuationRequest: ") output(v.valuationRequest)
  }
  