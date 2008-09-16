module templates

section main template

  define main() {
    form() {
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
  }

section basic page elements
  
  define sidebar() {
    list { listitem{ navigate(user(jippe)) {"Home"} } }
  }
  
  define body(){
    "default body"
  }
  
section menus
  
      define menu() {
        menubar() {
          dropdownmenu() {
            header() { "File" }
	    menuitem() { navigate(home()){"Home"} }
	    menuitem() { navigate(user(jippe)){"View user"} }
	    menuitem() { navigate(createUser()){"Create user"} }
	  }
        }
      }