module utils

section utilities

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
  
  globals {
    /**
     * getUser: retrieves a user or makes a new user and returns it
     */
    function getUser(name : String, email : Email) : User {
      var u : User;
      var users : List<User> :=
            select u from User as u 
            where (u._email = ~email);
      if(users.length = 0) {
        u := User { 
          name := name
          email := email
          password := "secret"
          registered := false 
        };
        u.persist();
      } else {
        for(eu : User in users) { // HACK!
          u := eu;
        }
      }
      return u;
    }
  }
