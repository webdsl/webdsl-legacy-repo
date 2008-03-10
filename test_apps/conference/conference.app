application com.example.conference

description {
  Conference application
}

imports templates
imports data
imports ac
imports initialize

section pages

define page home() {
  main()
  define body() {
    list {
      listitem { navigate(allConference()) { "All conferences" } }
      listitem { navigate(createConference()) { "Create conference" } }
      listitem { navigate(createPaper()) { "Submit paper" } }
      listitem { navigate(createReview()) { "Write review" } }      
    }                        
  }
}

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
  
        for (us : User in users ) {
          if (us.password.check(password)) {
            securityContext.principal := us;
            securityContext.loggedIn := true;
            return user(securityContext.principal);
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
            return error("Username or email address already in use");
          } else {  
            newUser.password := newUser.password.digest();
            newUser.persist();
            return signin();
          }
        }
      }
    }
  }
}

define feed createConference() {
  
}


define page error(msg : String) {
  main()
  title{"Error"}
  define body() {
    header{"Error"}
    output(msg)
  }
}
