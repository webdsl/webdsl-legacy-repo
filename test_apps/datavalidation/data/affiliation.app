module affiliation

  entity Affiliation {
    person -> Person (inverse=Person.affiliations)
    organization -> Organization (inverse=Organization.members)
    periodStart :: Date
    periodEnd :: Date
  }