module valuationpages

section pages

  define page allValuation() {
    init {
      goto allValuationRequest();
    }
/*    main()
    define sidebar() {
      navigate(newValuationRequest()){"New Valuation Request"}
    }
    define body() {
      header{"Valuations"}
      table {
        for(v : Valuation) {
          row { text(v.name) navigate(valuation(v)){"view"} navigate(editValuationProperty(v)){"edit"} }
        }
      }
    }*/
  }
  
  define page allValuationRequest() {
    main()
    define sidebar() {
      navigate(newValuationRequest()){"New Valuation Request"}
    }
    define body() {
      header{"Valuation Requests"}
      table {
        for(v : Valuation) {
          row { 
            text(v.name) 
            navigate(valuationRequest(v.valuationRequest)){"view request"} navigate(editValuationRequest(v.valuationRequest)){"edit request"} 
            navigate(valuation(v)){"view valuation"} navigate(editValuationProperty(v)){"edit valuation"} 
          }
        }
      }
    }
  }
  
  define page newValuationRequest() {
    var r : ValuationRequest := ValuationRequest {};
    var v : Valuation := Valuation {};
    var i : Invoice := Invoice { amount := 1000 };
    var newClient : Client := Client {};
    main()
    define body() {
      header{text("New Valuation Request")}
      form {
        table {
          row {
            block("datawidth") {
              group("Numbers") {
                groupitem { label("Valuation No.:"){input(v.number)} }
                  groupitem { label("Invoice No.:"){input(i.number)} }
              }
              group("Property address") {
                groupitem { label("Address"){input(r.address)} }
                groupitem { label("Suburb"){input(r.suburb)} }
                groupitem { label("State"){input(r.state)} }
                groupitem { label("Post Code"){input(r.postCode)} }
              }
              group("Property Characteristics") {
                groupitem { label("Category"){input(r.category)} }
                groupitem { label("Type"){input(r.type)} }            
              }
              group("Specifications") {
                groupitem { label("Report Type"){input(r.reportType)} }
                groupitem { label("Propose"){input(r.propose)} }            
              }
            }
            block("datawidth") {
              group("Client") {
                groupitem { label("Existing Client"){input(r.client)} }
                groupitem { label("or New Client"){input(newClient.name)} }  
              }
              group("Applicant") {
                groupitem { label("Name"){input(r.applicantName)} }
                groupitem { label("Phone"){input(r.applicantPhone)} }
              }
              group("Inspection Contact") {
                groupitem { label("Name"){input(r.inspectionName)} }
                groupitem { label("Phone"){input(r.inspectionPhone)} }
              }            
            }
          }
          row{action("Add Request", addRequest())}
          
          action addRequest()
          {
            if (newClient.name != "") {
              newClient.persist();
              r.client := newClient;
            }
            
/*            var bookValuation : BookValuationProcedureStatus := BookValuationProcedureStatus{};            
            bookValuation.persist();
            v.bookValuation := bookValuation;*/
            
            r.persist();
            v.valuationRequest := r;
            v.persist();
            i.valuation := v;
            i.persist();
            
            v.bookValuation := BookValuationProcedureStatus{};
            v.bookValuation.v := v;
            v.bookValuation.persist();
            v.persist();
            v.bookValuation.enable();

/*            v.bookValuation.enable();*/
            
            return allValuationRequest();
          }
        }
      }
    }
  }
  