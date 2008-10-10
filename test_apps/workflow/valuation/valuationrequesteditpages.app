module valuationrequesteditpages

section pages

  define page editValuationRequestDetails(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Request Details")}
      form {
        table {
          row {
            block("datawidth") {
              group("Client") {
                groupitem { label("Client") { input(v.client) } }
                groupitem { label("Primary Interest") { input(v.primaryInterest) } }
                groupitem { label("Broker") { input(v.broker) } }
                groupitem { label("Lender Contact"){ input(v.lenderContact) }}
                groupitem { label("Reference"){ input(v.reference) }}
              }
              group("Property") { 
                groupitem { label("Address") { input(v.address) } } 
                groupitem { label("Suburb"){ input(v.suburb) }}
                groupitem { label("State"){ input(v.state) }}
                groupitem { label("Post Code"){ input(v.postCode) }}
                groupitem { label("Category"){ input(v.category) }}
                groupitem { label("Type"){ input(v.type) }}
              }
              group("Comments") { 
                groupitem { label("") { input(v.comments) } } 
              }
            }
            block("datawidth") {
              group("Specifications") { 
                groupitem { label("Report Type") { input(v.reportType) } } 
                groupitem { label("Propose"){ input(v.propose) }}
              }
              group("Applicant") { 
                groupitem { label("Name") { input(v.applicantName) } } 
                groupitem { label("Phone"){ input(v.applicantPhone) }}
              }
              group("Inspection Contact") { 
                groupitem { label("Name") { input(v.inspectionName) } } 
                groupitem { label("Phone"){ input(v.inspectionPhone) }}
              }
              group("Miscellaneous") { 
                groupitem { label("Purchase") { input(v.purchase) } } 
                groupitem { label("Owner Est."){ input(v.ownerEst) }}
                groupitem { label("Tender"){ input(v.tender) }}
                groupitem { label("Status"){ output(v.status) }}
              }
            }
            row { action("Save changes", saveValuationRequest()) }
          }
        }
      }
      action saveValuationRequest() {
        v.persist();
      }
    }
  }

  define page editValuationRequestBooking(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Booking Details")}
      form {
        table {
          row {
            block("datawidth") {
              group("Request Summary") {
                groupitem { label("Client") { output(v.client) } }
                groupitem { label("Reference") { output(v.reference) } }
                groupitem { label("Lender Contact") { output(v.lenderContact) } }
                groupitem { label("Applicant") { output(v.applicantName) } }
                groupitem { label("Request Date") { output(v.requestDate) } }
                groupitem { label("Sent Date") { output(v.sentDate) } }
                groupitem { label("Status") { output(v.status) } }
                groupitem { label("Report Type") { output(v.reportType) } }
                groupitem { label("Purchase Price") { block{text("$ ") output(v.purchase)} } } 
                groupitem { label("Owner Estimation") { block{text("$ ") output(v.ownerEst)} } }
                groupitem { label("Tender Price") { block{text("$ ") output(v.tender)} } }
              }
              group("Location") { 
                groupitem { label("Map") { "N/A" } }
                groupitem { label("UDB Ref"){ input(v.udbRef) } }
              }
            }
            block("datawidth") {
              group("Booking") { 
                groupitem { label("Valuer") { input(v.valuer) } } 
                groupitem { label("Date"){ input(v.bookingDate) } }
                groupitem { label("Time") { input(v.bookingTime) } } 
                groupitem { label("Contact"){ input(v.bookingContact) } }
                groupitem { label("Phone"){ input(v.bookingPhone) } }
                groupitem { label("Booked by"){ input(v.bookedBy) } }
              }
              group("Booking Notes") { 
                groupitem { label("") { input(v.bookingNotes) } } 
              }
              group("Inspection Contact") { 
                groupitem { label("Name") { input(v.inspectionName) } } 
                groupitem { label("Phone"){ input(v.inspectionPhone) } }
              }
            }
            row { action("Save changes", saveValuationRequest()) }
          }
        }
        action saveValuationRequest() {
          v.persist();
        }
      }
    }
  }

  define page editValuationRequestQuote(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Quote Details")}
      form {      
        table {
          row {
            block("datawidth") {
              group("Invoice Details") {
                groupitem { label("Client") { input(v.client) } }
                groupitem { label("Primary Interest") { input(v.primaryInterest) } }
              }
              group("Comments") { 
                groupitem { label("") { input(v.comments) } } 
              }
              group("Total") { 
                groupitem { label("Address") { input(v.address) } } 
              }
            }
            block("datawidth") {
              group("Quote Terms") { 
                groupitem { label("Report Type") { input(v.reportType) } } 
                groupitem { label("Propose"){ input(v.propose) }}
              }
            }
          }
          row { action("Save changes", saveValuationRequest()) }
        }
      }
      action saveValuationRequest() {
        v.persist();
      }
    }
  }

  define page editValuationProperty(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Property Summary")}
      form {
        table {
          row {
            block("datawidth") {
              group("Property Summary 1") {
                groupitem { label("Lot") { input(v.lot) } }
                groupitem { label("Plan") { input(v.plan) } }
                groupitem { label("Plan No.") { input(v.planNumber) } }
                groupitem { label("Proprietor"){ input(v.proprietor) }}
                groupitem { label("Zoning"){ input(v.zoning) }}
                groupitem { label("Council"){ input(v.council) }}
                groupitem { label("Instrument"){ input(v.instrument) }}
              }
              group("Property Summary 3") { 
                groupitem { label("Current Use") { input(v.currentUse) } } 
                groupitem { label("Main Building"){ input(v.mainBuilding) }}
                groupitem { label("Built About"){ input(v.builtAbout) }}
                groupitem { label("Additions"){ input(v.additions) }}
                groupitem { label("Heritage Issues"){ input(v.heritageIssues) }}
                groupitem { label("Env. Issues"){ input(v.envIssues) }}
                groupitem { label("Ess. Repairs"){ input(v.essRepairs) }}
              }
            }
            block("datawidth") {
              group("Property Summary 2") { 
                groupitem { label("Site Dims") { input(v.siteDims) } }
                groupitem { label("Site Area") { input(v.siteArea) } }
                groupitem { label("Car Accom.") { input(v.carAccommodation) } }
                groupitem { label("Car Area"){ input(v.carArea) }}
                groupitem { label("Living Area"){ input(v.livingArea) }}
                groupitem { label("Outdoor Area"){ input(v.outdoorArea) }}
                groupitem { label("Other Area"){ input(v.otherArea) }}
              }
              group("Property Summary 4") {
                groupitem { label("Actual Rent (pw)") { input(v.actualRent) } }
                groupitem { label("Marketability"){ input(v.marketability) }}
                groupitem { label("View"){ input(v.view) }}
              }
            }
          }
          row { action("Save changes", saveValuation()) }
        }
      }
      action saveValuation() {
        v.persist();
      }
    }
  }
  
  define page editValuationMainBuilding(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Main Building")}
      form {
        table {
          row {
            block("datawidth") {
              group("Property Summary 1") {
                groupitem { label("Lot") { input(v.lot) } }
                groupitem { label("Plan") { input(v.plan) } }
                groupitem { label("Plan No.") { input(v.planNumber) } }
                groupitem { label("Proprietor"){ input(v.proprietor) }}
                groupitem { label("Zoning"){ input(v.zoning) }}
                groupitem { label("Council"){ input(v.council) }}
                groupitem { label("Instrument"){ input(v.instrument) }}
              }
              group("Property Summary 3") { 
                groupitem { label("Current Use") { input(v.currentUse) } } 
                groupitem { label("Main Building"){ input(v.mainBuilding) }}
                groupitem { label("Built About"){ input(v.builtAbout) }}
                groupitem { label("Additions"){ input(v.additions) }}
                groupitem { label("Heritage Issues"){ input(v.heritageIssues) }}
                groupitem { label("Env. Issues"){ input(v.envIssues) }}
                groupitem { label("Ess. Repairs"){ input(v.essRepairs) }}
              }
            }
            block("datawidth") {
              group("Property Summary 2") { 
                groupitem { label("Site Dims") { input(v.siteDims) } }
                groupitem { label("Site Area") { input(v.siteArea) } }
                groupitem { label("Car Accom.") { input(v.carAccommodation) } }
                groupitem { label("Car Area"){ input(v.carArea) }}
                groupitem { label("Living Area"){ input(v.livingArea) }}
                groupitem { label("Outdoor Area"){ input(v.outdoorArea) }}
                groupitem { label("Other Area"){ input(v.otherArea) }}
              }
              group("Property Summary 4") {
                groupitem { label("Actual Rent (pw)") { input(v.actualRent) } }
                groupitem { label("Marketability"){ input(v.marketability) }}
                groupitem { label("View"){ input(v.view) }}
              }
            }
          }
          row { action("Save changes", saveValuation()) }
        }
      }
      action saveValuation() {
        v.persist();
      }
    }
  }
  
  define page editValuationRisk(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    derive editPage from v
  }
  
  define page editValuationLand(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    derive editPage from v
  }
  
  define page editValuationSales(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    derive editPage from v
  }