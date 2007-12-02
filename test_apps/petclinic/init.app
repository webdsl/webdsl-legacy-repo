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
        telephone := ""
      };

    var o2 : Owner :=
      Owner {
        name := "Betty Davis"
        address := "638 Cardinal Ave."
        city := "Sun Prairie"
        telephone := ""
      };

    var o3 : Owner :=
      Owner {
        name := "Eduardo Rodriquez"
        address := "2693 Commerce St."
        city := "McFarland"
        telephone := ""
      };

    var o4 : Owner :=
      Owner {
        name := "Harold Davis"
        address := "563 Friendly St."
        city := "Windsor"
        telephone := ""
      };

    var o5 : Owner :=
      Owner {
        name := "Peter McTavish"
        address := "2387 S. Fair Way"
        city := "Madison"
        telephone := ""
      };

    var o6 : Owner :=
      Owner {
        name := "Jean Coleman"
        address := "105 N. Lake St."
        city := "Monona"
        telephone := ""
      };

    var o7 : Owner :=
      Owner {
        name := "Jeff Black"
        address := "1450 Oak Blvd."
        city := "Monona"
        telephone := ""
      };

    var o8 : Owner :=
      Owner {
        name := "Maria Escobito"
        address := "345 Maple St."
        city := "Madison"
        telephone := ""
      };

    var o9 : Owner :=
      Owner {
        name := "David Schroeder"
        address := "2749 Blackhawk Trail"
        city := "Madison"
        telephone := ""
      };

    var o10 : Owner :=
      Owner {
        name := "Carlos Estaban"
        address := "2335 Independence La."
        city := "Waunakee"
        telephone := ""
      };
  }
