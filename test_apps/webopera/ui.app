module ui

section pages

define page home() {
    main()
    define body() {
        table[width := 100%, class := body] {
         row { 
          column { 
           group(Arbeid.name) {
            table {
             row { 
              column { image("/images/arbeidsongeschiktheid.png") }
              column [class := productlist] { navigate("Arbeid1", arbeid1())}
             }
            }
           }
          }
         }
         row {
           column { 
            group(Ziekte.name) {
                image("/images/ziektekosten.png")                
            }
           }
          }
          row {
           column {
            group(Leven.name) {
                image("/images/pensioenen.png")
            }
           }
          }
        }
    }
}

define page arbeid1() {
    var arb1 : Arbeid1;
    #[class := body] {
        derive createRows from arb1 for (attrib1, attrib2)
    }
}