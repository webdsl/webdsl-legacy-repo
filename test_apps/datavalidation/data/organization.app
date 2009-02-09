module organization

  entity Organization {
    name :: String
    address -> Address
    website :: URL
    //just a query on allmembers with no end date currentMembers -> Set<Affiliation> //user is either in this set or a departments members but not both
    members -> Set<Affiliation>
    departments -> Set<Organization>
  }