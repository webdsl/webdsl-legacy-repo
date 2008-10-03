module valuationrequestviewpages

section pages

  define page valuationRequest(r : ValuationRequest) { 
    main()
    define sidebar() {
      valuationRequestSidebar(r)
    }
    define body() {
      header{text("Request Details")}
      table {
        row {
          block("datawidth") {
            group("Client") {
              groupitem { label("Client") { output(r.client) } }
              groupitem { label("Primary Interest") { output(r.primaryInterest) } }
              groupitem { label("Broker") { output(r.broker) } }
              groupitem { label("Lender Contact"){ output(r.lenderContact) }}
              groupitem { label("Reference"){ output(r.reference) }}
            }
            group("Property") { 
              groupitem { label("Address") { output(r.address) } } 
              groupitem { label("Suburb"){ output(r.suburb) }}
              groupitem { label(""){ output(r.state) }}
              groupitem { label("Post Code"){ output(r.postCode) }}
              groupitem { label("Category"){ output(r.category) }}
              groupitem { label("Type"){ output(r.type) }}
            }
            group("Comments") { 
              groupitem { label("") { output(r.comments) } } 
            }
          }
          block("datawidth") {
            group("Specifications") { 
              groupitem { label("Report Type") { output(r.reportType) } } 
              groupitem { label("Propose"){ output(r.propose) }}
            }
            group("Applicant") { 
              groupitem { label("Name") { output(r.applicantName) } } 
              groupitem { label("Phone"){ output(r.applicantPhone) }}
            }
            group("Inspection Contact") { 
              groupitem { label("Name") { output(r.inspectionName) } } 
              groupitem { label("Phone"){ output(r.inspectionPhone) }}
            }
            group("Miscellaneous") { 
              groupitem { label("Purchase") { output(r.purchase) } } 
              groupitem { label("Owner Est."){ output(r.ownerEst) }}
              groupitem { label("Tender"){ output(r.tender) }}
              groupitem { label("Status"){ output(r.status) }}
            }
          }
        }
      }
    }
  }

  define page valuationRequestBooking(r : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(r)
    }
    define body() {
      header{text("Booking Details")}
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
              groupitem { label("Purchase Price") { text("$ ") output(r.purchase) } } 
              groupitem { label("Owner Estimation") { text("$ ") output(r.ownerEst) } }
              groupitem { label("Tender Price") { text("$ ") output(r.tender) } }
            }
            group("Location") { 
              groupitem { label("Map") { "N/A" } }
              groupitem { label("UDB Ref"){ output(r.udbRef) } }
            }
          }
          block("datawidth") {
            group("Booking") { 
              for (v : Valuation in r.valuations) {
                groupitem { label("Valuer") { output(v.valuer) } } 
              }
              groupitem { label("Date"){ output(r.bookingDate) } }
              groupitem { label("Time") { output(r.bookingTime) } } 
              groupitem { label("Contact"){ output(r.bookingContact) } }
              groupitem { label("Phone"){ output(r.bookingPhone) } }
              groupitem { label("Booked by"){ output(r.bookedBy) } }
            }
            group("Booking Notes") { 
              groupitem { label("") { output(r.bookingNotes) } } 
            }
            group("Inspection Contact") { 
              groupitem { label("Name") { output(r.inspectionName) } } 
              groupitem { label("Phone"){ output(r.inspectionPhone) } }
            }
          }
        }
      }
    }
  }
  
  // TODO - nu alleen wat mockup
  define page valuationRequestQuote(r : ValuationRequest) {
    main()
    define sidebar() {
      valuationRequestSidebar(r)
    }
    define body() {
      header{text("Quote Details")}
      table {
        row {
          block("datawidth") {
            group("Invoice Details") {
              groupitem { label("Client") { output(r.client) } }
              groupitem { label("Primary Interest") { output(r.primaryInterest) } }
            }
            group("Comments") { 
              groupitem { label("") { output(r.comments) } } 
            }
            group("Total") { 
              groupitem { label("Address") { output(r.address) } } 
            }
          }
          block("datawidth") {
            group("Quote Terms") { 
              groupitem { label("Report Type") { output(r.reportType) } } 
              groupitem { label("Propose"){ output(r.propose) }}
            }
          }
        }
      }
    }
  }
