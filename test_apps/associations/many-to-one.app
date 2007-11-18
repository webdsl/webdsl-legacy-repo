module many-to-one

description {
  This is an automatically generated description
}

section data

entity Person {
  name :: String (name)
  pets -> Set<Pet>
}

entity Pet {
   name :: String (name)
   species :: String
   owner -> Person (inverse=Person.pets)
}

globals {

  var pet1 : Pet := Pet {
    name := "Minoes"
    species := "Poes"
  };
  
  var pet2 : Pet := Pet {
    name := "Fido"
    species := "Hond"
  };
  
  var pet3 : Pet := Pet {
    name := "Ronnie"
    species := "Rat"
  };
  
  var p1 : Person := Person {
    name := "Zef"
    pets := {pet1, pet3}
  };

  var p2 : Person := Person {
    name := "Danny"
    pets := {pet2}
  };

}

section pages

define page manytoone() {
  main()
  define body() {
    div("div1") {
      header() { "Pets" }
      list {
        for(p : Pet) {
          listitem { navigate(editPet(p)) { output(p.name) } }
        }
      }
    }
    
    div("div2") {
      header() { "Persons" }
      list {
        for(p : Person) {
          listitem { navigate(editPerson(p)) { output(p.name) } }
        }
      }
    }
  }
}
