module stringselectdata

section stringselect definitions

  string-select-entity State{"NSW", "Queensland", "Victoria", "Northern Territory", "Western Australia", "South Australia", "Tasmania"}
  string-select-entity PostCode{"2026", "2088", "2132", "2342"}
  
  // Property
  string-select-entity PropertyCategory{"Commercial", "Industrial", "Miscellaneous", "Residential", "Retail", "Unknown"}
  string-select-entity PropertyType{"Factories/Warehouses", "Flat Building", "Hospitality/Restaurant", "House", "Medical", "Multiple Shop Building", "Office Building", "Office Building - Rent", "Office Unit - Rent", "Office Units", "Office/Warehouse Units", "Other", "Rent Review", "Shop Building", "Shopping Centre", "Sporting/Recreation/Clubs", "Subdivision", "Unit", "Unit Development", "Unknown", "Warehouse Development", "Warehouse Unit"}
  
  // Specifications
  string-select-entity ReportType{"Long Report", "Progress Report", "Restricted Report", "Short Report"}
  string-select-entity Purpose{"Balance Sheet", "Capital Gains", "Depreciation", "Family Law", "GST", "Insurance", "Market", "Mortage Security", "Rental Review", "Sales Evidence", "Stamp Duty", "Trust and Super", "Unknown", "Valuation"}
  string-select-entity Comparison{"Inferior", "Similar", "Superior"} //???? niet te vinden
  
  // Client
  string-select-entity Contact {"David Bloomfield", "Peter Abbott", "Mark Addison"}
  
  // Quote
  string-select-entity FeeScale {"Commercial", "Residential"}
  string-select-entity QuoteTerm {"Outside Sydney Metro Area", "Far Outside Sydney Metro Area", "Dual Occupancy", "Mortgage In Possession", "Rescheduling Fee", "1 to less than 5 acres", "5 to less than 25 acres", "25 to less than 50 acres", "> 50 acres", "TBE / Non-Completed", "Other Charges", "Commercial - Multiple tenancies", "Progess Report", "Re-Assignment Report", "Credit Card Surcharge", "Cancellation Fee (Full)", "Cancellation Fee (Half)"}

  // Property summary 1
  string-select-entity Plan {"SP", "DP"}
  string-select-entity ZoningOption{"Gen Bus", "Gen Bus 3(a)", "Mixed Zone 10", "Res 2(a)", "Res 2(b)", "Res 2(c)"}
  string-select-entity CouncilOption{"Ashfield", "Auburn", "Bankstown", "Baulkham Hills", "Blacktown", "Blue Mountains", "Botany", "Burwood", "Camden", "CampbellTown", "Canada Bay", "Canterbury", "Cessnock", "Fairfield", "GosFord", "Hawkesbury", "Holroyd", "Hornsby", "Hunters Hill", "Kiama", "Kogarah", "Ku-ring-gai", "Lake Macquarie", "Lane Cove", "Leichhardt", "Liverpool", "Maitland", "Manly", "Marricksville", "Mosman", "Newcastle", "North Sydney", "Parramatta", "Penrith", "Pittwater", "Port Stephens", "Randwick", "Rockdale", "Ryde", "Shell Harbour", "South Sydney", "Strathfield", "Sutherland", "Sydney City", "Warringah", "Waverley", "Willoughby", "Wingarribbee", "Wollondilly", "Wollongong", "Woollahra", "Wyong"}
  string-select-entity Instrument {"Ashfield LEP", "Auburn PSO", "Bankstown PSO", "Baulkham hills LEP", "Blacktown LEP", "Blue Mountains LEP", "Botany LEP", "Burwood PSO", "Camden LEP", "CampbellTown LEP", "Canada Bay LEP", "Canterbury PSO", "Fairfield LEP", "GosFord PSO", "Hawkesbury LEP", "Holroyd LEP", "Hornsby LEP", "Hunters Hill LEP", "Hurstville LEP", "Kiama LEP", "Kogarah LEP", "Ku-ring-gai PSO", "Lake Macquarie LEP", "Lane Cove LEP", "Leichhardt LEP", "Liverpool LEP", "Manly LEP", "Marricksville PSO", "Mosman LEP", "North Sydney LEP", "Parramatta LEP", "Penrith LEP", "Pittwater LEP", "Randwick LEP", "Rockdale LEP", "Ryde LEP", "Shell Harbour LEP", "South Sydney LEP", "Strathfield PSO", "Sutherland LEP", "Sydney City LEP", "Warringah LEP", "Waverley LEP", "Willoughby LEP", "Wingarribbee LEP", "Wollondilly LEP", "Wollongong LEP", "Woollahra LEP", "Wyong LEP"}
  // Property summary 2
  string-select-entity SiteDims {"Irregular", "N/A", "Regular"}
  string-select-entity CarAccommodation {"Carport", "Carspace", "DLUG", "Double Carport", "Double Carspace", "N/A", "Off-Street Parking", "Security Carspace", "Single Carport", "SLUG", "SLUG and Carport", "Tandem", "TLUG", "Undercover Carspace"}  
  // Property summary 3
  string-select-entity CurrentUseOption {"Office", "Owner occupied", "Residential", "Residential - Tenanted", "Residential - Vacant", "Rural", "Shop", "Vacant Land"}
  string-select-entity MainBuildingOption {"N/A", "Detached House", "Dwelling", "House", "Lower Duplex", "Single Storey Detached Dwelling", "Single Storey Dwelling", "Single Storey House", "Single Storey Semi", "Single Storey Villa", "Split Level Dwelling", "Terrace", "Three Storey Unit Building", "Townhouse", "Two Storey Duplex", "Two Storey Dwelling", "Two Storey House", "Two Storey Unit Building", "Unit Building", "Upper Duplex", "Vacant Land"}
  string-select-entity BuiltAboutOption {"1900", "1910", "1920", "1930", "1940", "1950", "1960", "1970", "1980", "1990", "2000", "2010"}
  string-select-entity AdditionsOption {"N/A", "Fully Renovated", "Internally Renovated", "Partially Renovated", "Recently Renovated", "Renovated Bathroom", "Renovated Kitchen"}
  string-select-entity HeritageIssuesOption {"N/A", "Heritage Conservation Area", "Heritage Listed", "Local Heritage Order", "Part Heritage Listed"}
  string-select-entity EnvIssuesOption {"N/A", "Close to Industrial Property", "Flood Prone", "High voltage power lines", "Subject to D.E."}
  string-select-entity EssRepairsOption {"N/A", "Bathroom Renovation", "Finish Renovation", "Kitchen Renovation", "Needs Renovation", "Paintwork", "Some Paintwork"}
  // Property summary 4
  string-select-entity Marketability {"Poor", "Fair", "Good"}
  
  // Main Building
  string-select-entity StyleOption {"N/A", "Appartment", "Cottage", "Detached House", "House", "Single Storey Cottage", "Single Storey Dwelling", "Single Storey House", "Single Storey Semi", "Single Storey Unit Building", "Single Storey Villa", "Split Level", "Terrace", "Three Storey Unit Building", "Townhouse", "Two Storey Duplex", "Two Storey Dwelling", "Two Storey House", "Two Storey Unit Building", "Unit", "Vacant Land"}
  string-select-entity StreetAppeal {"Poor", "Fair", "Good", "Excellent"}
  string-select-entity WallAndRoof {"Besserblock", "Brick", "Brick and Concrete", "Brick and Iron", "Brick and Tile", "Brick Veneer", "Brick Veneer and Concrete Tile", "Brick/Fibro and Tile", "Brick/Weatherboard and Tile", "Clad and Tile", "Fibro", "Fibro and Iron", "Fibro and Tile", "Granite", "N/A", "Permalum", "Permalum and Tile", "Rendered Brick and Tile", "Timber", "Timber and Iron", "Timber and Tile", "Weatherboard", "Weatherboard and Tile"}
  string-select-entity WindowFrames{"N/A", "Aluminium", "Timber", "Timber and Aluminium"}
  string-select-entity InteriorLinings{"Brick", "Brick and GypRock", "Brick Veneer", "Concrete", "Fibro", "Gyprock", "N/A", "Plaster", "Plasterboard", "Render", "Timber"}
  string-select-entity IntExtCondition{"N/A", "Unknown", "TBE", "Poor", "Fair", "Good", "Very Good", "Excellent"}
  string-select-entity Flooring{"N/A", "Carpet on Concrete", "Carpet on Timber", "Concrete", "Concrete and Timber", "Polished Timber", "Timber"}
  string-select-entity InteriorLayout{"Functional"}
  
  // Risk
  string-select-entity Analysis{"1", "2", "3", "4", "5"}
  
  // Land
  string-select-entity PropertyIdentification{"Contract of Sale", "Council Rates Notice", "Council Records", "CT Search", "Registered Plan", "Valuer Generals Notice"}
  string-select-entity Market{"Weakening", "Steady", "Strengthening"}
  
  // Securitisation Req.
  string-select-entity Effect {"No environmental hazards, or dukes there of", "Not adversely affected by rail", "Not affected by electrical easements", "Not affected by encroachments", "Not affected by heritage issues", "Not affected by main road acquisition", "Not affected by pests", "Not subject to flooding or landslip"}
  string-select-entity Extension {"Adelaide Bank Ltd", "AFIG Wholesale Ltd", "AIMS Securitisation Ltd", "ARES Capital Management Pty Ltd", "Australian Wholesale Lending Pty Ltd", "First Mortgage Company Home Loans Pty Ltd", "GE Mortgage Insurance Services", "GEL Custodians Ltd", "Genworth Financial Mortgage Insurance Pty Ltd", "Homeloans Ltd", "ING Bank (Australia) Ltd", "JP Morgan Australia Ltd", "Loancorp", "Macquarie Bank Ltd", "Macquarie Securitisation Ltd", "Mobius Financial Services Pty Ltd", "Mortgage Management Corporation", "Origin Mortgage Management Services", "Permanent Custodians Ltd", "Permanent Trustee Australia Ltd", "Permanent Trustee Company Ltd", "Perpetual Ltd", "Perpetual Trustee Company Ltd", "PMI Indemnity Ltd", "PMI Mortgage Insurance", "Residential Mortgage Trust", "Resimac Ltd", "Rismark International", "Royal & Sun Alliance LMI", "Royal Guardian Mortgage Management Pty Ltd", "The Mortgage Insurance Company", "Tonto Hoe Loans Australia Pty Ltd", "Trust Company Fiduciary Service Ltd", "Vero Lenders Mortgage Insurance", "Western Lenders Mortgage Insurance"}
    
  // Valuation & Assessment
  string-select-entity Interest {"Company Title Vacant Possession", "Fee Simple", "Fee Simple Possession", "Fee Simple Vacant", "Fee Simple Vacant Possession"}
  string-select-entity Component {"Existing Property", "TBE"}
  string-select-entity AssessmentType {"Land and Building", "Unit", "Vacant Land"}
  
  
/*  
  function nothing() {
    
  }*/

  /* toch beter los
    string-select-entity Client{"Topline Foods", "Philips", "Nokia"}
    string-select-entity Broker{"Barneveld Schuurman Makelaars", "Jeltes ten Hoor", "Rooseboom"}
  */