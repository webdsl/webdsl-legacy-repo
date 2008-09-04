module templates

section templates

  define displayMessage(m : Message) {
    section {
      block("metadata") {
        output(m.sender) " on " output(m.date) ": "
      }
      output(m.text)
    }
  }

section main template.

  define main() {
    top()
    topmenu()
    sidebar()
    body()
    footer()
  }

section basic page elements.

  define sidebar() {
    section {
      if(securityContext.loggedIn) {
        header { "People you follow" }
        list {
          for(u : User in securityContext.principal.following order by u.username asc) {
            listitem { output(u) }
          }
        }
      } else {
        header { "New Dieslrs" }
        list {
          for(u : User order by u.signupDate desc limit 10) {
            listitem { output(u) }
          }
        }
      }
    }
  }

  define top() {
    block("logos") {
      image("/images/logo.png")
    }
    block("text") {
      if(securityContext.loggedIn) {
        section {
          "Logged in as " output(securityContext.principal)

          form {
            var msg : String
            group("Send message") {
              input(msg) action("Send", send()) 
            }

            action send() {
              var m : Message := Message{};
              m.sender := securityContext.principal;
              m.recipient := securityContext.principal;
              m.text := msg;
              m.original := true;
              m.save();
              broadcastMessage(securityContext.principal, msg);
              return home();
            }
          }
        }
      }
    }
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
      if(securityContext.loggedIn) {
        menu {
          menuheader { navigate(logout()) { "Logout" } }
        }
      } else {
        menu {
          menuheader { navigate(register()) { "Register" } }
        }
        menu {
          menuheader { navigate(login()) { "Login" } }
        }
      }
    }
  }

section default pages

  define page info(msg : String) {
    main()
    title { "Info" }
    define body() {
      section {
        header { "Info" }
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
