module templates

section templates

  define viewUpdate(u : Update) {
    section {
      block("metadata") {
        output(u.user) " on " output(u.date) ": "
      }
      output(u.text)
    }
  }

section main template.

  define main() {
    top()
    topmenu()
    //sidebar()
    body()
    footer()
  }

section basic page elements.

  define top() {
    block("logos") {
      image("/images/logo.png")
    }
    block("text") {
      if(securityContext.loggedIn) {
        section {
          "Logged in as " output(securityContext.principal)
          var msg : String
          form {
            input(msg)
            action("Update", update())

            action update() {
              var u : Update := Update {};
              u.user := securityContext.principal;
              u.text := msg;
              u.save();
              return user(securityContext.principal);
            }
          }
        }
      }
    }
  }
  
  define sidebar() {
    header { "Side Menu" }
  }
  
  define body() {
    "Welcome to the Diesler"
  }

  define footer() {
    block("footer_links") {
      list {
        listitem { navigate(home()) { "About" } }
        listitem { navigate(home()) { "Privacy" } }
        listitem { navigate(home()) { "Disclaimer" } }
        listitem { navigate(url("http://www.webdsl.org")) { "About WebDSL" } }
      }
    }
    
    block("footer_text") {
      text("Diesler :: a WebDSL demonstration")
    }
  }

  define topmenu() {
  
    menubar {
      menu {
        menuheader { navigate(home()) { "Home" } }
      }
      menu {
        menuheader { navigate(home()) { "Updates" } }
        //menuitem { navigate(vets()) { "Display veterinarians" } }
      }
      menu {
        menuheader { "User" } 
        menuitem { navigate(login()) { "Login" } }
        menuitem { navigate(register()) { "Register" } }
        menuspacer
        for (u : User) {
          menuitem { output(u) }
        }
      }
    }
  }

section default pages

  define page message(msg : String) {
    main()
    title { "Message" }
    define body() {
      section {
        header { "Message" }
        output(msg)
      }
    }
  }

  define page error(msg : String) {
    main()
    title { "Error" }
    define body() {
      section {
        header { "Error" }
        output(msg)
      }
    }
  }
