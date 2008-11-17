module data

section data model

  entity User {
    username :: String
    name     :: String
    password :: Secret
    manager  -> User
    
    function isValuer() : Bool {
      for(v : Valuer where v.user == this) {
        return true;
      }
      return false;
    }
    
    function hasBookingRights() : Bool {
      for (a : Authorization where a.right == "booking" && a.user == this) {
        return true;
      }
      return false;
    }
  }
  
/**
 * LIXI Valuation Data model
 */
/*   
  entity PlanValue {
    name :: String
  }
  
  entity InstrumentValue {
    name :: String
  }
  
  entity SiteDimsValue {
    name :: String
  }
  
  entity CarAccommodationValue {
    name :: String
  }
  
  entity MarketabilityValue {
    name :: String
  }
  
  entity StreetAppealValue {
    name :: String
  }
  
  entity WallAndRoofValue {
    name :: String
  }
  
  entity WindowFramesValue {
    name :: String
  }
  
  entity InteriorLiningsValue {
    name :: String
  }

  entity IntExtConditionValue {
    name :: String
  }

  entity FlooringValue {
    name :: String
  }

  entity InteriorLayoutValue {
    name :: String
  }
  
  entity AnalysisValue {
    name :: String
  }

  entity PropertyIdentificationValue {
    name :: String
  }
  
  entity MarketValue {
    name :: String
  }
  
  entity StateValue {
    name :: String
  }
  
  entity PostCodeValue {
    name :: String
  }
  
  entity PropertyCategoryValue {
    name :: String
  }
  
  entity PropertyTypeValue {
    name :: String
  }
  
  entity ReportTypeValue {
    name :: String
  }
  
  entity ProposeValue {
    name :: String
  }
  
  entity ComparisonValue {
    name :: String
  }*/
  
  
  entity ValuationRequestStatus {
    name :: String
  }
  
  entity ValuationRequest {
    status -> ValuationRequestStatus
    progressValuations -> Set<ProgressValuation> (inverse=ProgressValuation.valuationRequest)
    invoices -> Set<Invoice> (inverse=Invoice.valuationRequest)
      
    fullAddress :: String := this.address + " " + this.suburb + " " + this.state.name + " " + this.postCode.name
    name :: String := this.fullAddress + " Valuation Request"
    requestDate :: Date
    sentDate :: Date
    
    // Client
    client -> Client
    primaryInterest :: String //todo
    broker -> Broker
    lenderContact :: String //todo
    reference :: String
    
    // Property
    address :: String
    suburb :: String
    state -> StateValue
    postCode -> PostCodeValue
    category -> PropertyCategoryValue
    type -> PropertyTypeValue
    
    comments :: Text
    
    // Specifications
    reportType -> ReportTypeValue
    propose -> ProposeValue
    
    // Applicant
    applicantName :: String
    applicantPhone :: String
    
    // Inspection Contact
    inspectionName :: String
    inspectionPhone :: String
    
    // Miscellaneous
    purchase :: String
    ownerEst :: String
    tender :: String
    
    // Booking
    udbRef :: String
    bookingDate :: Date
    bookingTime :: String
    bookingContact :: String
    bookingPhone :: String
    bookedBy -> User
    bookingNotes :: Text
    
    /**
     * Former Valuation stuff
     */
    valuationNumber :: Int
    valuer -> Valuer
    
    // Property Summary
    lot :: Int
    plan -> PlanValue
    planNumber :: Int
    proprietor :: String
    zoning :: String // hoe met opties weergeven?
    council :: String
    instrument -> InstrumentValue // of dynamisch?
    
    siteDims -> SiteDimsValue
    siteArea :: Int
    carAccommodation -> CarAccommodationValue
    carArea :: Int
    livingArea :: Int
    outdoorArea :: Int
    otherArea :: Int
    
    currentUse :: String
    mainBuilding :: String
    builtAbout :: String
    additions :: String
    heritageIssues :: String
    envIssues :: String
    essRepairs :: String
    
    actualRent :: Int
    marketability -> MarketabilityValue
    view :: Text
    
    // Main Building
    buildingStyle :: String
    streetAppeal -> StreetAppealValue
    wallAndRoof -> WallAndRoofValue
    windowFrames -> WindowFramesValue
    interiorLinings -> InteriorLiningsValue
    internalCondition -> IntExtConditionValue
    externalCondition -> IntExtConditionValue
    flooring -> FlooringValue
    interiorLayoutValue -> InteriorLayoutValue
    accommodation :: Text
    pcItems :: Text
    fixturesAndFeatures :: Text
    bedrooms :: Int
    bathrooms :: Int
    ensuites :: Int
    
    // Risk Analysis
    locationRisk -> AnalysisValue
    landRisk -> AnalysisValue
    environmentalIssuesRisk -> AnalysisValue
    improvementsRisk -> AnalysisValue
    reducedValueRisk -> AnalysisValue
    marketVolatilityRisk -> AnalysisValue
    localEconomyImpactRisk -> AnalysisValue
    marketSegmentConditionRisk -> AnalysisValue
    
    // Land
    propertyIdentification -> PropertyIdentificationValue
    hasTitleBeenSearched :: Bool
    zoningEffect :: Text
    location :: Text
    siteDescriptionAndAccess :: Text
    services :: Text
    servicesConnectedToProperty :: Bool
    neighbourhood :: Text
    ancillaryImprovements :: Text
    
    // Sales evidence
    salesEvidence -> Set<SalesEvidence>
    levelOfActivity -> MarketValue
    recentDirection -> MarketValue
    multiTier :: Bool
    latestSaleDate :: Date
    latestSalePrice :: Int //Float
    latestSaleComment :: Text
  }
  
  entity Client {
    name :: String
    valuationRequests -> Set<ValuationRequest> (inverse=ValuationRequest.client)
    //todo
  }
  
  entity Broker {
    name :: String
    //todo
  }
  
  entity ProgressValuation {
    valuationRequest -> ValuationRequest
    date :: Date
    comments :: Text
  }
  
  entity Invoice {
    number :: Int
    amount :: Int
    valuationRequest -> ValuationRequest
  }
  
  entity Valuer {
    user -> User
    name :: String := this.user.name
    valuationRequests -> Set<ValuationRequest> (inverse=ValuationRequest.valuer)
  }
  
  entity SalesEvidence {
    address :: String
    saleDate :: Date
    price :: Int //Float
    comment :: Text
    comparison -> ComparisonValue
  }

  entity Authorization {
    right :: String
    user -> User
  }