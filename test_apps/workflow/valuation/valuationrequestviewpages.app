module valuationrequestviewpages

section pages

  define page valuationRequest(v : ValuationRequest) { 
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Valuation Request")}
      table {
        row {
          block("datawidth") {
            group("Status") { 
              groupitem { label("Request status") { output(v.status) } }
            }
            group("Request Summary") {
              groupitem { label("Client") { output(v.client) } }
              groupitem { label("Reference") { output(v.reference) } }
              groupitem { label("Lender Contact") { output(v.lenderContact) } }
              groupitem { label("Applicant") { output(v.applicantName) } }
              groupitem { label("Request Date") { output(v.requestDate) } }
              groupitem { label("Sent Date") { output(v.sentDate) } }
              groupitem { label("Status") { output(v.status) } }
              groupitem { label("Report Type") { output(v.reportType) } }
              groupitem { label("Purchase Price") { text("$ ") output(v.purchase) } } 
              groupitem { label("Owner Estimation") { text("$ ") output(v.ownerEst) } }
              groupitem { label("Tender Price") { text("$ ") output(v.tender) } }
            }
            group("Location") { 
              groupitem { label("Map") { "N/A" } }
              groupitem { label("UDB Ref"){ output(v.udbRef) } }
            }
          }
          block("datawidth") {
            group("Booking") { 
              groupitem { label("Valuer") { output(v.valuer) } } 
              groupitem { label("Date"){ output(v.bookingDate) } }
              groupitem { label("Time") { output(v.bookingTime) } } 
              groupitem { label("Contact"){ output(v.bookingContact) } }
              groupitem { label("Phone"){ output(v.bookingPhone) } }
              groupitem { label("Booked by"){ output(v.bookedBy) } }
            }
            group("Booking Notes") { 
              groupitem { label("") { output(v.bookingNotes) } } 
            }
            group("Inspection Contact") { 
              groupitem { label("Name") { output(v.inspectionName) } } 
              groupitem { label("Phone"){ output(v.inspectionPhone) } }
            }
          }
        }
      }
    }
  }

  define page valuationRequestDetails(v : ValuationRequest) { 
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Request Details")}
      table {
        row {
          block("datawidth") {
            group("Client") {
              groupitem { label("Client") { output(v.client) } }
              groupitem { label("Primary Interest") { output(v.primaryInterest) } }
              groupitem { label("Broker") { output(v.broker) } }
              groupitem { label("Lender Contact"){ output(v.lenderContact) }}
              groupitem { label("Reference"){ output(v.reference) }}
            }
            group("Property") { 
              groupitem { label("Address") { output(v.address) } } 
              groupitem { label("Suburb"){ output(v.suburb) }}
              groupitem { label("State"){ output(v.state) }}
              groupitem { label("Post Code"){ output(v.postCode) }}
              groupitem { label("Category"){ output(v.category) }}
              groupitem { label("Type"){ output(v.type) }}
            }
            group("Comments") { 
              groupitem { label("Comments") { output(v.comments) } } 
            }
          }
          block("datawidth") {
            group("Specifications") { 
              groupitem { label("Report Type") { output(v.reportType) } } 
              groupitem { label("Propose"){ output(v.propose) }}
            }
            group("Applicant") { 
              groupitem { label("Name") { output(v.applicantName) } } 
              groupitem { label("Phone"){ output(v.applicantPhone) }}
            }
            group("Inspection Contact") { 
              groupitem { label("Name") { output(v.inspectionName) } } 
              groupitem { label("Phone"){ output(v.inspectionPhone) }}
            }
            group("Miscellaneous") { 
              groupitem { label("Purchase") { output(v.purchase) } } 
              groupitem { label("Owner Est."){ output(v.ownerEst) }}
              groupitem { label("Tender"){ output(v.tender) }}
              groupitem { label("Status"){ output(v.status) }}
            }
          }
        }
      }
    }
  }
  
  define page valuationRequestBooking(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Booking Details")}
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
              groupitem { label("Purchase Price") { text("$ ") output(v.purchase) } } 
              groupitem { label("Owner Estimation") { text("$ ") output(v.ownerEst) } }
              groupitem { label("Tender Price") { text("$ ") output(v.tender) } }
            }
            group("Location") { 
              groupitem { label("Map") { "N/A" } }
              groupitem { label("UDB Ref"){ output(v.udbRef) } }
            }
          }
          block("datawidth") {
            group("Booking") { 
              groupitem { label("Valuer") { output(v.valuer) } } 
              groupitem { label("Date"){ output(v.bookingDate) } }
              groupitem { label("Time") { output(v.bookingTime) } } 
              groupitem { label("Contact"){ output(v.bookingContact) } }
              groupitem { label("Phone"){ output(v.bookingPhone) } }
              groupitem { label("Booked by"){ output(v.bookedBy) } }
            }
            group("Booking Notes") { 
              groupitem { label("") { output(v.bookingNotes) } } 
            }
            group("Inspection Contact") { 
              groupitem { label("Name") { output(v.inspectionName) } } 
              groupitem { label("Phone"){ output(v.inspectionPhone) } }
            }
          }
        }
      }
    }
  }
  
  // TODO - nu alleen wat mockup
  define page valuationRequestQuote(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Quote Details")}
      table {
        row {
          block("datawidth") {
            group("Invoice Details") {
              groupitem { label("Client") { output(v.client) } }
              groupitem { label("Primary Interest") { output(v.primaryInterest) } }
            }
            group("Comments") { 
              groupitem { label("") { output(v.comments) } } 
            }
            group("Total") { 
              groupitem { label("Address") { output(v.address) } } 
            }
          }
          block("datawidth") {
            group("Quote Terms") { 
              groupitem { label("Report Type") { output(v.reportType) } } 
              groupitem { label("Propose"){ output(v.propose) }}
            }
          }
        }
      }
    }
  }

  define page valuationProperty(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Property Summary")}
      table {
        row {
          block("datawidth") {
            group("Property Summary 1") {
              groupitem { label("Lot") { output(v.lot) } }
              groupitem { label("Plan") { output(v.plan) } }
              groupitem { label("Plan No.") { output(v.planNumber) } }
              groupitem { label("Proprietor"){ output(v.proprietor) }}
              groupitem { label("Zoning"){ output(v.zoning) }}
              groupitem { label("Council"){ output(v.council) }}
              groupitem { label("Instrument"){ output(v.instrument) }}
            }
            group("Property Summary 3") { 
              groupitem { label("Current Use") { output(v.currentUse) } } 
              groupitem { label("Main Building"){ output(v.mainBuilding) }}
              groupitem { label("Built About"){ output(v.builtAbout) }}
              groupitem { label("Additions"){ output(v.additions) }}
              groupitem { label("Heritage Issues"){ output(v.heritageIssues) }}
              groupitem { label("Env. Issues"){ output(v.envIssues) }}
              groupitem { label("Ess. Repairs"){ output(v.essRepairs) }}
            }
          }
          block("datawidth") {
            group("Property Summary 2") { 
              groupitem { label("Site Dims") { output(v.siteDims) } }
              groupitem { label("Site Area") { output(v.siteArea) } }
              groupitem { label("Car Accom.") { output(v.carAccommodation) } }
              groupitem { label("Car Area"){ output(v.carArea) }}
              groupitem { label("Living Area"){ output(v.livingArea) }}
              groupitem { label("Outdoor Area"){ output(v.outdoorArea) }}
              groupitem { label("Other Area"){ output(v.otherArea) }}
            }
            group("Property Summary 4") {
              groupitem { label("Actual Rent (pw)") { output(v.actualRent) } }
              groupitem { label("Marketability"){ output(v.marketability) }}
              groupitem { label("View"){ output(v.view) }}
            }
          }
        }
      }
    }
  }
  
  define page valuationMainBuilding(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Main Building")}
      table {
        row {
          block("datawidth") {
            group("Property Summary 1") {
              groupitem { label("Lot") { output(v.lot) } }
              groupitem { label("Plan") { output(v.plan) } }
              groupitem { label("Plan No.") { output(v.planNumber) } }
              groupitem { label("Proprietor"){ output(v.proprietor) }}
              groupitem { label("Zoning"){ output(v.zoning) }}
              groupitem { label("Council"){ output(v.council) }}
              groupitem { label("Instrument"){ output(v.instrument) }}
            }
            group("Property Summary 3") { 
              groupitem { label("Current Use") { output(v.currentUse) } } 
              groupitem { label("Main Building"){ output(v.mainBuilding) }}
              groupitem { label("Built About"){ output(v.builtAbout) }}
              groupitem { label("Additions"){ output(v.additions) }}
              groupitem { label("Heritage Issues"){ output(v.heritageIssues) }}
              groupitem { label("Env. Issues"){ output(v.envIssues) }}
              groupitem { label("Ess. Repairs"){ output(v.essRepairs) }}
            }
          }
          block("datawidth") {
            group("Property Summary 2") { 
              groupitem { label("Site Dims") { output(v.siteDims) } }
              groupitem { label("Site Area") { output(v.siteArea) } }
              groupitem { label("Car Accom.") { output(v.carAccommodation) } }
              groupitem { label("Car Area"){ output(v.carArea) }}
              groupitem { label("Living Area"){ output(v.livingArea) }}
              groupitem { label("Outdoor Area"){ output(v.outdoorArea) }}
              groupitem { label("Other Area"){ output(v.otherArea) }}
            }
            group("Property Summary 4") {
              groupitem { label("Actual Rent (pw)") { output(v.actualRent) } }
              groupitem { label("Marketability"){ output(v.marketability) }}
              groupitem { label("View"){ output(v.view) }}
            }
          }
        }
      }
    }
  }
  
  define page valuationRisk(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    derive viewPage from v
  }
  
  define page valuationLand(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    derive viewPage from v
  }
  
  define page valuationSales(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    derive viewPage from v
  }
  