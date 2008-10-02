module valuationpages

section pages

  define page allValuation() {
    main()
    define body() {
      for(v : Valuation) {
        output(v)
        text(" ")
      }
    }
  }
  
  define page allValuationRequest() {
    main()
    define sidebar() {
      navigate(newValuationRequest()){"New Valuation Request"}
    }
    define body() {
      table {
        for(r : ValuationRequest) {
          row { text(r.name) navigate(valuationRequest(r)){"view"} }
        }
      }
    }
  }
  
  define page newValuationRequest() {
    var r : ValuationRequest := ValuationRequest {};
    var v : Valuation := Valuation { };
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
            r.persist();
            v.valuationRequest := r;
            v.persist();
            i.valuation := v;
            i.persist();
            
            return allValuationRequest();
          }
        }
      }
    }
  }
  