application com.example.osb

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

	main()
	
	define sidebarText() {
	  header { "Welkom bij OSB Gateway" }
	  par { "Via deze pagina kunt u contract transformaties uitvoeren" }
	  par { "(UC17.v5)" }
	}
	
	define body() {
	  var e : homeEntity;
	  
	  form {
	    group {
	      groupitem { label("Broncontract") { input(e.broncontract) } }
	      groupitem { label("Doelcontract") { input(e.doelcontract) } }
	      groupitem { label("JMS contract") { input(e.jmscontract) } }
	    }
	    group {
	      action("Contract transformatie uitvoeren", uitvoeren())
	    }
	    action uitvoeren() {
	      return home();
	    }
	  }
	}
}
