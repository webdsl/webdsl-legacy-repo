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
    
          for (us : User in users  where us.registered) {
            if (us.password.check(password)) {
              securityContext.principal := us;
              securityContext.loggedIn := true;
              return tasks(securityContext.principal);
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
            row { "Full name" input(newUser.name) }
            row { "Email" input(newUser.email) }
            row { "Password" input(newUser.password) }
          }
          captcha()
          action("Register", createUser())
          action createUser() {
            var users : List<User> :=
              select u from User as u 
              where (u._username = ~newUser.username) or (u._email = ~newUser.email);
              
            if (users.length > 0) {
              for(u : User in users) {
                if(!u.registered) { // Future user, now fill in the details
                  u.username := newUser.username;
                  u.name := newUser.name;
                  u.password := newUser.password.digest();
                  u.registered := true;
                  return signin();
                }
              }
              return error("Username or email address already in use");
            } else {  
              newUser.password := newUser.password.digest();
              newUser.registered := true;
              newUser.persist();
              return signin();
            }
          }
        }
      }
    }
  }

  access control rules {
    rules page tasks(u : User) {
      true
    }
  }

  define page tasks(u : User) {
    init {
      if(!securityContext.loggedIn) {
        goto signin();
      }
    }
    title {"Task list"}
    main()
    define body() {
      section {
        header{"Task to do" }
        list {
          for(t : ConferenceTask in u.tasksList where !t.completed) {
            listitem { output(t) }
          }
        }
      }
      section {
        header{"Tasks already done"}
        list {
          for(t : ConferenceTask in u.tasksList where t.completed) {
            listitem { output(t.name) }
          }
        }
      }
    }
  }

  access control rules {
    rules page selectActiveRoles() {
      securityContext.loggedIn
    }
  }
  define page selectActiveRoles() {
    title{"Set active roles"}
    main()
    define body() {
      header{"Select active roles"}
      form {
        select(securityContext.activeRoles from securityContext.principal.roles)

        action("Set", setRoles())
        action setRoles() {
          return home();
        }
      }
    }
  }
