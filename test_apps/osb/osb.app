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
}
