module valuationrequesteditpages

section pages

  define page editValuationRequest(r : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(r)
    }
    define body() {
      header{text("Request Details")}
      form {
        table {
          row {
            block("datawidth") {
              group("Client") {
                groupitem { label("Client") { input(r.client) } }
                groupitem { label("Primary Interest") { input(r.primaryInterest) } }
                groupitem { label("Broker") { input(r.broker) } }
                groupitem { label("Lender Contact"){ input(r.lenderContact) }}
                groupitem { label("Reference"){ input(r.reference) }}
              }
              group("Property") { 
                groupitem { label("Address") { input(r.address) } } 
                groupitem { label("Suburb"){ input(r.suburb) }}
                groupitem { label("State"){ input(r.state) }}
                groupitem { label("Post Code"){ input(r.postCode) }}
                groupitem { label("Category"){ input(r.category) }}
                groupitem { label("Type"){ input(r.type) }}
              }
              group("Comments") { 
                groupitem { label("") { input(r.comments) } } 
              }
            }
            block("datawidth") {
              group("Specifications") { 
                groupitem { label("Report Type") { input(r.reportType) } } 
                groupitem { label("Propose"){ input(r.propose) }}
              }
              group("Applicant") { 
                groupitem { label("Name") { input(r.applicantName) } } 
                groupitem { label("Phone"){ input(r.applicantPhone) }}
              }
              group("Inspection Contact") { 
                groupitem { label("Name") { input(r.inspectionName) } } 
                groupitem { label("Phone"){ input(r.inspectionPhone) }}
              }
              group("Miscellaneous") { 
                groupitem { label("Purchase") { input(r.purchase) } } 
                groupitem { label("Owner Est."){ input(r.ownerEst) }}
                groupitem { label("Tender"){ input(r.tender) }}
                groupitem { label("Status"){ output(r.status) }}
              }
            }
            row { action("Save changes", saveValuationRequest()) }
          }
        }
      }
      action saveValuationRequest() {
        r.persist();
      }
    }
  }

  define page editValuationRequestBooking(r : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(r)
    }
    define body() {
      header{text("Booking Details")}
      form {
        table {
          row {
            block("datawidth") {
              group("Request Summary") {
                groupitem { label("Client") { output(r.client) } }
                groupitem { label("Reference") { output(r.reference) } }
                groupitem { label("Lender Contact") { output(r.lenderContact) } }
                groupitem { label("Applicant") { output(r.applicantName) } }
                groupitem { label("Request Date") { output(r.requestDate) } }
                groupitem { label("Sent Date") { output(r.sentDate) } }
                groupitem { label("Status") { output(r.status) } }
                groupitem { label("Report Type") { output(r.reportType) } }
                groupitem { label("Purchase Price") { block{text("$ ") output(r.purchase)} } } 
                groupitem { label("Owner Estimation") { block{text("$ ") output(r.ownerEst)} } }
                groupitem { label("Tender Price") { block{text("$ ") output(r.tender)} } }
              }
              group("Location") { 
                groupitem { label("Map") { "N/A" } }
                groupitem { label("UDB Ref"){ input(r.udbRef) } }
              }
            }
            block("datawidth") {
              group("Booking") { 
                for (v : Valuation in r.valuations) {
                  groupitem { label("Valuer") { input(v.valuer) } } 
                }
                groupitem { label("Date"){ input(r.bookingDate) } }
                groupitem { label("Time") { input(r.bookingTime) } } 
                groupitem { label("Contact"){ input(r.bookingContact) } }
                groupitem { label("Phone"){ input(r.bookingPhone) } }
                groupitem { label("Booked by"){ input(r.bookedBy) } }
              }
              group("Booking Notes") { 
                groupitem { label("") { input(r.bookingNotes) } } 
              }
              group("Inspection Contact") { 
                groupitem { label("Name") { input(r.inspectionName) } } 
                groupitem { label("Phone"){ input(r.inspectionPhone) } }
              }
            }
            row { action("Save changes", saveValuationRequest()) }
          }
        }
        action saveValuationRequest() {
          r.persist();
        }
      }
    }
  }

  define page editValuationRequestQuote(r : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(r)
    }
    define body() {
      header{text("Quote Details")}
      form {      
        table {
          row {
            block("datawidth") {
              group("Invoice Details") {
                groupitem { label("Client") { input(r.client) } }
                groupitem { label("Primary Interest") { input(r.primaryInterest) } }
              }
              group("Comments") { 
                groupitem { label("") { input(r.comments) } } 
              }
              group("Total") { 
                groupitem { label("Address") { input(r.address) } } 
              }
            }
            block("datawidth") {
              group("Quote Terms") { 
                groupitem { label("Report Type") { input(r.reportType) } } 
                groupitem { label("Propose"){ input(r.propose) }}
              }
            }
          }
          row { action("Save changes", saveValuationRequest()) }
        }
      }
      action saveValuationRequest() {
        r.persist();
      }
    }
  }
