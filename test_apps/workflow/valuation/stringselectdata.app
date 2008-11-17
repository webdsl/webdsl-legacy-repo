module stringselectdata

section stringselect definitions

  string-select-entity Plan {"SP", "DP"}
  string-select-entity Instrument {"Mosman LEP", "North Sydney LEP", "Rest to be done"}
  string-select-entity SiteDims {"Irregular", "N/A", "Regular"}
  string-select-entity CarAccommodation {"Carport", "Carspace", "DLUG", "Double Carport", "Double Carspace", "N/A", "Off-Street Parking", "Security Carspace", "Single Carport", "SLUG", "SLUG and Carport", "Tandem", "TLUG", "Undercover Carspace"}  
  string-select-entity Marketability {"Poor", "Fair", "Good"}
  string-select-entity StreetAppeal {"Poor", "Fair", "Good", "Excellent"}
  string-select-entity WallAndRoof {"Besserblock", "Brick", "Brick and Concrete", "Brick and Iron", "Brick and Tile", "Brick Veneer", "Brick Veneer and Concrete Tile", "Brick/Fibro and Tile", "Brick/Weatherboard and Tile", "Clad and Tile", "Fibro", "Fibro and Iron", "Fibro and Tile", "Granite", "N/A", "Permalum", "Permalum and Tile", "Rendered Brick and Tile", "Timber", "Timber and Iron", "Timber and Tile", "Weatherboard", "Weatherboard and Tile"}
  string-select-entity WindowFrames{"N/A", "Aluminium", "Timber", "Timber and Aluminium"}
  string-select-entity InteriorLinings{"Brick", "Brick and GypRock", "Brick Veneer", "Concrete", "Fibro", "Gyprock", "N/A", "Plaster", "Plasterboard", "Render", "Timber"}
  string-select-entity IntExtCondition{"N/A", "Unknown", "TBE", "Poor", "Fair", "Good", "Very Good", "Excellent"}
  string-select-entity Flooring{"N/A", "Carpet on Concrete", "Carpet on Timber", "Concrete", "Concrete and Timber", "Polished Timber", "Timber"}
  string-select-entity InteriorLayout{"Functional"}
  string-select-entity Analysis{"1", "2", "3", "4", "5"}
  string-select-entity PropertyIdentification{"Contract of Sale", "Council Rates Notice", "Council Records", "CT Search", "Registered Plan", "Valuer Generals Notice"}
  string-select-entity Market{"Weakening", "Steady", "Strengthening"}
  string-select-entity State{"NSW", "Queensland", "Victoria", "Northern Territory", "Western Australia", "South Australia", "Tasmania"}
  string-select-entity PostCode{"2026", "2088", "2132", "2342"}
  string-select-entity PropertyCategory{"Residential", "Offices", "Kekstra"}
  string-select-entity PropertyType{"House", "Flat", "Appartment"}
  string-select-entity ReportType{"Short Report", "Extensive Report", "Too long Report"}
  string-select-entity Propose{"Market", "Market too", "What other values?"}
  string-select-entity Comparison{"Inferior", "Similar", "Superior"}
/*  
  function nothing() {
    
  }*/

  /* toch beter los
    string-select-entity Client{"Topline Foods", "Philips", "Nokia"}
    string-select-entity Broker{"Barneveld Schuurman Makelaars", "Jeltes ten Hoor", "Rooseboom"}
  */