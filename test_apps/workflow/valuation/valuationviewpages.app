module valuationviewpages
    
section pages

  define page valuationProperty(v : Valuation) {
    main()
    define sidebar() {
      valuationRequestSidebar(v.valuationRequest)
    }
    define body() {
      header{text(v.name) text("Property Summary")}
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
  
  define page valuationMainBuilding(v : Valuation) {
    main()
    define sidebar() {
      valuationRequestSidebar(v.valuationRequest)
    }
    define body() {
      header{text(v.name) text("Main Building")}
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
  
  define page valuationRisk(v : Valuation) {
    main()
    define sidebar() {
      valuationRequestSidebar(v.valuationRequest)
    }
    derive viewPage from v
  }
  
  define page valuationLand(v : Valuation) {
    main()
    define sidebar() {
      valuationRequestSidebar(v.valuationRequest)
    }
    derive viewPage from v
  }
  
  define page valuationSales(v : Valuation) {
    main()
    define sidebar() {
      valuationRequestSidebar(v.valuationRequest)
    }
    derive viewPage from v
  }