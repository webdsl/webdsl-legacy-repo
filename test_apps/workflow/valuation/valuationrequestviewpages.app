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
              groupitem { label("Purpose"){ output(v.purpose) }}
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
              groupitem { label("Alternative Bill To") { output(v.alternativeBill) } }
              groupitem { label("Fee Scale") { output(v.feeScale) } }
            }
            group("Comments") { 
              groupitem { label("") { output(v.quoteComments) } } 
            }
            group("Total") { 
              groupitem { label("Other") { output(v.other) } } 
            }
          }
          block("datawidth") {
            group("Quote Terms") { 
              groupitem { label("Quote Terms") { output(v.quoteTerms) } } 
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
    define body() {
      header{text("Risk Analysis")}
      table {
        row {
          block("datawidth") {
            group("Risk Analysis") {
              groupitem { label("Location") { output(v.locationRisk) } }
              groupitem { label("Land") { output(v.landRisk) } }
              groupitem { label("Environmental Issues") { output(v.environmentalIssuesRisk) } }
              groupitem { label("Improvements"){ output(v.improvementsRisk) }}
              groupitem { label("Reduced Value"){ output(v.reducedValueRisk) }}
              groupitem { label("Market Volatility"){ output(v.marketVolatilityRisk) }}
              groupitem { label("Local Economy Impact"){ output(v.localEconomyImpactRisk) }}
              groupitem { label("Market Segment Condition"){ output(v.marketSegmentConditionRisk) }}
            }
          }
        }
      }
    }
  }
  
  define page valuationLand(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Land")}
      table {
        row {
          block("datawidth") {
            group("Land") {
              groupitem { label("Property Identification") { output(v.propertyIdentification) } }
              groupitem { label("Has Title Been Searched") { output(v.hasTitleBeenSearched) } }
              groupitem { label("Zoning Effect") { output(v.zoningEffect) } }
              groupitem { label("Location"){ output(v.location) }}
              groupitem { label("Site Description and Access"){ output(v.siteDescriptionAndAccess) }}
            }
          }
        }
      }
    }
  }
  
  define page valuationSales(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Sales")}
      table {
        row {
          block("datawidth") {
            group("Sales") {
              groupitem { label("Sales Evidence") { output(v.salesEvidence) } }
              groupitem { label("Level of Activity") { output(v.levelOfActivity) } }
              groupitem { label("Recent Direction") { output(v.recentDirection) } }
              groupitem { label("Multi Tier"){ output(v.multiTier) }}
              groupitem { label("Latest Sale Date"){ output(v.latestSaleDate) }}
              groupitem { label("Latest Sale Price"){ output(v.latestSalePrice) }}
              groupitem { label("Latest Sale Comment"){ output(v.latestSaleComment) }}
            }
          }
        }
      }
    }
  }
  
  define page valuationSecuritisation(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Sales")}
      table {
        row {
          block("datawidth") {
            group("Effects") {
              groupitem { label(""){ output(v.effects) }}
            }
          }
          block("datawidth") {
            group("Extensions") {
              groupitem { label(""){ output(v.extensions) }}
            }
          }
        }
      }
    }
  }
  
  define page valuationAssessment(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Valuation and Assessment")}
      table {
        row {
          block("datawidth") {
            group("Assessment") {
              groupitem { label("Interest Value") { output(v.interest) } }
              groupitem { label("Val Component") { output(v.component) } }
              groupitem { label("Rental Value ($)") { output(v.rentalValue) } }
              groupitem { label("Replacement"){ output(v.replacement) }}
              groupitem { label("Other Assessment"){ output(v.otherAssessment) }}
            }
            group("Building Erection") { 
              groupitem { label("To Be Erected") { output(v.toBeErected) } } 
              groupitem { label("Builder"){ output(v.builder) }}
              groupitem { label("Date"){ output(v.buildingDate) }}
              groupitem { label("Price"){ output(v.buildingPrice) }}
              groupitem { label("Cost"){ output(v.buildingCost) }}
            }
          }
          block("datawidth") {
            group("Market Value") { 
              groupitem { label("Assess. Type") { output(v.assessmentType) } }
              groupitem { label("Land") { output(v.land) } }
              groupitem { label("Improvements") { output(v.improvements) } }
              groupitem { label("Market Value"){ output(v.marketValue) }}
            }
          }
        }
      }
    }
  }
  
  define page valuationComments(v : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(v)
    }
    define body() {
      header{text("Valuation and Assessment")}
      table {
        row {
          block("datawidth") {
            group("Additional Comments") {
              groupitem { label("") { output(v.additionalComments) } }
            }
          }
        }
      }
    }
  }
  