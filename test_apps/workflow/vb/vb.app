application com.example.vb

imports templates

section data

  entity Persoon {
    name :: String
  }
  
  globals {
    var pRuben : Persoon := Persoon { name := "Ruben" };
    var pZef : Persoon := Persoon { name := "Zef" };
  }

section pages

  define page initialize() {
    init {
      pRuben.addAchternaam := AddAchternaamProcedureStatus{};
      pRuben.addAchternaam.p := pRuben;
      pRuben.addAchternaam.persist();
      pRuben.persist();
      pRuben.addAchternaam.enable();
      
      pZef.addAchternaam := AddAchternaamProcedureStatus{};
      pZef.addAchternaam.p := pZef;
      pZef.addAchternaam.persist();
      pZef.persist();
      pZef.addAchternaam.enable();
    }
    main()
    define body() {
      par{"Initialization performed (initializing procedures for existing objects)"}
    }
  }

  define page persoon(p : Persoon) {
    derive viewPage from p
  }

  define page home() {
  	main()
  	define body() {
  	  par{navigate(initialize()) {"Initialize"}}
  		par{navigate(allTasks()) {"Tasks"}}
  	}
  }

section procedures

  procedure addAchternaam(p : Persoon) {
    view {
      var achternaam : String;
      main()
      define local body {
        form {
          label("Naam"){output(p.name)}
          input(achternaam)
          action("Voeg Achternaam Toe", do())
        }
      }
    }
    do {
      p.name := p.name + " " + achternaam;
      p.persist();
    }
  }