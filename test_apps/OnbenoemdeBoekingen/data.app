module data

section data model

  entity BronFormaat {
    doccode  :: String
    duedate  :: Date
    valuedoc :: Float
    comm     :: Text
    usrref1  :: String
    verwerkt :: Bool
  }

  entity Registratie {
    docCode           :: String
    ontvangstDatum    :: Date
    bedrag            :: Float
    omschrijving      :: Text
    client            :: String
    polisSchadeNummer :: Int
    afdeling          :: String
    boekingsDatum     :: Date
    opmerking         :: Text
    behandelaar       -> Afdeling
    gereedFIN         :: Bool
    gereedFTA         :: Bool
    nieuw             :: Bool
    splitsing         :: Bool
    verwijderDatum    :: Date
  }

  entity Gebruiker {
    gebruikersNaam :: String (id)
    afdeling       -> Afdeling
    naam           :: String (name)
    wachtwoord     :: Secret
    werkUren       :: String
    norm           :: String
    email          :: Email
    isCoordinator  :: Bool
  }

  entity Afdeling {
    naam :: String (name)
  }

section initial data

  init {
    Afdeling { naam := "Uitkeringen" }.save();
    var nietTeBenoemen : Afdeling := Afdeling { naam := "Niet te benoemen" }; nietTeBenoemen.save();
    Afdeling { naam := "FO1 - Particuliere Pensioenen" }.save();
    Afdeling { naam := "FO2 - Ondernemers Pensioenen" }.save();
    Afdeling { naam := "Financien" }.save();
    Afdeling { naam := "BO1" }.save();
    Afdeling { naam := "BO2" }.save();
    Afdeling { naam := "BO3" }.save();

    Gebruiker { gebruikersNaam := "co", afdeling := nietTeBenoemen, naam := "Coordinator", wachtwoord := ("co" as Secret).digest(), isCoordinator := true }.save();
  }
