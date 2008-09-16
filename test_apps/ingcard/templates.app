module templates

section main template.

  define main() {
  	top()
  	sidebar()
  	body()
  }

section basic page elements.

  define top() {
  	image("/images/ing_card_logo.png")
  }

  define sidebar() {
    sideMenu()
  }
  
  define body() {
  	"Standard body"
  }

  
section menus.
  
  define sideMenu() {
    list {
        listitem { navigate(home()) { "Home" } }
        listitem { navigate(home()) { "Create Card Request" } }
        listitem { navigate(home()) { "Work Card Request" } }
        listitem { navigate(home()) { "Scoring" } }
        listitem { navigate(home()) { "Maintenance" } }
        listitem { navigate(home()) { "Digital Applications" } }
        listitem { navigate(home()) { "Pending Requests" } }
        listitem { navigate(home()) { "Letters" } }
    }
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

