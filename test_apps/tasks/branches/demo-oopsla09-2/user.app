module user

section data model

entity User {
  username :: String (id, name, validate(isUniqueUser(this), "Username is taken"))
  password :: Secret
}

define page user(u : User) { main{ header{ output(u.username) } } }

section user registration

define page register() {
  var u: User := User{}
  var password : Secret
  action register() {
    validate(u.password == password, "Passwords do not match");
    u.password := u.password.digest();
    u.save(); 
    return user(u);
  }
  main{
    header{"Register as WebTasks User"}
    form{
      group {
        groupitem{ "Username: " input(u.username) }
        groupitem{ "Password: " input(u.password) }
        groupitem{ "Verify password: " input(password) }
      }
      action("Register", register())
    }
  }
}
