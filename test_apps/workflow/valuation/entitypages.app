module entitypages

section user pages

  define page user(object : User) { derive viewPage from object }
  
section value pages

  define page planValue (object : PlanValue) { derive viewPage from object }
  define page instrumentValue (object : InstrumentValue) { derive viewPage from object }
  define page siteDimsValue (object : SiteDimsValue) { derive viewPage from object }
  define page carAccommodationValue (object : CarAccommodationValue) { derive viewPage from object }
  define page marketabilityValue (object : MarketabilityValue) { derive viewPage from object }
  define page streetAppealValue (object : StreetAppealValue) { derive viewPage from object }
  define page wallAndRoofValue (object : WallAndRoofValue) { derive viewPage from object }
  define page windowFramesValue (object : WindowFramesValue) { derive viewPage from object }
  define page interiorLiningsValue (object : InteriorLiningsValue) { derive viewPage from object }
  define page intExtConditionValue (object : IntExtConditionValue) { derive viewPage from object }
  define page flooringValue (object : FlooringValue) { derive viewPage from object }
  define page interiorLayoutValue (object : InteriorLayoutValue) { derive viewPage from object }
  define page analysisValue (object : AnalysisValue) { derive viewPage from object }
  define page propertyIdentificationValue (object : PropertyIdentificationValue) { derive viewPage from object }
  define page marketValue (object : MarketValue) { derive viewPage from object }
  define page client(object : Client) { derive viewPage from object }
  define page broker(object : Broker) { derive viewPage from object }
  define page state(object : State) { derive viewPage from object }
  define page postCode(object : PostCode) { derive viewPage from object }
  define page propertyCategory(object : PropertyCategory) { derive viewPage from object }
  define page propertyType(object : PropertyType) { derive viewPage from object }
  define page reportType(object : ReportType) { derive viewPage from object }
  define page propose(object : Propose) { derive viewPage from object }
  define page valuationRequestStatus(object : ValuationRequestStatus) { derive viewPage from object }
  define page invoice(object : Invoice) { derive viewPage from object }
  define page valuation(object : Valuation) { derive viewPage from object }
  define page comparisonValue (object : ComparisonValue) { derive viewPage from object }
  define page salesEvidence (object : SalesEvidence) { derive viewPage from object }
  define page valuer (object : Valuer) { derive viewPage from object }
  define page allComparisonValue() {
    main()
    define body() {
      for(c : ComparisonValue) {
        text(c.name)
        text(" ")
      }
    }
  }
  define page allInvoice() {
    main()
    define body() {
      for(i : Invoice) {
        output(i)
        text(" ")
      }
    }
  }
  