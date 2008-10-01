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

entity Uperson {
  name :: String (name)
}

entity Upet {
   name :: String (name)
   species :: String
   owner -> Uperson
}

define page editPerson(a:Person){
  derive editPage from a
}
define page editPet(a:Pet){
  derive editPage from a
}
define page editUperson(a:Uperson){
  derive editPage from a
}
define page editUpet(a:Upet){
  derive editPage from a
}
define page person(a:Person){
  derive viewPage from a
}
define page pet(a:Pet){
  derive viewPage from a
}
define page uperson(a:Uperson){
  derive viewPage from a
}
define page upet(a:Upet){
  derive viewPage from a
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

  var up1 : Uperson := Uperson {
    name := "Zef"
  };

  var up2 : Uperson := Uperson {
    name := "Danny"
  };
  
  var upet1 : Upet := Upet {
    name := "Minoes"
    species := "Poes"
    owner := up1
  };
  
  var upet2 : Upet := Upet {
    name := "Fido"
    species := "Hond"
    owner := up2
  };
  
  var upet3 : Upet := Upet {
    name := "Ronnie"
    species := "Rat"
    owner := up2
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
          listitem { navigate(editPet(p)) { output(p.name) output(p.owner) } }
        }
      }
    }
    
    div("div2") {
      header() { "Persons" }
      list {
        for(p : Person) {
          listitem { navigate(editPerson(p)) { output(p.name) output(p.pets) } }
        }
      }
    }
  }
}

define page umanytoone() {
  main()
  define body() {
    div("div1") {
      header() { "Pets" }
      list {
        for(p : Upet) {
          listitem { navigate(editUpet(p)) { output(p.name) output(p.owner) } }
        }
      }
    }
    
    div("div2") {
      header() { "Persons" }
      list {
        for(p : Uperson) {
          listitem { navigate(editUperson(p)) { output(p.name) } }
        }
      }
    }
  }
}
