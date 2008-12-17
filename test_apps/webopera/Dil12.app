module webopera/datamodel/products/Dileur
imports datamodel/datamodel
description {
  Initialize visibility to false:
}
section  entity .

// List of strings should be encapsulated in entities. Why not make a single entity
// for list options, and include a visibility and default property? Think this through...

  entity Dileur {
    prodid :: Int ( id )
    herkomstkps -> List<String>
    tariefcode :: String
    verzekeringsvormlv -> List<String>
    aantlevlyfr -> List<String>
    v1datgeboort :: String
    v1geslacht -> List<String>
    nabestaandelijfrentedadelijkingaand :: Bool
    v2datgeboort :: String
    v2geslacht -> List<String>
    percovergangvzlyfr :: String
    percovergangmvlyfr :: String
    datingang :: String
    datinganguitkering :: String
    srtlyfr -> List<String>
    srtlyfr2 -> List<String>
    uitkeringsduurobv -> List<String>
    dateinduitkering :: String
    duurjr_lyfr :: String
    duurmnd_lyfr :: String
    stijginguitkering :: String
    stijgendvanafingang -> List<String>
    uitgangspunt -> List<String>
    bedragkoopsom :: String
    bedragrente :: String
    bedragrente2 :: String
    uitkeringstermijn -> List<String>
    achterafvooraf -> List<String>
    slotuitkering -> List<String>
    contraverzekering -> List<String>
    afwijkendkapitaalcontra :: Bool
    bedragcontraverzekering :: String
    afwijkendeduurcontra :: Bool
    duurcontraverzekering :: String
    herkomstmij -> List<String>
    dispolisnramf :: String
    buttonpolisnramf :: String
    polisnramf :: String
    polisnramfpopup :: String
    herkomstbedragamf :: String
    rekenrenteversie -> List<String>
    rekenrente_lyfr :: String
    rekenrente_lyfr2 :: String
    medischewaarborg :: String
    overlijdensrisico :: String
    tabelpremie :: String
    telagesterftekansv1 :: Bool
    telagesterftekansv2 :: Bool
    knoppopup :: String
    wijzigenprov -> List<String>
    koopsomdirectingaandelijfrente_afsluit :: String
    koopsomdirectingaandelijfrente_afsluit_bedrag :: String
    koopsomdirectingaandelijfrente_doorlopend :: String
    koopsomdirectingaandelijfrente_doorlopend_bedrag :: String
    koopsomcontra_afsluit :: String
    koopsomcontra_afsluit_bedrag :: String
    koopsomcontra_doorlopend :: String
    koopsomcontra_doorlopend_bedrag :: String
    totaalprov_afsluit_bedrag :: String
    totaalprov_doorlopend_bedrag :: String
    inbouwen_afsluit :: String
    inbouwen_doorlopend :: String
    inbouwen_afsluit_bedrag :: String
    inbouwen_doorlopend_bedrag :: String
  } section  start page .

      define page home () {
        main()
        define kiesproduct () {
          block[class := selectedMenuItem] {
            text("Kies product")
            }
        }
        define sidebar () {
          table[class := sideMenu] {
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
        define body () {
          table[width := 100%, class := body] {
            row {
              column {
                group(Arbeid.name){table {
                                     row {
                                       column {
                                         image("/images/arbeidsongeschiktheid.png")
                                       }
                                       column[class := productlist] {
                                         form {
                                         }
                                         }
                                     }
                                   }}
              }
            }
            row {
              column {
                group(Ziekte.name){table {
                                     row {
                                       column {
                                         image("/images/ziektekosten.png")
                                       }
                                       column[class := productlist] {
                                         form {
                                         }
                                         }
                                     }
                                   }}
              }
            }
            row {
              column {
                group(Leven.name){table {
                                    row {
                                      column {
                                        image("/images/pensioenen.png")
                                      }
                                      column[class := productlist] {
                                        form {
                                          actionLink("Direct Ingaande Lijfrente in euro's", loadDileur())
                                        }
                                        action loadDileur ( )
                                        {
                                          var prod : Dileur := Dileur{} ;
                                          prod.save();
                                          return DileurAlgemeen(prod);
                                        }
                                        }
                                    }
                                  }}
              }
            }
            }
        }
      } section  configuration pages .

          define page DileurAlgemeen (prod : Dileur) {
            main()
            define body () {
              form {
                table[class := configform] {
                  row {
                    output("Herkomst koopsom")
                    input(prod.herkomstkps)
                  }
                  row {
                    output("Lijfrentevorm")
                    input(prod.tariefcode)
                  }
                  row {
                    output("Lijfrentevorm")
                    input(prod.verzekeringsvormlv)
                  }
                  row {
                    output("Aantal levens")
                    input(prod.aantlevlyfr)
                  }
                  row {
                    output("Geboortedatum verzekerde")
                    input(prod.v1datgeboort)
                  }
                  row {
                    output("Geslacht verzekerde")
                    input(prod.v1geslacht)
                  }
                  row {
                    output("Direct ingaande nabestaandelijfr.")
                    input(prod.nabestaandelijfrentedadelijkingaand)
                  }
                  row {
                    output("Geboortedatum medeverz.")
                    input(prod.v2datgeboort)
                  }
                  row {
                    output("Geslacht medeverzekerde")
                    input(prod.v2geslacht)
                  }
                  row {
                    output("Overgang op verzekerde %")
                    input(prod.percovergangvzlyfr)
                  }
                  row {
                    output("Overgang op medeverz. %")
                    input(prod.percovergangmvlyfr)
                  }
                  row {
                    output("Ingangsdatum verzekering")
                    input(prod.datingang)
                  }
                  row {
                    output("Ingangsdatum renteuitkeringen")
                    input(prod.datinganguitkering)
                  }
                  row {
                    output("Soort lijfrente")
                    input(prod.srtlyfr)
                  }
                  row {
                    output("")
                    input(prod.srtlyfr2)
                  }
                  row {
                    output("Tijdelijke lijfrente op basis van")
                    input(prod.uitkeringsduurobv)
                  }
                  row {
                    output("Einddatum")
                    input(prod.dateinduitkering)
                  }
                  row {
                    output("Jr")
                    input(prod.duurjr_lyfr)
                  }
                  row {
                    output("Mnd")
                    input(prod.duurmnd_lyfr)
                  }
                  row {
                    output("Percentage stijging uitkering")
                    input(prod.stijginguitkering)
                  }
                  row {
                    output("Stijgend tijdens de uitstelperiode")
                    input(prod.stijgendvanafingang)
                  }
                  row {
                    output("Uitgangspunt berekening")
                    input(prod.uitgangspunt)
                  }
                  row {
                    output("Koopsom bedrag")
                    input(prod.bedragkoopsom)
                  }
                  row {
                    output("Lijfrente bedrag")
                    input(prod.bedragrente)
                  }
                  row {
                    output("")
                    input(prod.bedragrente2)
                  }
                  row {
                    output("Uitkeringstermijn")
                    input(prod.uitkeringstermijn)
                  }
                  row {
                    output("Uitbetaling")
                    input(prod.achterafvooraf)
                  }
                  row {
                    output("Slotuitkering")
                    input(prod.slotuitkering)
                  }
                  row {
                    output("Contraverzekering")
                    input(prod.contraverzekering)
                  }
                  row {
                    output("- Afwijkend kapitaal")
                    input(prod.afwijkendkapitaalcontra)
                  }
                  row {
                    output("")
                    input(prod.bedragcontraverzekering)
                  }
                  row {
                    output("- Afwijkende duur")
                    input(prod.afwijkendeduurcontra)
                  }
                  row {
                    output("jaar")
                    input(prod.duurcontraverzekering)
                  }
                  row {
                    output("Koopsom komt vrij bij")
                    input(prod.herkomstmij)
                  }
                  row {
                    output("Polisnummer(s) Amersfoortse")
                    input(prod.dispolisnramf)
                  }
                  row {
                    output("...")
                    input(prod.buttonpolisnramf)
                  }
                  row {
                    output("Polisnummer")
                    input(prod.polisnramf)
                  }
                  row {
                    output("Polisnummers")
                    input(prod.polisnramfpopup)
                  }
                  row {
                    output("Bedrag uit Amersfoortse-polis")
                    input(prod.herkomstbedragamf)
                  }
                  }
              }
            }
          }

          define page DileurResultaten (prod : Dileur) {
            main()
            define body () {
              form {
                table[class := configform] {
                  row {
                    output("Versie rekenrente")
                    input(prod.rekenrenteversie)
                  }
                  row {
                    output("Rekenrente")
                    input(prod.rekenrente_lyfr)
                  }
                  row {
                    output("")
                    input(prod.rekenrente_lyfr2)
                  }
                  row {
                    output("Medische waarborgen")
                    input(prod.medischewaarborg)
                  }
                  row {
                    output("Overlijdensrisico")
                    input(prod.overlijdensrisico)
                  }
                  row {
                    output("")
                    input(prod.tabelpremie)
                  }
                  row {
                    output("")
                    input(prod.telagesterftekansv1)
                  }
                  row {
                    output("")
                    input(prod.telagesterftekansv2)
                  }
                  row {
                    output("VarProv")
                    input(prod.knoppopup)
                  }
                  }
              }
            }
          }

          define page DileurProvisie (prod : Dileur) {
            main()
            define body () {
              form {
                table[class := configform] {
                  row {
                    output("")
                    input(prod.wijzigenprov)
                  }
                  row {
                    output("")
                    input(prod.koopsomdirectingaandelijfrente_afsluit)
                  }
                  row {
                    output("")
                    input(prod.koopsomdirectingaandelijfrente_afsluit_bedrag)
                  }
                  row {
                    output("")
                    input(prod.koopsomdirectingaandelijfrente_doorlopend)
                  }
                  row {
                    output("")
                    input(prod.koopsomdirectingaandelijfrente_doorlopend_bedrag)
                  }
                  row {
                    output("")
                    input(prod.koopsomcontra_afsluit)
                  }
                  row {
                    output("")
                    input(prod.koopsomcontra_afsluit_bedrag)
                  }
                  row {
                    output("")
                    input(prod.koopsomcontra_doorlopend)
                  }
                  row {
                    output("")
                    input(prod.koopsomcontra_doorlopend_bedrag)
                  }
                  row {
                    output("")
                    input(prod.totaalprov_afsluit_bedrag)
                  }
                  row {
                    output("")
                    input(prod.totaalprov_doorlopend_bedrag)
                  }
                  row {
                    output("")
                    input(prod.inbouwen_afsluit)
                  }
                  row {
                    output("")
                    input(prod.inbouwen_doorlopend)
                  }
                  row {
                    output("")
                    input(prod.inbouwen_afsluit_bedrag)
                  }
                  row {
                    output("")
                    input(prod.inbouwen_doorlopend_bedrag)
                  }
                  }
              }
            }
          }
