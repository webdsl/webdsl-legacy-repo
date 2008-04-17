module authentication

section auth stuff.


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