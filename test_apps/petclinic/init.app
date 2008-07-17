module init

section global data

  globals {

    var spec1 : Specialty :=
      Specialty {
        name := "dentistry"
      };

    var spec2 : Specialty :=
      Specialty {
        name := "surgery"
      };

    var spec3 : Specialty :=
      Specialty {
        name := "radiology"
      };

    var vet1 : Vet :=
      Vet {
        name := "James Carter"
      };

    var vet2 : Vet :=
      Vet {
        name := "Linda Douglas"
        specialties := {spec1, spec2}
      };

    var vet3 : Vet :=
      Vet {
        name := "Sharon Jenkins"
      };

    var vet4 : Vet :=
      Vet {
        name := "Helen Leary"
        specialties := {spec3}
      };

    var vet5 : Vet :=
      Vet {
        name := "Rafael Ortega"
        specialties := {spec2}
      };

    var vet6 : Vet :=
      Vet {
        name := "Henry Stevens"
        specialties := {spec3}
      };

    var o1 : Owner :=
      Owner {
        name := "George Franklin"
        address := "110 W. Liberty St."
        city := "Madison"
        telephone := "6085551023"
      };

    var o2 : Owner :=
      Owner {
        name := "Betty Davis"
        address := "638 Cardinal Ave."
        city := "Sun Prairie"
        telephone := "6085551749"
      };

    var o3 : Owner :=
      Owner {
        name := "Eduardo Rodriquez"
        address := "2693 Commerce St."
        city := "McFarland"
        telephone := "6085558763"
      };

    var o4 : Owner :=
      Owner {
        name := "Harold Davis"
        address := "563 Friendly St."
        city := "Windsor"
        telephone := "6085553198"
      };

    var o5 : Owner :=
      Owner {
        name := "Peter McTavish"
        address := "2387 S. Fair Way"
        city := "Madison"
        telephone := "6085552765"
      };

    var o6 : Owner :=
      Owner {
        name := "Jean Coleman"
        address := "105 N. Lake St."
        city := "Monona"
        telephone := "6085552654"
      };

    var o7 : Owner :=
      Owner {
        name := "Jeff Black"
        address := "1450 Oak Blvd."
        city := "Monona"
        telephone := "6085555387"
      };

    var o8 : Owner :=
      Owner {
        name := "Maria Escobito"
        address := "345 Maple St."
        city := "Madison"
        telephone := "6085557683"
      };

    var o9 : Owner :=
      Owner {
        name := "David Schroeder"
        address := "2749 Blackhawk Trail"
        city := "Madison"
        telephone := "6085559435"
      };

    var o10 : Owner :=
      Owner {
        name := "Carlos Estaban"
        address := "2335 Independence La."
        city := "Waunakee"
        telephone := "6085555487"
      };
    
    var pt1 : PetType :=
      PetType {
        name := "Cat"
      };
    
    var pt2 : PetType :=
      PetType {
        name := "Dog"
      };
    
    var pt3 : PetType :=
      PetType {
        name := "Parrot"
      };
    
    var pt4 : PetType :=
      PetType {
        name := "Hamster"
      };
    
    var p1 : Pet :=
     Pet {
       name := "Carla"
       birthdate := Date("22/06/2002")
       type := pt1
       owner := o6
     };
     
   var p2 : Pet :=
     Pet {
       name := "Harry"
       birthdate := Date("13/04/2001")
       type := pt4
       owner := o6
     };
     
   var p3 : Pet :=
     Pet {
       name := "Peter"
       birthdate := Date("03/01/1997")
       type := pt3
       owner := o1
     };
     
   var p4 : Pet :=
     Pet {
       name := "Fido"
       birthdate := Date("18/11/1991")
       type := pt2
       owner := o3
     };
  }
