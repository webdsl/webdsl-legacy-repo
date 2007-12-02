module datamodel

section definition

  entity Owner {
    name :: String (name, notempty)
    address :: String (notempty, minlength(6))
    city :: String
    telephone :: String
    pets -> Set<Pet>
  }

  entity Pet {
    name :: String(name)
    birthdate :: Date
    type -> PetType
    visits -> Set<Visit>
  }

  entity PetType {
    name :: String(name)
  }

  entity Vet {
    name :: String(name)
    specialties -> Set<Specialty>
  }

  entity Specialty {
    name     :: String (name)
  }

  entity Visit {
    date :: Date
    description :: String
  }

section queries

  globals {

    function searchOwner(searchString : String) : List<Owner> {
      var tempString : String := "%" + searchString + "%";
      var res : List<Owner> := select o from Owner as o where o._name like ~tempString order by o._name ascending;
      return res;
    }

  }