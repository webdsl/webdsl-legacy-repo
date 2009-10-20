module user-ui

define userrow(user : User) { 
  row{
    column{ output(user) }
    column{ output(user.unfinished.length) }
    column{ removeUser(user) }
  }
}

define removeUser(user : User) {
  action removeUser(user : User) {
    for(task : Task in user.tasks) {
      task.delete();
    }
    user.delete();
  }
  form{ 
    action("X", removeUser(user))
  }
}

define page register() {
  main{
    section{ 
      header{"Register as Web Tasks User"}
      addUser()
    }
  }
}

define addUser() {
  var user : User := User {}
  var password : Secret
  action add() {
    validate(user.password == password, "Passwords do not match");
    user.password := user.password.digest();
    user.save(); 
    return tasks(user); 
  }
  form{
    group{
      row{ "Username: " input(user.username) }
      row{ "Password: " input(user.password)}
      row{ "Password: " input(password) }
    }
    action("Add", add())
  }
}

define page signin() {
  main{
    section{
      header{"Sign in"}
      signinform()
    }
  }
}

define signinform() {
  var username : String
  var password : Secret
  action signin() {
    if(authenticate(username, password)) {
      message("Welcome " + username);
      return tasks(getUniqueUser(username));
    }
    message("Wrong username, password combination.");
  }
  form{
    table{
      row{ "Username: " input(username) }
      row{ "Password: " input(password) }
    }
    action("Sign in", signin())
  }
}

define signinoffMenu() {
  if(!loggedIn()) {
    listitem{ navigate(signin()){"Sign in"} }
  } else {
    listitem{ navigate(tasks(principal())){output(principal().username)} }
    form{ 
      listitem{ action("Sign off", signoffAction()) }
      action signoffAction() { signoff(); }
    }
  }
}
