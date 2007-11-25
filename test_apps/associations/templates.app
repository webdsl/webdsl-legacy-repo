module templates

section main template.

  define main() {
    div("outersidebar") {
      sidebar()
    }
    div("outerbody") {
      div("menubar") {
        menu()
      }
      body()
    }
  }

section basic page elements.

  define sidebar() {
    navigate(home()) {"Home"}
    header() {"Unidirectional"}
    list() {
      listitem() { navigate(umanytomany()) {"Many-to-many"} }
      listitem() { navigate(umanytoone()) {"Many-to-one"} }
      listitem() { navigate(uonetomany()) {"One-to-many"} }
      listitem() { navigate(onetoone()) {"One-to-one"} }
    }
    header() {"Bidirectional"}
    list() {
      listitem() { navigate(manytomany()) {"Many-to-many"} }
      listitem() { navigate(manytoone()) {"Many-to-one"} }
      listitem() { navigate(onetomany()) {"One-to-many"} }
      listitem() { navigate(onetoone()) {"One-to-one"} }
    }
  }

section menus.
  
  define menu() {

    
  }
  
section entity management.

  define manageMenu() {}
  
  define page manage() {
    main()
    define sidebar() {}
    define body() {
      createMenu()
      allMenu()
    }
  }

