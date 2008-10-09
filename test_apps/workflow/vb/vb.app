application com.example.vb

imports templates

section data

  entity Persoon {
    name :: String
  }
  
  /** 
   * PROBLEM: in globals{}, only VarDeclInits can be used. The following code will therefore not work. Thus, procedures cannot
   *   be performed for objects that are created using initialization code, unless they are initialized inside a page (see define
   *   page initialize())
   *
   *   var pRuben : Persoon := newPersoon(); 
   *   pRuben.name := "Ruben";
   */
  var pRuben : Persoon := Persoon { name := "Ruben" };
  var pZef : Persoon := Persoon { name := "Zef" };

section pages

  init {
    /**
     * PROBLEM: As ProcedureStatus needs a reference to the object it works on, and self-referential objects cannot be created
     *   using VarDeclInits, procedures on objects defined in initialization data can only be initialized using a separate page.
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

  define page persoon(p : Persoon) {
    derive viewPage from p
  }

  define page home() {
  	main()
  	define body() {
  		par{navigate(allTasks()) {"Tasks"}}
  	}
  }

section procedures

  procedure addAchternaam(p : Persoon) {
    view {
      main()
      define body() { // PROBLEM: within procedure view sections, 'define body' will present problems; 'local' must be added.
        var achternaam : String; // PROBLEM: moving this declaration to the scope of define local body will present problems, but maybe this behaviour is actually good.
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
