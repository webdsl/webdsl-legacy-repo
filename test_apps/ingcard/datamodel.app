module datamodel

section entities

entity Customer {
	voorletters :: String
	achternaam :: String
	geboortedatum :: Date
	overlijdensdatum :: Date
	geslacht :: String
	nationaliteit :: String
	sofinummer :: Int
	telefoonnummer :: String
	mobielnummer :: String
	emailadres :: Email
	adres <> Address
	card -> Card
}

entity Address {
	straat :: String
	nummer :: Int
	postcode :: String
	woonplaats :: String
	land :: String
}

entity Card {
	rekeningnummer :: String
	bestedingslimiet :: Int
	rentepercentage :: Int
	vervaldatum :: Date
	naam_op_kaart :: String
}


section queries

    function searchCustomerByLastname(searchString : String) : List<Customer> {
      var tempString : String := "%" + searchString + "%";
      var res : List<Customer> := select o from Customer as o where o._achternaam like ~tempString order by o._achternaam ascending;
      return res;
    }
