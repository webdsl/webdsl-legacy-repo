module valuationprocedures

section procedures

  auto procedure valuationWorkflow(v : ValuationRequest) {
    process {
      enableEditRequest(v)
      ; bookValuation(v)
      ; repeat {
          editValuationScreens(v)
        } until finalizeValuation(v)
      ; while (true) {
          editValuationScreens(v)
        }
        xor
        approveValuation(v)
      ; disableEditRequest(v)
      ; sendValuation(v)
    }
  }
  
  auto procedure editValuationScreens(v : ValuationRequest) {
    process {
      editValuationProperty(v) 
      xor editValuationMainBuilding(v) 
      xor editValuationRisk(v) 
      xor editValuationLand(v) 
      xor editValuationSales(v) 
      xor editValuationSecuritisation(v) 
      xor editValuationAssessment(v) 
      xor editValuationComments(v)
    }
  }
  
  auto procedure enableEditRequest(v : ValuationRequest) {
    do {
      v.startEditValuationRequestDetails();
      v.startEditValuationRequestBooking();
      v.startEditValuationRequestQuote();
    }
  }
  
  auto procedure disableEditRequest(v : ValuationRequest) {
    do {
      v.editValuationRequestDetails.disable();
      v.editValuationRequestBooking.disable();
      v.editValuationRequestQuote.disable();
    }
  }

  procedure bookValuation(v : ValuationRequest) {
    who { securityContext.principal != null && securityContext.principal.hasBookingRights() }
    view {
      main()
      define local body() {
        header{text("Book Valuation")}
        form {
          table {
            row {
              block("datawidth") {
                group("Booking") {
                  groupitem { label("Valuer") { input(v.valuer) } }
                  groupitem { label("Date") { input(v.bookingDate) } }
                  groupitem { label("Time") { input(v.bookingTime) } }
                  groupitem { label("Contact"){ input(v.bookingContact) } }
                  groupitem { label("Phone"){ input(v.bookingPhone) } }
                  groupitem { label("Notes") { input(v.bookingNotes) } } 
                }
              }
              row { action("Book Valuation", do()) }
            }
          }
        }
      }
    }
    do { 
      v.status := initValuationRequestStatus2; 
      v.bookedBy := securityContext.principal;
      v.persist();
      v.persist();
    }
  }
  
  procedure editValuationRequestDetails(v : ValuationRequest) {
    who {
      canEditValuationRequest(v)
    }
    view {
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
                  groupitem { label("Purpose"){ input(v.purpose) }}
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
              row { action("Save changes", do()) }
            }
          }
        }
      }
    }
    do {
      v.persist();
    }
    processed { v.editValuationRequestDetails.enable(); }
  }

  procedure editValuationRequestBooking(v : ValuationRequest) {
    who {
      canEditValuationRequest(v)
    }
    view {
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
              row { action("Save changes", do()) }
            }
          }
        }
      }
    }
    do {
      v.persist();
    }
    processed { v.editValuationRequestBooking.enable(); }
  }

  procedure editValuationRequestQuote(v : ValuationRequest) {
    who {
      canEditValuationRequest(v)
    }
    view {
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
                  groupitem { label("Alternative Bill To") { input(v.alternativeBill) } }
                  groupitem { label("Fee Scale") { input(v.feeScale) } }
                }
                group("Comments") { 
                  groupitem { label("") { input(v.quoteComments) } } 
                }
                group("Total") { 
                  groupitem { label("Other") { input(v.other) } } 
                }
              }
              block("datawidth") {
                group("Quote Terms") { 
                  groupitem { label("Quote Terms") { input(v.quoteTerms) } } 
                }
              }
            }
            row { action("Save changes", do()) }
          }
        }
      }
    }
    do {
      v.persist();
    }
    processed { v.editValuationRequestQuote.enable(); }
  }

  procedure editValuationProperty(v : ValuationRequest) {
    who {
      canEditValuation(v)
    }
    view {
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
                  groupitem { label("Plan") { input(v.plan) } } // selectStringInput(v.plan, )
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
            row { action("Save changes", do()) }
          }
        }
      }
    }
    do {
      v.persist();
    }
  }
  
  procedure editValuationMainBuilding(v : ValuationRequest) {
    who {
      canEditValuation(v)
    }
    view {
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
                group("Main Building") {
                  groupitem { label("Building Style") { input(v.buildingStyle) } }
                  groupitem { label("Street Appeal") { input(v.streetAppeal) } }
                  groupitem { label("Wall and Roof") { input(v.wallAndRoof) } }
                  groupitem { label("Window Frames"){ input(v.windowFrames) }}
                  groupitem { label("Interior Linings"){ input(v.interiorLinings) }}
                  groupitem { label("Internal Condition"){ input(v.internalCondition) }}
                  groupitem { label("External Condition"){ input(v.externalCondition) }}
                }
              }
            }
            row { action("Save changes", do()) }
          }
        }
      }
    }
    do {
      v.persist();
    }
  }
  
  procedure editValuationRisk(v : ValuationRequest) {
    who {
      canEditValuation(v)
    }
    view {
      main()
      define sidebar() {
        valuationRequestSidebar(v)
      }
      define body() {
        header{text("Risk Analysis")}
        form {
          table {
            row {
              block("datawidth") {
                group("Risk Analysis") {
                  groupitem { label("Location") { input(v.locationRisk) } }
                  groupitem { label("Land") { input(v.landRisk) } }
                  groupitem { label("Environmental Issues") { input(v.environmentalIssuesRisk) } }
                  groupitem { label("Improvements"){ input(v.improvementsRisk) }}
                  groupitem { label("Reduced Value"){ input(v.reducedValueRisk) }}
                  groupitem { label("Market Volatility"){ input(v.marketVolatilityRisk) }}
                  groupitem { label("Local Economy Impact"){ input(v.localEconomyImpactRisk) }}
                  groupitem { label("Market Segment Condition"){ input(v.marketSegmentConditionRisk) }}
                }
              }
            }
            row { action("Save changes", do()) }
          }
        }
      }
    }
    do {
      v.persist();
    }
  }
  
  procedure editValuationLand(v : ValuationRequest) {
    who {
      canEditValuation(v)
    }
    view {
      main()
      define sidebar() {
        valuationRequestSidebar(v)
      }
      define body() {
        header{text("Land")}
        form {
          table {
            row {
              block("datawidth") {
                group("Land") {
                  groupitem { label("Property Identification") { input(v.propertyIdentification) } }
                  groupitem { label("Has Title Been Searched") { input(v.hasTitleBeenSearched) } }
                  groupitem { label("Zoning Effect") { input(v.zoningEffect) } }
                  groupitem { label("Location"){ input(v.location) }}
                  groupitem { label("Site Description and Access"){ input(v.siteDescriptionAndAccess) }}
                }
              }
            }
            row { action("Save changes", do()) }
          }
        }
      }
    }
    do {
      v.persist();
    }
  }
  
  procedure editValuationSales(v : ValuationRequest) {
    who {
      canEditValuation(v)
    }
    view {
      main()
      define sidebar() {
        valuationRequestSidebar(v)
      }
      define body() {
        header{text("Sales Evidence")}
        form {
          table {
            row {
              block("datawidth") {
                group("Sales") {
                  groupitem { label("Sales Evidence") { input(v.salesEvidence) } }
                  groupitem { label("Level of Activity") { input(v.levelOfActivity) } }
                  groupitem { label("Recent Direction") { input(v.recentDirection) } }
                  groupitem { label("Multi Tier"){ input(v.multiTier) }}
                  groupitem { label("Latest Sale Date"){ input(v.latestSaleDate) }}
                  groupitem { label("Latest Sale Price"){ input(v.latestSalePrice) }}
                  groupitem { label("Latest Sale Comment"){ input(v.latestSaleComment) }}
                }
              }
            }
            row { action("Save changes", do()) }
          }
        }
      }
    }
    do {
      v.persist();
    }
  }
  
  procedure editValuationSecuritisation(v : ValuationRequest) {
    who {
      canEditValuation(v)
    }
    view {
      main()
      define sidebar() {
        valuationRequestSidebar(v)
      }
      define body() {
        header{text("Valuation Securitisation")}
        form {
          table {
            row {
              block("datawidth") {
                group("Effects") {
                  groupitem { label(""){ input(v.effects) }}
                }
              }
              block("datawidth") {
                group("Extensions") {
                  groupitem { label(""){ input(v.extensions) }}
                }
              }
            }
            row { action("Save changes", do()) }
          }
        }
      }
    }
    do {
      v.persist();
    }
  }
  
  procedure editValuationAssessment(v : ValuationRequest) {
    who {
      canEditValuation(v)
    }
    view {
      main()
      define sidebar() {
        valuationRequestSidebar(v)
      }
      define body() {
        header{text("Valuation and Assessment")}
        form {
          table {
            row {
              block("datawidth") {
                group("Assessment") {
                  groupitem { label("Interest Value") { input(v.interest) } }
                  groupitem { label("Val Component") { input(v.component) } }
                  groupitem { label("Rental Value ($)") { input(v.rentalValue) } }
                  groupitem { label("Replacement"){ input(v.replacement) }}
                  groupitem { label("Other Assessment"){ input(v.otherAssessment) }}
                }
                group("Building Erection") { 
                  groupitem { label("To Be Erected") { input(v.toBeErected) } } 
                  groupitem { label("Builder"){ input(v.builder) }}
                  groupitem { label("Date"){ input(v.buildingDate) }}
                  groupitem { label("Price"){ input(v.buildingPrice) }}
                  groupitem { label("Cost"){ input(v.buildingCost) }}
                }
              }
              block("datawidth") {
                group("Market Value") { 
                  groupitem { label("Assess. Type") { input(v.assessmentType) } }
                  groupitem { label("Land") { input(v.land) } }
                  groupitem { label("Improvements") { input(v.improvements) } }
                  groupitem { label("Market Value"){ input(v.marketValue) }}
                }
              }
            }
            row { action("Save changes", do()) }
          }
        }
      }
    }
    do {
      v.persist();
    }
  }
  
  procedure editValuationComments(v : ValuationRequest) {
    who {
      canEditValuation(v)
    }
    view {
      main()
      define sidebar() {
        valuationRequestSidebar(v)
      }
      define body() {
        header{text("Additional Comments")}
        form {
          table {
            row {
              block("datawidth") {
                group("Additional Comments") {
                  groupitem { label("") { input(v.additionalComments) } }
                }
              }
            }
            row { action("Save changes", do()) }
          }
        }
      }
    }
    do {
      v.persist();
    }
  }
  
  procedure finalizeValuation(v : ValuationRequest) {
    who { canEditValuation(v) }
    when { v.editValuationProperty.isProcessed 
           && v.editValuationMainBuilding.isProcessed 
            /*          && v.editValuationRisk.isProcessed 
                      && v.editValuationLand.isProcessed 
                      && v.editValuationSales.isProcessed 
                      && v.editValuationSecuritisation.isProcessed 
                      && v.editValuationAssessment.isProcessed */
           && v.editValuationComments.isProcessed }
    do {
      // set to "Awaiting Approval"
      v.status := initValuationRequestStatus4;
      v.persist();
    }
  }
  
  procedure approveValuation(v : ValuationRequest) {
    who { canApproveValuations() }
    do {
      // set to "Approved"
      v.status := initValuationRequestStatus5;
      v.persist();
    }
  }
  
  procedure sendValuation(v : ValuationRequest) {
    who { canSendValuations() }
    do {
      // set to "Sent"
      v.status := initValuationRequestStatus6;
      v.persist();
    }
  }
  
  /** 
   * Functions and templates
   */
  
  function unbookedValuationsExist() : Bool {
    var unbookedValuations : Bool := false;
    for (v : ValuationRequest) {
      if (v.bookValuation != null && v.bookValuation.isEnabled) {
        unbookedValuations := true;
      }
    }
    return unbookedValuations;
  }
  
  function viewFinalizeValuationLink(v : ValuationRequest) : Bool {
    return (
      valuationRequestHasProcedures(v) && 
      securityContext.principal != null && 
      v.bookValuation != null && 
      v.bookValuation.isEnabled
      //v.bookValuation != null && v.bookValuation.canPerform()
    );
  }
  
  function viewBookValuationLink(v : ValuationRequest) : Bool {
    return (
      valuationRequestHasProcedures(v) && 
      securityContext.principal != null && 
      securityContext.principal.hasBookingRights() && 
      v.bookValuation != null && 
      v.bookValuation.isEnabled
      //v.bookValuation != null && v.bookValuation.canPerform()
    );
  }
  
  function unbookedValuations() : Bool {
    return (securityContext.principal != null && securityContext.principal.hasBookingRights() && unbookedValuationsExist());
  }
  
  function valuationRequestHasEditRequestProcedures(v : ValuationRequest) : Bool {
    return (
      securityContext.principal != null && 
      canEditValuationRequest(v) && (
        v.editValuationRequestDetails != null && 
        v.editValuationRequestDetails.isEnabled 
        || 
        v.editValuationRequestBooking != null && 
        v.editValuationRequestQuote.isEnabled 
        ||
        v.editValuationRequestQuote != null && 
        v.editValuationRequestQuote.isEnabled
      )
    );
  }
  
  function valuationRequestHasEditValuationProcedures(v : ValuationRequest) : Bool {
    return (
      securityContext.principal != null && 
      canEditValuation(v) && (
        v.editValuationSales != null && 
        v.editValuationSales.isEnabled 
        || 
        v.editValuationLand != null && 
        v.editValuationLand.isEnabled 
        ||
        v.editValuationRisk != null && 
        v.editValuationRisk.isEnabled 
        || 
        v.editValuationMainBuilding != null && 
        v.editValuationMainBuilding.isEnabled 
        ||
        v.editValuationProperty != null && 
        v.editValuationProperty.isEnabled
      )
    );
  }
  
  define bookValuationTasks() {
    if (unbookedValuations()) {
      par {
        section() {
          header(){"Booking tasks"}
          list {
            for (v : ValuationRequest) {
              if (valuationRequestHasProcedures(v) && v.bookValuation != null && v.bookValuation.isEnabled) {    
                listitem{ navigate(bookValuation(v)) { 
                  text("Book ") text(v.fullAddress) 
                } }
              }
            }
          }
        }
      }
    }
  }  