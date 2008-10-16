application com.example.subprocedure

imports templates

section data

  entity Persoon {
    name :: String
  }

section procedures

  procedure a(p : Persoon) {
    process {
      a1(p)
      ; repeat { a2(p) } until a3(p)
    }
  }
  procedure a1(p : Persoon) { }
  procedure a2(p : Persoon) { }
  procedure a3(p : Persoon) { }
  
/*  procedure b(p : Persoon) { }*/

section pages

  define page persoon(p : Persoon) {
    derive viewPage from p
  }

  define page home() {
  	main()
  	define body() {
  	  par{navigate(startTest()){"Start test"}}
  		par{navigate(allTasks()){"Tasks"}}
  	}
  }

  define page startTest() {
    init {
      var p : Persoon := newPersoon();
      p.name := "Ruben";
      p.persist();
      p.a.enable();
    }
    main()
    define body() {
      par{"Test started"}
  		par{navigate(allTasks()) {"Tasks"}}
    }
  }
  
  define page allPersoon() {
    for (p : Persoon) {
      par{navigate(persoon(p))}
    }
  }