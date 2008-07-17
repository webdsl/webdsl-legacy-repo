module pages

section pages

define page vets() {
  style { "bla.css" }
  main()
  define body() {
    header { "Veterinarians:" }
    table() {
      header { "Name" "Specialty" }
      for (v : Vet) {
        row {
          text(v.name)
          bla2(v.specialtiesList)
        }
      }
    }
  }
}

/*
define ouput(coll : *<*>) {
    list() {
      for (s : * in coll) {
        listitem { text("____") output(s.name) } 
      }
    }
}
*/

define bla2(coll : List<Specialty>) {
    list() {
      for (s : Specialty in coll) {
        listitem { text("____") output(s.name) } 
      }
    }
}

define bla2(coll : List<Visit>) {
    list() {
      for (v : Visit in coll) {
        listitem { text("....") output(v.date) } 
      }
    }
}


define bla2(i1 : Int, i2 : Int, i3 : Int) {
    text("bla")
}

define page vet(v : Vet) {
  main()
  define body() {
    header { "Veterinarian: " output(v.name) }
    bla2(v.specialtiesList)
  }
}

define page pet(p : Pet) {
  main()
  define body() {
    header { "Pet: " output(p.name) }
    bla2(p.visitsList)
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
      header { "Name" "Address" "City" "Telephone" }
      for (o : Owner in foundOwners) {
        output(o.name)
        row { action(o.name, ownerDetails(o)) text(o.address) text(o.city) text(o.telephone) }
      }
    }

    action ownerDetails(owner : Owner) {
      return owner(owner);
    }
  }
}

define page owner(o : Owner) {
  main()
  define body() {

    header { "Owner: " text(o.name) }
    table() {
      row { "Name: " text(o.name) }
      row { "Address: " text(o.address) }
      row { "City: " text(o.city) }
      row { "Telephone: " text(o.telephone) }
    }
  }
}

define page allVisit() {
  main()
  define body() {
    list() {
      for (v : Visit) {
        listitem { text(v.name) }
      }
    }
  }
}

define page allOwner() {
  main()
  define body() {
    list() {
      for (o : Owner) {
        listitem { text(o.name) }
      }
    }
  }
}

define page allPet() {
  main()
  define body() {
    list() {
      for (p : Pet) {
        listitem { text(p.name) }
      }
    }
  }
}

define page createVet() { 
  var v : Vet;
  derive createPage from v
}

define page createOwner() { 
  var o : Owner;
  derive createPage from o
}

define page createVisit() { 
  var v : Visit;
  derive createPage from v
}

define page createPet() { 
  var p : Pet;
  derive createPage from p
}

define page specialty(s : Specialty) { 
  derive viewPage from s
}

define page test() {
  "bla"
}