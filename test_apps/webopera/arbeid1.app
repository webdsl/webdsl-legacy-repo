module arbeid1

imports datamodel

section arbeid1 datamodel

    entity Arbeid1 {
        attrib1 :: String (name)
        attrib2 :: Int
        attrib3 -> Arbeid1_attrib3_option
        results1 :: Int
        results2 :: Float        
    }

    entity Arbeid1_attrib3_option {
        name :: String
        visibility :: Bool
    }

    function arbeid1_calculate(arb1 : Arbeid1) {
        arb1.results1 := arb1.attrib2;
        arb1.results2 := 4.5;
        arb1.save();
    }

section menus

    define arbeid1_main(arb1 : Arbeid1) {
      main()
      define sidebar() {
        table [class := sideMenu] {
          row {
            column {
                image("/images/vink.png")
            }
            column { 
                navigate(home()){"Kies product"}
            }
          }
         row {
            column {
                image("/images/vink.png")
            }
            column { 
                navigate(enterClient()){"Klant"}
            }
          }
          row {
            column {
                image("/images/vink.png")
            }
            column {
                navigate(arbeid1_tab1(arb1)){"Tabblad1"}
            }
          }
          row {
            column {
                image("/images/vink.png")
            }
            column {
                navigate(arbeid1_tab2(arb1)){"Tabblad2"}
            }
          }
          row {
            column {
                image("/images/exclm.png")
            }
            column [class := selectedMenuItem] {
                text("Resultaten")
            }
          }
        }
      }
      define header() {
        menubar("horizontal") {
            menu {
                menuheader{ navigate(arbeid1_tab1(arb1)){"Tabblad1"}}
                menuheader{ navigate(arbeid1_tab2(arb1)){"Tabblad2"}}
                menuheader{ navigate(arbeid1_results(arb1)){"Resultaten"}}
            }
        }
      }
    }


section ui

    define page arbeid1_tab1(arb1 : Arbeid1) {        
        arbeid1_main(arb1)
        define body() {
          form {
            group("Arbeidsongeschiktheidsverzekering 1") [class := body] {
              groupitem {
                label("Attrib1") { input(arb1.attrib1) }
              }
              groupitem {
                label("Attrib2") { input(arb1.attrib2) }
              }
              groupitem {
                label("Attrib3") { input(arb1.attrib3) }
              }
              groupitem {
                navigatebutton(home(), "<< Terug")
                action("Resultaat >>", toResults())
              }
            }
          }        
          action toResults() {
            arb1.save();
            arbeid1_calculate(arb1);
            return arbeid1_results(arb1);
          }
        }
    }
    
    define page arbeid1_tab2(arb1 : Arbeid1) {
      arbeid1_main(arb1)
      define body() {
        text("Other tab page")
      }
    }
    
    define page arbeid1_results(arb1 : Arbeid1) {
      arbeid1_main(arb1)
    
      define body() {
        group("Resultaten") {
            groupitem {
                label("Results1") { output(arb1.results1) }
            }
            groupitem {
                label("Results2") { output(arb1.results2) }
            }
        }
      }
    }