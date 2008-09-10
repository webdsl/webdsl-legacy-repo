module templates

section templates

  define displayPost(p : Post) {
    section {
      header { output(p) }
      block("metadata") {
        "Posted by " output(p.author) " on " output(p.date)  " | "
        navigate(post(p)) {
          if(p.commentCount == 0) {
            "Comment"
          } else {
            if(p.commentCount == 1) {
              "1 Comment"
            } else {
              output(p.commentCount) " Comments"
            }
          }
        }
        if(securityContext.loggedIn) {
          " | " navigate(editPost(p))
        }
      }
      output(p.text)
    }
  }

  define displayComment(c : Comment) {
    section {
      output(c.text)
      block("commentmetadata") { "Posted by " output(c.name) " on " output(c.date) }
    }
  }

section main template.

  define main() {
    top()
    sidebar()
    body()
    footer()
  }

section basic page elements.

  define sidebar() {
    section {
      header { "Menu" }
      list {
        listitem { navigate(home()) { "Home" } }
        if(securityContext.loggedIn) {
          listitem { navigate(logout()) { "Logout" } }
          listitem { navigate(newPost()) { "New post" } }
        } else {
          listitem { navigate(register()) { "Register" } }
          listitem { navigate(login()) { "Login" } }
        }
      }
      header { "Recent posts" }
      list {
        for(p : Post where p.status == publishedStatus order by p.date desc limit 5) {
          listitem { output(p) }
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
        }
      }
    }
  }

  define body() {
    "Welcome to blog!"
  }
  
  define footer() {
    block("footer_links") {
      list {
        listitem { navigate(page(aboutPage)) { "About" } }
        //listitem { navigate(home()) { "Privacy" } }
        //listitem { navigate(home()) { "Disclaimer" } }
        listitem { navigate(url("http://www.webdsl.org")) { "About WebDSL" } }
      }
    }
    
    block("footer_text") {
      text("Blog :: awesome!")
    }
  }

  /*
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
  */

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
