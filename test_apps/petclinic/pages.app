module pages

section pages

define page vets() {
  main()
  define body() {
    header { "Veterinarians:" }
    list {
      for (v : Vet) { 
        listitem { text(v.name) }
        list {
          for (s : Specialty in v.specialtiesList) {
            listitem { text(s.name) }
          }
        }
      }
    }
  }
}

define page findOwner() {
  main()
  define body() {
    var searchString : String := "";

    header { "Find Owners:" }
    text("Last Name: ")
    input(searchString)
    actionLink("Find Owners", findOwners(searchString))
    action findOwners(s : String) {
      goto owners(s);
    }

    div("add_owner_link_div") {
      navigate(createOwner()) { "Add Owner" }
    }
  }
}

define page owners(searchString : String) {
  main()
  define body() {
    var foundOwners : List<Owner> := searchOwner(searchString);
  
    header { "Owners:" }
    list {
      for (o : Owner in foundOwners) { 
        listitem { text(o.name) }
      }
    }
  }
}