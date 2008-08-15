module templates

section basic page elements.

  define main() {
    top()
    topmenubar()
	sidebar()
	mainbody()
    footer()
  }
  
  define topmenubar() {
    topmenu()
  }
  
  define topmenu() {
    menubar() {
      menu {
        menuheader { navigate(home()) { "Home" } }
      }
      menu {
        menuheader { navigate(home()) { "Products" } }
      }
      menu {
        menuheader { navigate(home()) { "Services" } }
      }
      menu {
        menuheader { navigate(home()) { "Contact" } }
      }
    }
  }

  define top() {
    block("logos") {
      image("images/webdsl_logo_small.png")
      image("images/webdsl_logo_text.png")
    }
    block("text") {
	  text("default header")
	}
  }
  
  define sidebar() {
    list {
      listitem { navigate(home()) { "Home" } }
      listitem { navigate(leftmenu()) { "Left menu" } }
    }
  }


  define mainbody() {
//    section{ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut." }
    main_sidebar()
    main_mainbody()
  }
  
  define main_sidebar() {
    section{ "Hello world! Standard mainbody" }
  }
  
  define main_mainbody() {
    section{ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut." }
    section{ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut." }
  }
  

  define footer() {
    block("footer_links") {
      list {
        listitem { navigate(home()) { "About" } }
        listitem { navigate(home()) { "Privacy" } }
        listitem { navigate(home()) { "Disclaimer" } }
      }
    }
    
    block("footer_text") {
      text("A WebDSL Layout Demonstration")
    }
  }

  