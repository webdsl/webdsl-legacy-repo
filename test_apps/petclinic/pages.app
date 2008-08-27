module pages

section pages

define page vets() {
  main()
  define body() {
    header { "Veterinarians:" }
    table() {
      header { "Name" "Specialty" }
      for (v : Vet) {
        row {
          text(v.name) output(v.specialtiesList)
        }
      }
    }
  }
}


define output(coll : List<Specialty>) {
    list() {
      for (s : Specialty in coll) {
        listitem { text("____") output(s.name) } 
      }
    }
}

define output(coll : List<Visit>) {
    list() {
      for (v : Visit in coll) {
        listitem { text("....") output(v.date) } 
      }
    }
}


define page vet(v : Vet) {
  main()
  define body() {
    header { "Veterinarian: " output(v.name) }
    output(v.specialtiesList)
  }
}

define page pet(p : Pet) {
  main()
  define body() {
    header { "Pet: " output(p.name) }
    output(p.visitsList)
  }
}

define page findOwner() {
  main()
  define body() {
    var searchString : String;

	form {
		header { "Find Owners:" }
		text("Last Name: ")
		input(searchString)
		action("Find Owners", findOwners())
		action findOwners() {
		  return owners(searchString);
		}
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
			row {
			  navigate(owner(o)) { output(o.name) } text(o.address) text(o.city) text(o.telephone)
			}
		  }
		}
  }
}

define page owner(o : Owner) {
  derive viewPage from o
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
  title("Create new owner")

  main()
  define body() {
    var o : Owner;
    header{ "Create new owner" } 
    form { 
      group("Owner details") {
        groupitem { label("Name:") { input(o.name) } }
        groupitem { label("Address:") { input(o.address) } }
      }
      group("More details") {
        groupitem { label("City:") { input(o.city) } }
        groupitem { label("Telephone:") { input(o.telephone) } }
        groupitem { label("Pets:") { input(o.pets) } }
      }
      group {
        action("Save", save()) 
        action("Cancel", cancel())
      }
    }
    
    action save() { 
      o.save(); 
	  return owner(o);
    }
    action cancel() {
      cancel home();
    }
  } 
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
