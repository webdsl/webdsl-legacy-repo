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
    
    valuationNumber :: Int
    valuer -> Valuer
    
  // Request Details
    // Client
    client -> Client
    primaryInterest -> Client
    broker -> Broker
    lenderContact -> ContactValue
    reference :: String
    // Property
    address :: String
    suburb :: String
    state -> StateValue
    postCode -> PostCodeValue
    category -> PropertyCategoryValue
    type -> PropertyTypeValue
    // Comments
    comments :: Text
    // Specifications
    reportType -> ReportTypeValue
    purpose -> PurposeValue
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
    
  // Booking Details
    udbRef :: String
    bookingDate :: Date
    bookingTime :: String
    bookingContact :: String
    bookingPhone :: String
    bookedBy -> User
    bookingNotes :: Text
    
    // Quote
    alternativeBill -> Client
    feeScale -> FeeScaleValue
    invoicePaid :: Bool
    quoteComments :: Text
    other :: Int //Float
    quoteTerms -> Set<QuoteTermValue>
    
    // Property Summary
    lot :: Int
    plan -> PlanValue
    planNumber :: Int
    proprietor :: String
    zoning :: String (select=ZoningOption)
    council :: String (select=CouncilOption)
    instrument -> InstrumentValue
    
    siteDims -> SiteDimsValue
    siteArea :: Int
    carAccommodation -> CarAccommodationValue
    carArea :: Int
    livingArea :: Int
    outdoorArea :: Int
    otherArea :: Int
    
    currentUse :: String (select=CurrentUseOption)
    mainBuilding :: String (select=MainBuildingOption)
    builtAbout :: String (select=BuiltAboutOption)
    additions :: String (select=AdditionsOption)
    heritageIssues :: String (select=HeritageIssuesOption)
    envIssues :: String (select=EnvIssuesOption)
    essRepairs :: String (select=EssRepairsOption)
    
    actualRent :: Int
    marketability -> MarketabilityValue
    view :: Text
    
    // Main Building
    buildingStyle :: String (select=StyleOption)
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
    
    // Securitisation Req.
    effects -> Set<EffectValue>
    extensions -> Set<ExtensionValue>
    
    // Valuation & Assessment
    interest -> InterestValue
    component -> ComponentValue
    rentalValue :: Int //Float
    replacement :: Int //Float
    otherAssessment :: Int //Float
    // Market Value
    assessmentType -> AssessmentTypeValue
    land :: Int //Float
    improvements :: Int //Float
    marketValue :: Int //Float
    // Building Erection
    toBeErected :: Bool
    builder :: String
    buildingDate :: Date
    buildingPrice :: Int //Float
    buildingCost :: Int //Float
    
    // Additional comments
    additionalComments :: Text
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