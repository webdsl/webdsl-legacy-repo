module authentication

section auth stuff.
define page register()
{
  main()
  define body()
  {
    header{"Register"}
      var newUser : User := User { };
      form { 
        table {
          row{ "Username: " input(newUser.username) }
          row{ "Password: " input(newUser.password) }
        }
        action("Create", createUser())
        action createUser() {
          newUser.viewAccess := priv;
          newUser.password := newUser.password.digest();
          newUser.persist();
          return home();
        }
      }  
  }
}


define login()
{
  header{"Login"}
  var usr : User := User{};

  form { 
    table {
      row{ "username: " input(usr.username) }
      row{ "password: " input(usr.password) }
    }
    action("login", login())
    action login() {
      var users : List<User> :=
        select u from User as u 
        where (u._username = ~usr.username);
            
      for (us : User in users ) {
        if (us.password.check(usr.password))
        {
        securityContext.principal := us;
        securityContext.loggedIn := true;
        return home();
        }
      }
      return home();
    }
  }

}

define logout()
{
  form
  {   
    action("logout", logout())
    action logout() {
      securityContext.loggedIn:=false;
      securityContext.principal:=null;
      return home();
    }
  }
}