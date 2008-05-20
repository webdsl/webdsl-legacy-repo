module datamodel/user

section users

  entity User {
    username        :: String (name)
    name            :: String
    email           :: Email (id)
    password        :: Secret
    isAdmin         :: Bool
  }

  define page editUser(us : User) {
    main()
    define body() {
      header{"Edit " output(us.name)}
      form {
        table {
          derive editRows from us
          row {
            action("Save", save())
            action save() {
              us.password = us.password.digest();
              us.save();
              return user(us);
            }
          }
        }
      }
    }
  }
  
  globals {
  
    // getUser: find a user or make a new user
    
    function getUser(name : String, email : Email) : User {
      var u : User;
      var users : List<User> :=
            select u from User as u 
            where (u._email = ~email);
      if(users.length = 0) {
        u := User { 
          name       := name
          email      := email
          password   := "secret"
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
