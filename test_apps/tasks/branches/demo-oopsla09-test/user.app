module user

section data model

entity User {
  username :: String (id, name, validate(isUniqueUser(this), "Username is taken"))
  password :: Secret
  
  function remove() {
    for(t : Task in tasks) { t.remove(); }
    this.delete();
  }
}

access control rules 

  principal is User with credentials username, password

  rule template *(*) { true }
  rule page *(*) { true }
  
  rule page register() { !loggedIn }
  rule page signin() { !loggedIn }
  rule template logout() { loggedIn }

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
        groupitem{ "Password: " input(password) }
      }
      action("Register", register())
    }
  }
}

define deleteUser(u : User) {
  form{ action("Delete User", action{ u.remove(); return root(); }) }
}
