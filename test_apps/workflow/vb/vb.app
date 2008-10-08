application com.example.vb

imports templates

section data

  entity Persoon {
    name :: String
  }
  
  globals {
    /** 
     * PROBLEEM: in globals{} kunnen volgens mij alleen VarDeclInits. Onderstaande kan daarom niet. Procedures kunnen daarom
     * niet direct worden uitgevoerd.
     *   var pRuben : Persoon := newPersoon(); 
     *   pRuben.name := "Ruben";
     */
    var pRuben : Persoon := Persoon { name := "Ruben" };
    var pZef : Persoon := Persoon { name := "Zef" };
  }

section pages

  define page initialize() {
    init {
      /**
       * PROBLEEM: Omdat een ProcedureStatus een verwijzing naar het object nodig heeft, en je geen self-referential object
       *   kunt maken mbv een vardeclinit, kan procedure-data alleen worden geinitialized in een losse pagina
       */
      
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
      var achternaam : String; // PROBLEEM: als deze declaratie verplaatst wordt naar define local body, werkt het niet. Maar misschien is dat juist goed.
      main()
      define local body { // PROBLEEM: als ik local weglaat, gaat het mis
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