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
    list() {
      listitem() { navigate(home()) {"Home"} }
      listitem() { navigate(manytomany()) {"Many-to-many"} }
      listitem() { navigate(manytoone()) {"Many-to-one"} }
//      listitem() { navigate(onetoone()) {"One-to-one"} }
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

