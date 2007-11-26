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
        address := ""
        city := ""
        telephone := ""
      };

    var o2 : Owner :=
      Owner {
        name := "Betty Davis"
        address := ""
        city := ""
        telephone := ""
      };

    var o3 : Owner :=
      Owner {
        name := "Eduardo Rodriquez"
        address := ""
        city := ""
        telephone := ""
      };

    var o4 : Owner :=
      Owner {
        name := "Harold Davis"
        address := ""
        city := ""
        telephone := ""
      };

    var o5 : Owner :=
      Owner {
        name := "Peter McTavish"
        address := ""
        city := ""
        telephone := ""
      };

    var o6 : Owner :=
      Owner {
        name := "Jean Coleman"
        address := ""
        city := ""
        telephone := ""
      };

    var o7 : Owner :=
      Owner {
        name := "Jeff Black"
        address := ""
        city := ""
        telephone := ""
      };

    var o8 : Owner :=
      Owner {
        name := "Maria Escobito"
        address := ""
        city := ""
        telephone := ""
      };

    var o9 : Owner :=
      Owner {
        name := "David Schroeder"
        address := ""
        city := ""
        telephone := ""
      };

    var o10 : Owner :=
      Owner {
        name := "Carlos Estaban"
        address := ""
        city := ""
        telephone := ""
      };
  }
