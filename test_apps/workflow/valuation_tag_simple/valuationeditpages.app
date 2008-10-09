module valuationeditpages

section pages

  define page editValuationProperty(v : Valuation) {
    main()
    define sidebar() {
      valuationRequestSidebar(v.valuationRequest)
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
  
  define page editValuationMainBuilding(v : Valuation) {
    main()
    define sidebar() {
      valuationRequestSidebar(v.valuationRequest)
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
  
  define page editValuationRisk(v : Valuation) {
    main()
    define sidebar() {
      valuationRequestSidebar(v.valuationRequest)
    }
    derive editPage from v
  }
  
  define page editValuationLand(v : Valuation) {
    main()
    define sidebar() {
      valuationRequestSidebar(v.valuationRequest)
    }
    derive editPage from v
  }
  
  define page editValuationSales(v : Valuation) {
    main()
    define sidebar() {
      valuationRequestSidebar(v.valuationRequest)
    }
    derive editPage from v
  }