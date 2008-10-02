application org.webdsl.testProcess


description {
  A workflow example for a personal development plan workflow
}

imports templates
imports data
imports init
imports entitypages
imports ac
imports layout
imports style
//imports testsidebar

/*section procedures

  procedure pdpWorkflow(p : PdpMeeting) {
    do {
      p.report := "Kanarie";
      p.persist();
    }
    process {
      (employeeFillInForm(p) and managerFillInForm(p));
      repeat {
        writeReport(p);
        approveReport(p)
      } until finalizeReport(p)
    }
  }
  
  procedure employeeFillInForm(p : PdpMeeting) {
    who { securityContext.principal == p.employee }
    view {
      derive procedurePage from p for (employeePreparation) {
        title{"Fill in employee form"}
        header{"Fill in employee form"}
      }
    }
  }

  procedure managerFillInForm(p : PdpMeeting) {
    who { securityContext.principal == p.employee.manager }
    view {
      title{"Fill in manager form"}
      derive procedurePage from p for (managerPreparation) {
        title{"Fill in manager form"}
        header{"Fill in manager form"}
      }
    }
  }

  procedure writeReport(p : PdpMeeting) {
    who { securityContext.principal == p.employee.manager }
    view {
      derive procedurePage from p for (report) {
        title{"Write report"}
        header{"Write report"}
      }
    }
  }

  procedure finalizeReport(p : PdpMeeting) {
    who { securityContext.principal == p.employee.manager }
  }

  procedure approveReport(p : PdpMeeting) {
    who { securityContext.principal == p.employee }
  }*/
      
  
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
      
/*      var employee : User
      header { "Home" }
      section {
        form {
          header{"Quick start PDP Meeting for employee"}
          action("Quick start", quickStart())
        
          action quickStart() {
            var p : PdpMeeting := newPdpMeeting();
            p.employee := aUser;
            p.persist();
            p.pdpWorkflow.enable();
            
            if (securityContext.loggedIn) {
              return message("Workflows started!");
            } else {
              return signin();
            }
          }
        }
      }*/
/*      
      section {
        form {
          header{"Organize PDP Meeting"}
          "For: " input(employee)
          action("Organize", organize())

          action organize() {
            var p : PdpMeeting := newPdpMeeting();
            p.employee := employee;
            p.persist();
            p.pdpWorkflow.enable();

            if (securityContext.loggedIn) {
              return message("Workflows started!");
            } else {
              return signin();
            }
          }
        }
      }*/
    }
  }
  
  define page allValuation() {
    main()
    define body() {
      for(v : Valuation) {
        output(v)
        text(" ")
      }
    }
  }
  
  define page valuationRequest(object : ValuationRequest) { 
    define sidebar() {
      valuationRequestSidebar(object)
    }
    derive viewPage from object 
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
            //v.valuationRequest := r;
            r.persist();
            v.valuationRequest := r;
            v.persist();
            i.valuation := v;
            i.persist(); // waarom werkt het maar 1 kant op???
            
            return allValuationRequest();
          }
        }
      }
    }
  }
  
  define page testValuationAdd() {
    main()
    
    var v : Valuation := Valuation {};
    define body() {
      header{text(v.name) text("Property Summary")}
      table {
        row {
          group("Property Summary 1") {
            groupitem { label("Lot") { input(v.lot) } }
            groupitem { label("Plan") { input(v.plan) } }
            groupitem { label("Plan No.") { input(v.planNumber) } }
            groupitem { label("Proprietor"){ input(v.proprietor) }}
            groupitem { label("Zoning"){ input(v.zoning) }}
            groupitem { label("Council"){ input(v.council) }}
            groupitem { label("Instrument"){ input(v.instrument) }}
          }
          group("Property Summary 2") { 
            groupitem { label("Site Dims") { input(v.siteDims) } }
            groupitem { label("Site Area") { input(v.siteArea) } }
            groupitem { label("Car Accom.") { input(v.carAccommodation) } }
            groupitem { label("Car Area"){ input(v.carArea) }}
            groupitem { label("Living Area"){ input(v.livingArea) }}
            groupitem { label("Outdoor Area"){ input(v.outdoorArea) }}
            groupitem { label("Other Area"){ input(v.otherArea) }}
          }
        }
        row {
          group("Property Summary 3") { 
            groupitem { label("Current Use") { input(v.currentUse) } } 
            groupitem { label("Main Building"){ input(v.mainBuilding) }}
            groupitem { label("Built About"){ input(v.builtAbout) }}
            groupitem { label("Additions"){ input(v.additions) }}
            groupitem { label("Heritage Issues"){ input(v.heritageIssues) }}
            groupitem { label("Env. Issues"){ input(v.envIssues) }}
            groupitem { label("Ess. Repairs"){ input(v.essRepairs) }}
          }
          group("Property Summary 4") {
            groupitem { label("Actual Rent (pw)") { input(v.actualRent) } }
            groupitem { label("Marketability"){ input(v.marketability) }}
            groupitem { label("View"){ input(v.view) }}
          }
        }
      }
    }
  }
  
  define page testValuationRequest(r: ValuationRequest) {
//    text("Valuation: ") output(r.valuation)
  }

  define page testValuation(v: Valuation) {
    text("ValuationRequest: ") output(v.valuationRequest)
  }

access control rules 
  rule page *(*) {
    true
  }
