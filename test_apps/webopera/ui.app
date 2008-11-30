module ui

imports arbeid1

section pages

define page home() {
    main()
    define kiesproduct() {
        block [class := selectedMenuItem] {
            text("Kies product")            
        }
    }
    define sidebar() {        
        table [class := sideMenu] {
          row {
            column {
              image("/images/exclm.png")
            }
            column { 
              kiesproduct()
            }
          }
        }
    }
    define body() {
        table[width := 100%, class := body] {
         row { 
          column { 
           group(Arbeid.name) {
            table {
             row { 
              column { image("/images/arbeidsongeschiktheid.png") }
              column [class := productlist] { 
                form {
                    actionLink("Arbeid1", arbeid1())             
                }
                action arbeid1() {
                 var arb1 : Arbeid1 := Arbeid1 { attrib1 := "teststring" };
                 arb1.save();
                 return arbeid1_tab1(arb1);                 
                }
              }
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

define page testpagina() {
    text("Deze test is geslaagd")
}

define page enterClient() {
    var cli : Client := Client {};
    derive createPage from cli
}

