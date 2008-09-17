module ac


access control rules 

  rule page createMeeting() {
    true
  }

  rule page *(*) {
    true
  }
  rule action *(*) {
    true
  }
  rule template *(*) {
    true
  }

section pages

  principal is User with credentials username, password

  define page signin() {
    main()
    title{"Log in"}
    define body() {
      var username : String;
      var password : Secret;
      form { 
        group("Login info") {
          groupitem { label("Username:") { input(username) } }
          groupitem { label("Password:") { input(password) } }
        }
        action("Sign in", signin())
        action signin() {
          for (us : User where us.username == username) {
            if (us.password.check(password)) {
              securityContext.principal := us;
              securityContext.loggedIn := true;
              return allTasks();
            }
          }
          securityContext.loggedIn := false;
          return error("Wrong combination of username and password");
        }
      }
    }
  }
  
  define page error(msg : String) {
    main()
    title{"Error"}
    define body() {
      header{"Error"}
      output(msg)
    }
  }

  define page message(msg : String) {
    main()
    title{"Message"}
    define body() {
      header{"Message"}
      output(msg)
    }
  }
  
