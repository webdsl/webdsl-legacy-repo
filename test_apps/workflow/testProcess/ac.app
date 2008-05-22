module ac


access control rules 

  rule page createPdpMeeting() {
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
    title{"Sign in"}
    define body() {
      var username : String;
      var password : Secret;
      form { 
        table {
          row{ "Username: " input(username) }
          row{ "Password: " input(password) }
          row{ action("Sign in", signin()) "" }
        }
        action signin() {
          var users : List<User> :=
            select u from User as u 
            where (u._username = ~username);
    
          for (us : User in users) {
            if (us.password.check(password)) {
              securityContext.principal := us;
              securityContext.loggedIn := true;
              return allTasks();
            }
          }
          securityContext.loggedIn := false;
          return error("Wrong combination of username/password");
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
  
