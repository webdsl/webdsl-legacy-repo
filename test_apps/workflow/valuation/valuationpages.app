module valuationpages

section pages
  
  define page allValuationRequest() {
    main()
    define sidebar() {
      navigate(newValuationRequest()){"New Valuation Request"}
    }
    define body() {
      header{"Valuation Requests"}
      table {
        for(v : ValuationRequest) {
          row { 
            text(v.name) 
            navigate(valuationRequest(v)){"view request"} navigate(editValuationRequestDetails(v)){"edit request"} 
            navigate(valuationProperty(v)){"view valuation"} navigate(editValuationProperty(v)){"edit valuation"} 
          }
        }
      }
    }
  }
  
  define page newValuationRequest() {
    var v : ValuationRequest := ValuationRequest {};
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
                groupitem { label("Valuation No.:"){input(v.valuationNumber)} }
                  groupitem { label("Invoice No.:"){input(i.number)} }
              }
              group("Property address") {
                groupitem { label("Address"){input(v.address)} }
                groupitem { label("Suburb"){input(v.suburb)} }
                groupitem { label("State"){input(v.state)} }
                groupitem { label("Post Code"){input(v.postCode)} }
              }
              group("Property Characteristics") {
                groupitem { label("Category"){input(v.category)} }
                groupitem { label("Type"){input(v.type)} }            
              }
              group("Specifications") {
                groupitem { label("Report Type"){input(v.reportType)} }
                groupitem { label("Propose"){input(v.propose)} }            
              }
            }
            block("datawidth") {
              group("Client") {
                groupitem { label("Existing Client"){input(v.client)} }
                groupitem { label("or New Client"){input(newClient.name)} }  
              }
              group("Applicant") {
                groupitem { label("Name"){input(v.applicantName)} }
                groupitem { label("Phone"){input(v.applicantPhone)} }
              }
              group("Inspection Contact") {
                groupitem { label("Name"){input(v.inspectionName)} }
                groupitem { label("Phone"){input(v.inspectionPhone)} }
              }            
            }
          }
          row{action("Add Request", addRequest())}
          
          action addRequest()
          {
            if (newClient.name != "") {
              newClient.persist();
              v.client := newClient;
            }
            
            v.persist();
            i.valuationRequest := v;
            i.persist();
            
            v.bookValuation := BookValuationProcedureStatus{};
            v.bookValuation.v := v;
            v.bookValuation.persist();
            v.persist();
            v.bookValuation.enable();

            return allValuationRequest();
          }
        }
      }
    }
  }
  