application com.example.persist

description {
	This is an automatically generated description
}

imports templates

section data

  entity Persoon {
    name :: String
    vader -> Persoon
  }

section pages

define page persoon(p : Persoon) {
  derive viewPage from p
}

define page home() {
  init {
    var pEvert : Persoon := newPersoon();
    pEvert.name :=  "Evert";
    pEvert.persist();
    var pRuben : Persoon := newPersoon();
    pRuben.name :=  "Ruben";
    pRuben.vader := pEvert;
    pRuben.persist();
  }
	main()
	define body() {
		text("Created mensen")
	}
}

section procedures

  procedure addAchternaam(p : Persoon) {
    view {
      var vader : Persoon := p.vader;
      form {
        input(p.name)
        input(p.vader.name)
        action("Voeg Achternaam Toe", do())
      }
    }
    do {
      vader.persist();
      p.persist();
    }
  }