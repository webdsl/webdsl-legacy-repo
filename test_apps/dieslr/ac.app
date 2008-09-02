module ac

section access control

  principal is User with credentials username, password
  
access control rules

  rule page *(*) {
      true
  }

  rule template *(*) {
      true
  }

  rule action *(*) {
      true
  }

section ac login stuff

  define page login() {
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
          for (us : User where us.username = username) {
            if (us.password.check(password)) {
              securityContext.principal := us;
              securityContext.loggedIn := true;
              return message("You have been logged in.");
            }
          }
          securityContext.loggedIn := false;
          return error("Wrong combination of username and password");
        }
      }
    }
  }

  define page logout() {
    init {
      securityContext.loggedIn := false;
      securityContext.principal := null;
      goto home();
    }
  }

  define page register() {
    title {"Register"}
    main()
    define body() {
      var user : User := User{};
      form {
        group("Register") {
          groupitem { label("Username:") { input(user.username) } }
          groupitem { label("Password:") { input(user.password) } }
        }
        action("Register", doRegister())
      }
      action doRegister() {
        for(u : User where u.username = user.username) {
          return error("User already registered. Sorry.");
        }
        user.password := user.password.digest();
        user.save();
        return message("You have now been registered. Click Login to login.");
      }
    }
  }
