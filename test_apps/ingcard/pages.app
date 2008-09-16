module pages

  section all page definitions
  
	define page customers(searchString : String) {
	  main()
	  define body() {
			var foundCustomers : List<Customer> := searchCustomerByLastname(searchString);
		
			header { "Customers:" }
			table() {
			  header { "Name" "Address" "City" "Telephone" }
			  for (o : Customer in foundCustomers) {
				row {
				  navigate(customer(o)) { output(o.achternaam) } text(o.geboortedatum) text(o.sofinummer) text(o.emailadres)
				}
			  }
			}
	  }
	}
	
	define page customer(c : Customer) {
	  main()
	  define body() {
	  	customerDetails(c)
	  	
	  	group("Persoonlijke gegevens") {
	  	  groupitem { label("Voorletters") { output(c.voorletters) } }
	  	  groupitem { label("Achternaam") { output(c.achternaam) } }
	  	  groupitem { label("Geboortedatum") { output(c.geboortedatum) } }
	  	  groupitem { label("Sofinummer") { output(c.sofinummer) } }
	  	  groupitem { label("Emailadres") { output(c.emailadres) } }
	  	  groupitem { label("Geslacht") { output(c.geslacht) } }
	    }
	  }
	}
	
	define page card(c : Customer) {
	  main()
	  define body() {
	  	customerDetails(c)
	  	
	  	group("Card data") {
	  	  groupitem { label("Naam op kaart") { output(c.card.naam_op_kaart) } }
	  	  groupitem { label("Rekeningnummer") { output(c.card.rekeningnummer) } }
	  	  groupitem { label("Bestedingslimiet") { output(c.card.bestedingslimiet) } }
	  	  groupitem { label("Rente percentage") { output(c.card.rentepercentage) } }
	  	  groupitem { label("Vervaldatum") { output(c.card.vervaldatum) } }
	    }
	  }
	}
	
	define customerDetails(c : Customer) {
	  group {
	  	  groupitem { label("Voorletters") { output(c.voorletters) } }
	  	  groupitem { label("Achternaam") { output(c.achternaam) } }
	  	  groupitem { label("Geboortedatum") { output(c.geboortedatum) } }
	  }
	  group {
	  	  groupitem { label("Sofinummer") { output(c.sofinummer) } }
	  	  groupitem { label("Emailadres") { output(c.emailadres) } }
	  	  groupitem { label("Geslacht") { output(c.geslacht) } }
	  }

	  list {
	    listitem { navigate(customer(c)) { "Customer" } }
	    listitem { navigate(card(c)) { "Card" } }
	    listitem { navigate(customer(c)) { "Campaign" } }
	    listitem { navigate(customer(c)) { "Maintenance" } }
	    listitem { navigate(customer(c)) { "Transaction" } }
	    listitem { navigate(customer(c)) { "Statement" } }
	    listitem { navigate(customer(c)) { "Internet Account" } }
	    listitem { navigate(customer(c)) { "Contact History" } }
	    listitem { navigate(customer(c)) { "Letters" } }
	  }
	}
	
	define page address(a : Address) {
	  derive viewPage from a
	}
