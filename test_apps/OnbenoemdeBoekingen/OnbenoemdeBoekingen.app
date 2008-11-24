application OnbenoemdeBoekingen

imports style/main
imports data
imports menu
imports logic

section pages

  define page home(){
    main()
    define body(){"test"}
  }

  define page nieuweInvoer() {
    title { "Invoer bron matteriaal" }
    var b : BronFormaat := BronFormaat{}

    derive createPage from b for (doccode, duedate, valuedoc, comm, usrref1)
  }

  define page invoerNaarRegistratie() {
    title { "Invoer naar registratie" }
    main()
    define body() {
      header { "Invoer naar registratie" }
      form {
        action("Importeren", importeren())

        action importeren() {
          bronFormaatNaarRegistratie();
          return alleRegistraties();
        }
      }
    }
  }

  define page alleRegistraties() {
    title { "Alle registraties"}
    main()
    define body() {
      header { "Alle registraties"}
      table {
        row {
            header { "Doc code"}
            header { "Ontvangst datum"}
            header { "Bedrag"}
            header { "Omschrijving "}
            //header { "Client"}
            header { "Polis schade nummer "}
            header { "Afdeling "}
            header { "Boekings datum "}
            header { "Opmerking"}
            header { "Behandelaar "}
            /*
            header { "GereedFIN "}
            header { "GereedFTA "}
            header { "Nieuw "}
            header { "Splitsing "}
            header { "VerwijderDatum "}
            */
        }
        for(r : Registratie) {
          form {
            row { 
              output(r.docCode)
              output(r.ontvangstDatum)
              output(r.bedrag)
              output(r.omschrijving)
              //output(r.client)
              output(r.polisSchadeNummer)
              output(r.afdeling)
              output(r.boekingsDatum)
              output(r.opmerking)
              input(r.behandelaar)
              /*
              output(r.gereedFIN)
              output(r.gereedFTA)
              output(r.nieuw)
              output(r.splitsing)
              output(r.verwijderDatum)
              */
              action("Opslaan", opslaan(r))
            }
          }
          action opslaan(r : Registratie) {
            r.save();
            return alleRegistraties();
          }
        }
      }
    }
  }

