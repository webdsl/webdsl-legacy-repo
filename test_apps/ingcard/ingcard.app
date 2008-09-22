application com.example.ingcard

description {
	This is an automatically generated description
}

imports datamodel
imports templates
imports pages
imports style
imports layout

section pages

  define page home() {
    var customer : Customer;

	main()
	define body() {
		form() {
			group("Search Card Request") {
				groupitem { label("Postcode") { input(customer.adres.postcode) } }
				groupitem { label("Achternaam") { input(customer.achternaam) } }
			}
			group() {
				action("Search Card Request", search_request())
			}
			
			action search_request() {
			  return home();
			}
		}
		
		form() {
		    var customerByLastname : String;
		    
			group("Search Customer") {
				groupitem { label("Kaartnummer") { input(customer.achternaam) } }
				groupitem { label("Achternaam") { input(customerByLastname) } }
				groupitem { label("Bankrekeningnummer") { input(customer.achternaam) } }
			}
			group() {
				action("Search Customer", search_customer())
			}
			
			action search_customer() {
			  return customers(customerByLastname);
			}
		}
	}
}
