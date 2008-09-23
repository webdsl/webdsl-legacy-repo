module static


section pages
  
  define page home() {
    top()
    block("menu") {
      topmenu()
      quickadd()
      quicksearch()
    }
    main()
    footer()
  }

section templates



  define top() {
    block("logos") {
      image("/images/gohome.svg")
    }
    block("text") {
      header{"Welcome to your notes!"}
    }
  }
  
  define topmenu() {
    navigate(home()) { "the notes"  }
  }
  
  define footer() {
    block("footer_links") {
      list {
        listitem { navigate(url("mailto:mweststrate@gmail.com")){ "by Michel Weststrate" }}
        listitem { navigate(url("http://www.webdsl.org")) { "About WebDSL" } }
      }
    }
    
    block("footer_text") {
      text("MyNote :: a WebDSL AJAX demonstration")
    }
  }