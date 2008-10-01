module one-to-many

description {
  This is an automatically generated description
}

section data

entity Uomperson {
  name :: String (name)
  pets -> Set<Uompet>
}

entity Uompet {
   name :: String (name)
   species :: String
}

entity Omperson {
  name :: String (name)
  pets -> Set<Ompet> //old(inverse=Ompet.owner)
}

entity Ompet {
   name :: String (name)
   species :: String
   owner -> Omperson (inverse=Omperson.pets)
}

define page editUomperson(a:Uomperson){
  derive editPage from a
}
define page editUompet(a:Uompet){
  derive editPage from a
}
define page editOmperson(a:Omperson){
  derive editPage from a
}
define page editOmpet(a:Ompet){
  derive editPage from a
}
define page uompersion(a:Uomperson){
  derive viewPage from a
}
define page uompet(a:Uompet){
  derive viewPage from a
}
define page omperson(a:Omperson){
  derive viewPage from a
}
define page ompet(a:Ompet){
  derive viewPage from a
}

globals {

  var uompet1 : Uompet := Uompet {
    name := "Minoes"
    species := "Poes"
  };
  
  var uompet2 : Uompet := Uompet {
    name := "Fido"
    species := "Hond"
  };
  
  var uompet3 : Uompet := Uompet {
    name := "Ronnie"
    species := "Rat"
  };
  
  var uomup1 : Uomperson := Uomperson {
    name := "Zef"
    pets := {uompet1, uompet3}
  };

  var uomup2 : Uomperson := Uomperson {
    name := "Danny"
    pets := {uompet2}
  };
  
  
  var omup1 : Omperson := Omperson {
    name := "Zef"
  };

  var omup2 : Omperson := Omperson {
    name := "Danny"
  };
  
  var ompet1 : Ompet := Ompet {
    name := "Minoes"
    species := "Poes"
    owner := omup1
  };
  
  var ompet2 : Ompet := Ompet {
    name := "Fido"
    species := "Hond"
    owner := omup1
  };
  
  var ompet3 : Ompet := Ompet {
    name := "Ronnie"
    species := "Rat"
    owner := omup2
  };
}

section pages

define page onetomany() {
  main()
  define body() {
    div("div1") {
      header() { "Pets" }
      list {
        for(p : Ompet) {
          listitem { navigate(editOmpet(p)) { output(p.name) output(p.owner) } }
        }
      }
    }
    
    div("div2") {
      header() { "Persons" }
      list {
        for(p : Omperson) {
          listitem { navigate(editOmperson(p)) { output(p.name) output(p.pets) } }
        }
      }
    }
  }
}

define page uonetomany() {
  main()
  define body() {
    div("div1") {
      header() { "Pets" }
      list {
        for(p : Uompet) {
          listitem { navigate(editUompet(p)) { output(p.name) } }
        }
      }
    }
    
    div("div2") {
      header() { "Persons" }
      list {
        for(p : Uomperson) {
          listitem { navigate(editUomperson(p)) { output(p.name) output(p.pets) } }
        }
      }
    }
  }
}
