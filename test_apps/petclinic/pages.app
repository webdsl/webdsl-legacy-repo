module pages

section pages

define page vets() {
  main()
  define body() {
    header { "Veterinarians:" }
    table() {
      row { "Name" "Specialty" }
      for (v : Vet) {
        row {
          text(v.name)
          table() {
            for (s : Specialty in v.specialtiesList) {
              row { text(s.name) }
            }
          }
        }
      }
    }
  }
}

define page findOwner() {
  main()
  define body() {
    var searchString : String;

    header { "Find Owners:" }
    text("Last Name: ")
    input(searchString)
    action("Find Owners", findOwners())
    action findOwners() {
      return owners(searchString);
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
    table() {
      row { "Name" "Address" "City" "Telephone" }
      for (o : Owner in foundOwners) {
        row { action(o.name, ownerDetails(o)) text(o.address) text(o.city) text(o.telephone) }
      }
    }

    action ownerDetails(owner : Owner) {
      return owner(owner);
    }
  }
}