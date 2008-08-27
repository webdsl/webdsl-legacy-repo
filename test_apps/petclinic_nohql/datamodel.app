module datamodel

section definition

  entity Owner {
    name :: String (name)
    address :: String
    city :: String
    telephone :: String
    pets -> Set<Pet>
  }

  entity Pet {
    name :: String (name)
    birthdate :: Date
    type -> PetType
    visits -> Set<Visit>
    owner -> Owner (inverse=Owner.pets)
  }

  entity PetType {
    name :: String (name)
  }

  entity Vet {
    name :: String (name)
    specialties -> Set<Specialty>
  }

  entity Specialty {
    name     :: String  (name)
  }

  entity Visit {
    date :: Date
    description :: String
    pet -> Pet (inverse=Pet.visits)
  }

section queries

    function searchOwner(searchString : String) : List<Owner> {
      var tempString : String := "%" + searchString + "%";
      var res : List<Owner> := List<Owner>();
      for(o : Owner where o.name = searchString order by o.name desc) {
        res.add(o);
      }
      return res;
    }
