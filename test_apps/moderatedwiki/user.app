module user

section pages

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
              return home();
            }
          }
          securityContext.loggedIn := false;
          return error("Wrong combination of username/password");
        }
      }
      par{ navigate(register()){"Register new user"} }
    }
  }

  define page register() {
    main()
    title{"Register new user"}
    define body() {
      section {
        header{"Register New User"}
        
        var newUser : User := User { };
        form { 
          table{
            row { "Username" input(newUser.username) }
            row { "Email" input(newUser.email) }
            row { "Password" input(newUser.password) }
          }
          captcha()
          action("Register", createUser())
          action createUser() {
            var users : List<User> :=
              select u from User as u 
              where (u._username = ~newUser.username) or (u._email = ~newUser.email);
              
            newUser.password := newUser.password.digest();
            newUser.persist();
            return signin();
          }
        }
      }
    }
  }

  access control rules {
    rules page editUser(u : User) {
      adminRole in securityContext.principal.roles
    }
  }


