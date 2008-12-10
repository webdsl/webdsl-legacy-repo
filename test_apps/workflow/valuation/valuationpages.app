module valuationpages

section pages
  
  define page allValuationRequest() {
    main()
    define sidebar() {
      navigate(newValuation()){"New Valuation Request"}
    }
    define body() {
      header{"Valuation Requests"}
      table {
        for(v : ValuationRequest) {
          row { 
            text(v.name) 
            navigate(valuationRequest(v)){"view request"} navigate(editValuationRequestDetails(v)){"edit request"} 
            navigate(valuationProperty(v)){"view valuation"} navigate(editValuationProperty(v)){"edit valuation"}
            navigate(bookValuation(v)){"book valuation"}
          }
        }
      }
    }
  }
  
  define page newValuation() {
    var i : Invoice := Invoice { amount := 1000 };
    var newClient : Client := Client {};
    
    var valuationNumber : Int; 
    var address : String;
    var suburb : String;
    var state : StateValue;
    var postCode : PostCodeValue;
    var category : PropertyCategoryValue;
    var type : PropertyTypeValue;
    var reportType : ReportTypeValue;
    var purpose : PurposeValue;
    var oldClient : Client;
    var applicantName : String;
    var applicantPhone : String;
    var inspectionName : String;
    var inspectionPhone : String;
    
    main()
    define body() {
      header{text("New Valuation Request")}
      form {
        table {
          row {
            block("datawidth") {
              group("Numbers") {
                groupitem { label("Valuation No.:"){input(valuationNumber)} }
                  groupitem { label("Invoice No.:"){input(i.number)} }
              }
              group("Property address") {
                groupitem { label("Address"){input(address)} }
                groupitem { label("Suburb"){input(suburb)} }
                groupitem { label("State"){input(state)} }
                groupitem { label("Post Code"){input(postCode)} }
              }
              group("Property Characteristics") {
                groupitem { label("Category"){input(category)} }
                groupitem { label("Type"){input(type)} }            
              }
              group("Specifications") {
                groupitem { label("Report Type"){input(reportType)} }
                groupitem { label("Propose"){input(purpose)} }            
              }
            }
            block("datawidth") {
              group("Client") {
                groupitem { label("Existing Client"){input(oldClient)} }
                groupitem { label("or New Client"){input(newClient.name)} }  
              }
              group("Applicant") {
                groupitem { label("Name"){input(applicantName)} }
                groupitem { label("Phone"){input(applicantPhone)} }
              }
              group("Inspection Contact") {
                groupitem { label("Name"){input(inspectionName)} }
                groupitem { label("Phone"){input(inspectionPhone)} }
              }            
            }
          }
          row{action("Add Request", addRequest())}
          
          action addRequest()
          {
            var v : ValuationRequest := ValuationRequest {};

            if (newClient.name != "") {
              newClient.persist();
              v.client := newClient;
            } else {
              v.client := oldClient;
            }
            
            v.valuationNumber := valuationNumber;
            v.address := address;
            v.suburb := suburb;
            v.state := state;
            v.postCode := postCode;
            v.category := category;
            v.type := type;
            v.reportType := reportType;
            v.purpose := purpose;
            v.applicantName := applicantName;
            v.applicantPhone := applicantPhone;
            v.inspectionName := inspectionName;
            v.inspectionPhone := inspectionPhone;
            
            v.persist();
            i.valuationRequest := v;
            i.persist();
            v.startValuationWorkflow();

            return allValuationRequest();
          }
        }
      }
    }
  }
  