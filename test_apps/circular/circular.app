application whatever

imports templates

section data

  entity Pet {
    name :: String (name)
    owner -> Person
  }
  
  entity Person {
    name :: String (name)
    pet -> Pet
  }
  
  globals {
    var danny : Person := Person {
      name := "Danny"
      pet := dexter
    };
    
   var dexter : Pet := Pet {
     name := "Dexter"
     owner := danny
   };
  }
  
section pages

  define page home() {
	main()
	define body() {
		"Hello world!"
		output(dexter.name)
	}
  
  }
