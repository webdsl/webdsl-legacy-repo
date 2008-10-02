module init

section user data

  globals {
    var userLiming : User := User {
      username := "liming",
      name     := "Liming Zhu",
      password := "secret"
    };
    var userRuben : User := User {
      username := "ruben",
      name     := "Ruben Verhaaf",
      password := "secret"
    };
  }
  
section value data

  globals {
    var initPlan1 : PlanValue := PlanValue { name := "SP" };
    var initPlan2 : PlanValue := PlanValue { name := "DP" };
  }

  globals {
    var initInstrument1 : InstrumentValue := InstrumentValue { name := "Mosman LEP" };
    var initInstrument2 : InstrumentValue := InstrumentValue { name := "North Sydney LEP" };
    var initInstrument3 : InstrumentValue := InstrumentValue { name := "Rest to be done" };
/*    var initInstrument : InstrumentValue := InstrumentValue { name := "" };
    var initInstrument : InstrumentValue := InstrumentValue { name := "" };
    var initInstrument : InstrumentValue := InstrumentValue { name := "" };
    var initInstrument : InstrumentValue := InstrumentValue { name := "" };
    var initInstrument : InstrumentValue := InstrumentValue { name := "" };
    var initInstrument : InstrumentValue := InstrumentValue { name := "" };
    var initInstrument : InstrumentValue := InstrumentValue { name := "" };
    var initInstrument : InstrumentValue := InstrumentValue { name := "" };
    var initInstrument : InstrumentValue := InstrumentValue { name := "" };
*/  }
  
  globals {
    var initSiteDims1 : SiteDimsValue := SiteDimsValue { name := "Irregular" };
    var initSiteDims2 : SiteDimsValue := SiteDimsValue { name := "N/A" };
    var initSiteDims3 : SiteDimsValue := SiteDimsValue { name := "Regular" };
  }

  globals {
    var initCarAccommodation1 : CarAccommodationValue := CarAccommodationValue { name := "Carport" };
    var initCarAccommodation2 : CarAccommodationValue := CarAccommodationValue { name := "Carspace" };
    var initCarAccommodation3 : CarAccommodationValue := CarAccommodationValue { name := "DLUG" };
    var initCarAccommodation4 : CarAccommodationValue := CarAccommodationValue { name := "Double Carport" };
    var initCarAccommodation5 : CarAccommodationValue := CarAccommodationValue { name := "Double Carspace" };
    var initCarAccommodation6 : CarAccommodationValue := CarAccommodationValue { name := "N/A" };
    var initCarAccommodation7 : CarAccommodationValue := CarAccommodationValue { name := "Off-Street Parking" };
    var initCarAccommodation8 : CarAccommodationValue := CarAccommodationValue { name := "Security Carspace" };
    var initCarAccommodation9 : CarAccommodationValue := CarAccommodationValue { name := "Single Carport" };
    var initCarAccommodation10 : CarAccommodationValue := CarAccommodationValue { name := "SLUG" };
    var initCarAccommodation11 : CarAccommodationValue := CarAccommodationValue { name := "SLUG and Carport" };
    var initCarAccommodation12 : CarAccommodationValue := CarAccommodationValue { name := "Tandem" };
    var initCarAccommodation13 : CarAccommodationValue := CarAccommodationValue { name := "TLUG" };
    var initCarAccommodation14 : CarAccommodationValue := CarAccommodationValue { name := "Undercover Carspace" };
  }
  
  globals {
    var initMarketability1 : MarketabilityValue := MarketabilityValue { name := "Poor" };
    var initMarketability2 : MarketabilityValue := MarketabilityValue { name := "Fair" };
    var initMarketability3 : MarketabilityValue := MarketabilityValue { name := "Good" };
  }
  
  globals {
    var initStreetAppeal1 : StreetAppealValue := StreetAppealValue { name := "Poor" };
    var initStreetAppeal2 : StreetAppealValue := StreetAppealValue { name := "Fair" };
    var initStreetAppeal3 : StreetAppealValue := StreetAppealValue { name := "Good" };
    var initStreetAppeal4 : StreetAppealValue := StreetAppealValue { name := "Excellent" };
  }
  
  globals {
    var initWallAndRoof1 : WallAndRoofValue := WallAndRoofValue { name := "Besserblock" };
    var initWallAndRoof2 : WallAndRoofValue := WallAndRoofValue { name := "Brick" };
    var initWallAndRoof3 : WallAndRoofValue := WallAndRoofValue { name := "Brick and Concrete" };
    var initWallAndRoof4 : WallAndRoofValue := WallAndRoofValue { name := "Brick and Iron" };
    var initWallAndRoof5 : WallAndRoofValue := WallAndRoofValue { name := "Brick and Tile" };
    var initWallAndRoof6 : WallAndRoofValue := WallAndRoofValue { name := "Brick Veneer" };
    var initWallAndRoof7 : WallAndRoofValue := WallAndRoofValue { name := "Brick Veneer and Concrete Tile" };
    var initWallAndRoof8 : WallAndRoofValue := WallAndRoofValue { name := "Brick/Fibro and Tile" };
    var initWallAndRoof9 : WallAndRoofValue := WallAndRoofValue { name := "Brick/Weatherboard and Tile" };
    var initWallAndRoof10 : WallAndRoofValue := WallAndRoofValue { name := "Clad and Tile" };
    var initWallAndRoof11 : WallAndRoofValue := WallAndRoofValue { name := "Fibro" };
    var initWallAndRoof12 : WallAndRoofValue := WallAndRoofValue { name := "Fibro and Iron" };
    var initWallAndRoof13 : WallAndRoofValue := WallAndRoofValue { name := "Fibro and Tile" };
    var initWallAndRoof14 : WallAndRoofValue := WallAndRoofValue { name := "Granite" };
    var initWallAndRoof15 : WallAndRoofValue := WallAndRoofValue { name := "N/A" };
    var initWallAndRoof16 : WallAndRoofValue := WallAndRoofValue { name := "Permalum" };
    var initWallAndRoof17 : WallAndRoofValue := WallAndRoofValue { name := "Permalum and Tile" };
    var initWallAndRoof18 : WallAndRoofValue := WallAndRoofValue { name := "Rendered Brick and Tile" };
    var initWallAndRoof19 : WallAndRoofValue := WallAndRoofValue { name := "Timber" };
    var initWallAndRoof20 : WallAndRoofValue := WallAndRoofValue { name := "Timber and Iron" };
    var initWallAndRoof21 : WallAndRoofValue := WallAndRoofValue { name := "Timber and Tile" };
    var initWallAndRoof22 : WallAndRoofValue := WallAndRoofValue { name := "Weatherboard" };
    var initWallAndRoof23 : WallAndRoofValue := WallAndRoofValue { name := "Weatherboard and Tile" };
  }
  
  globals {
    var initWindowFramesValue1 : WindowFramesValue := WindowFramesValue { name := "N/A" };
    var initWindowFramesValue2 : WindowFramesValue := WindowFramesValue { name := "Aluminium" };
    var initWindowFramesValue3 : WindowFramesValue := WindowFramesValue { name := "Timber" };
    var initWindowFramesValue4 : WindowFramesValue := WindowFramesValue { name := "Timber and Aluminium" };
  }

  globals {
    var initInteriorLiningsValue1 : InteriorLiningsValue := InteriorLiningsValue { name := "Brick" };
    var initInteriorLiningsValue2 : InteriorLiningsValue := InteriorLiningsValue { name := "Brick and GypRock" };
    var initInteriorLiningsValue3 : InteriorLiningsValue := InteriorLiningsValue { name := "Brick Veneer" };
    var initInteriorLiningsValue4 : InteriorLiningsValue := InteriorLiningsValue { name := "Concrete" };
    var initInteriorLiningsValue5 : InteriorLiningsValue := InteriorLiningsValue { name := "Fibro" };
    var initInteriorLiningsValue6 : InteriorLiningsValue := InteriorLiningsValue { name := "Gyprock" };
    var initInteriorLiningsValue7 : InteriorLiningsValue := InteriorLiningsValue { name := "N/A" };
    var initInteriorLiningsValue8 : InteriorLiningsValue := InteriorLiningsValue { name := "Plaster" };
    var initInteriorLiningsValue9 : InteriorLiningsValue := InteriorLiningsValue { name := "Plasterboard" };
    var initInteriorLiningsValue10 : InteriorLiningsValue := InteriorLiningsValue { name := "Render" };
    var initInteriorLiningsValue11 : InteriorLiningsValue := InteriorLiningsValue { name := "Timber" };
  }

  globals {
    var initIntExtConditionValue1 : IntExtConditionValue := IntExtConditionValue { name := "N/A" };
    var initIntExtConditionValue2 : IntExtConditionValue := IntExtConditionValue { name := "Unknown" };
    var initIntExtConditionValue3 : IntExtConditionValue := IntExtConditionValue { name := "TBE" };
    var initIntExtConditionValue4 : IntExtConditionValue := IntExtConditionValue { name := "Poor" };
    var initIntExtConditionValue5 : IntExtConditionValue := IntExtConditionValue { name := "Fair" };
    var initIntExtConditionValue6 : IntExtConditionValue := IntExtConditionValue { name := "Good" };
    var initIntExtConditionValue7 : IntExtConditionValue := IntExtConditionValue { name := "Very Good" };
    var initIntExtConditionValue8 : IntExtConditionValue := IntExtConditionValue { name := "Excellent" };
  }

  globals {
    var initFlooringValue1 : FlooringValue := FlooringValue { name := "N/A" };
    var initFlooringValue2 : FlooringValue := FlooringValue { name := "Carpet on Concrete" };
    var initFlooringValue3 : FlooringValue := FlooringValue { name := "Carpet on Timber" };
    var initFlooringValue4 : FlooringValue := FlooringValue { name := "Concrete" };
    var initFlooringValue5 : FlooringValue := FlooringValue { name := "Concrete and Timber" };
    var initFlooringValue6 : FlooringValue := FlooringValue { name := "Polished Timber" };
    var initFlooringValue7 : FlooringValue := FlooringValue { name := "Timber" };
  }

  globals {
    var initInteriorLayoutValue1 : InteriorLayoutValue := InteriorLayoutValue { name := "Functional" };
  }
  
  globals {
    var initAnalysisValue1 : AnalysisValue := AnalysisValue { name := "1" };
    var initAnalysisValue2 : AnalysisValue := AnalysisValue { name := "2" };
    var initAnalysisValue3 : AnalysisValue := AnalysisValue { name := "3" };
    var initAnalysisValue4 : AnalysisValue := AnalysisValue { name := "4" };
    var initAnalysisValue5 : AnalysisValue := AnalysisValue { name := "5" };
  }
  
  globals {
    var initPropertyIdentificationValue1 : PropertyIdentificationValue := PropertyIdentificationValue { name := "Contract of Sale" };
    var initPropertyIdentificationValue2 : PropertyIdentificationValue := PropertyIdentificationValue { name := "Council Rates Notice" };
    var initPropertyIdentificationValue3 : PropertyIdentificationValue := PropertyIdentificationValue { name := "Council Records" };
    var initPropertyIdentificationValue4 : PropertyIdentificationValue := PropertyIdentificationValue { name := "CT Search" };
    var initPropertyIdentificationValue5 : PropertyIdentificationValue := PropertyIdentificationValue { name := "Registered Plan" };
    var initPropertyIdentificationValue6 : PropertyIdentificationValue := PropertyIdentificationValue { name := "Valuer Generals Notice" };
  }
  
  globals {
    var initMarketValue1 : MarketValue := MarketValue { name := "Weakening" };
    var initMarketValue2 : MarketValue := MarketValue { name := "Steady" };
    var initMarketValue3 : MarketValue := MarketValue { name := "Strengthening" };
  }
  
  globals {
    var initClient1 : Client := Client { name := "Topline Foods" };
    var initClient2 : Client := Client { name := "Philips" };
    var initClient3 : Client := Client { name := "Nokia" };
  }
  
  globals {
    var initBroker1 : Broker := Broker { name := "Barneveld Schuurman Makelaars" };
    var initBroker2 : Broker := Broker { name := "Jeltes ten Hoor" };
    var initBroker3 : Broker := Broker { name := "Rooseboom" };
  }
  
  globals {
    var initStateValue1 : State := State { name := "NSW" };
    var initStateValue2 : State := State { name := "Queensland" };
    var initStateValue3 : State := State { name := "Victoria" };
    var initStateValue4 : State := State { name := "Northern Territory" };
    var initStateValue5 : State := State { name := "Western Australia" };
    var initStateValue6 : State := State { name := "South Australia" };
    var initStateValue7 : State := State { name := "Tasmania" };
  }
  
  globals {
    var initPostCode1 : PostCode := PostCode { name := "2026" };
    var initPostCode2 : PostCode := PostCode { name := "2088" };
    var initPostCode3 : PostCode := PostCode { name := "2132" };
    var initPostCode4 : PostCode := PostCode { name := "2342" };
  }
  
  globals {
    var initPropertyCategory1 : PropertyCategory := PropertyCategory { name := "Residential" };
    var initPropertyCategory2 : PropertyCategory := PropertyCategory { name := "Offices" };
    var initPropertyCategory3 : PropertyCategory := PropertyCategory { name := "Kekstra" };
  }
  
  globals {
    var initPropertyType1 : PropertyType := PropertyType { name := "House" };
    var initPropertyType2 : PropertyType := PropertyType { name := "Flat" };
    var initPropertyType3 : PropertyType := PropertyType { name := "Appartment" };    
  }
  
  globals {
    var initReportType1 : ReportType := ReportType { name := "Short Report" };
    var initReportType2 : ReportType := ReportType { name := "Extensive Report" };
    var initReportType3 : ReportType := ReportType { name := "Too long Report" };    
  }
  
  globals {
    var initPropose1 : Propose := Propose { name := "Market" };
    var initPropose2 : Propose := Propose { name := "Market too" };
    var initPropose3 : Propose := Propose { name := "What other values?" };
  }
  
  globals {
    var initValuationRequestStatus1 : ValuationRequestStatus := ValuationRequestStatus { name := "Request Received" };
    var initValuationRequestStatus2 : ValuationRequestStatus := ValuationRequestStatus { name := "Booked" };
    var initValuationRequestStatus3 : ValuationRequestStatus := ValuationRequestStatus { name := "Pending" };
    var initValuationRequestStatus4 : ValuationRequestStatus := ValuationRequestStatus { name := "Awaiting Approval" };
    var initValuationRequestStatus5 : ValuationRequestStatus := ValuationRequestStatus { name := "Approved" };
    var initValuationRequestStatus6 : ValuationRequestStatus := ValuationRequestStatus { name := "Sent" };
  }
    
  globals {
    var initComparisonValue1 : ComparisonValue := ComparisonValue { name := "Inferior" };
    var initComparisonValue2 : ComparisonValue := ComparisonValue { name := "Similar" };
    var initComparisonValue3 : ComparisonValue := ComparisonValue { name := "Superior" };
  }
  
  globals {
    var valuerLiming : Valuer := Valuer { user := userLiming };
    var valuerRuben : Valuer := Valuer { user := userRuben };
  }
  
  globals {
    var initValuationRequest1 : ValuationRequest := ValuationRequest { address := "1/13 O Brien Street" suburb := "Bondi Beach" postCode := initPostCode1 state := initStateValue1 };
    var initValuation1 : Valuation := Valuation { valuationRequest := initValuationRequest1 number := 228320 valuer := valuerRuben };
    var initInvoice1 : Invoice := Invoice { valuation := initValuation1 number := 34987 };

    var initValuationRequest2 : ValuationRequest := ValuationRequest { address := "13 Garden Street" suburb := "Eveleigh" postCode := initPostCode3 state := initStateValue1 };
    var initValuation2 : Valuation := Valuation { valuationRequest := initValuationRequest2 number := 219320 valuer := valuerLiming };
    var initInvoice2 : Invoice := Invoice { valuation := initValuation2 number := 53453 };
  }