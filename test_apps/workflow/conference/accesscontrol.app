module accesscontrol

section access control

  access control rules {
    principal is User with credentials username, password

    rules page *(*) { true }
    rules template *(*) { true }
    //rules action *(*) { true }
    
    pointcut defaultAccess() { 
        page register()
      , page signIn()
      , page signOut()
      , page home()
      , page allConference()
      , page error(*)
      , page message(*)
      , page conference(*)
    }
    
    rules pointcut defaultAccess() {
      true
    }
    
    pointcut adminOperations() {
        page create*(*)
      , page edit*(*)
    }
    
    rules pointcut adminOperations() {
      securityContext.principal != null && securityContext.principal.isAdmin
    }

    rules page editConference(c : Conference) {
      securityContext.principal != null && (securityContext.principal.isAdmin || securityContext.principal in c.chairs)
    }
    
  }
  

section access control pages

  define page signIn() {
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
  
  define page signOut() {
    main()
    init{
      securityContext.loggedIn := false;
      //return signIn();
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
                  return signIn();
                }
              }
              return error("Username or email address already in use");
            } else {  
              newUser.password := newUser.password.digest();
              newUser.registered := true;
              newUser.persist();
              return signIn();
            }
          }
        }
      }
    }
  }

