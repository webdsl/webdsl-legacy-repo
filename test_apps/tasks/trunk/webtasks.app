application webtasks

imports layouts

section data model

entity User {
  username   :: String (id, name, validate(isUniqueUser(this), "Username is taken"))
  password   :: Secret
  tasks      -> List<Task>
  unfinished -> List<Task> := [t | t : Task in this.tasks where !(t.done)]
  todo       -> List<Task> := [t | t : Task in this.tasks where !(t.archived)]
  archive    -> List<Task> := [t | t : Task in this.tasks where t.archived]
}

entity Task {
  name        :: String (name)
  description :: Text
  done        :: Bool
  archived    :: Bool
  user        -> User (inverse=User.tasks)
}

section user management

define page root() {
  main{
    section{
      header{"Users"}
      usertable{
        for(user : User) { userrow(user) }
      }
    }
  }
}

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

section task management

define output(user : User) {
  navigate(tasks(user)){output(user.username)}
}

define page tasks(user : User) {
  main{
    section{
      header{"Tasks for " output(user.username) }
      table{
        taskList(user.todo)
        addTask(user)
      }
      navigate(archive(user)){"Archive"}
    }
  }
}

define page archive(user : User) {
  main{
    section{
      header{"Archive for " output(user.username) }
      table{
        taskList(user.archive)
      }
      navigate(tasks(user)){"Tasks"}
    }
  }
}

define taskList(tasks : List<Task>) {
  for(task : Task in tasks) {
    row{ 
      output(task.done)
      output(task)
      column{ manageTask(task) }
    }
  }
}

define addTask(user : User) {
  var newTask : Task := Task{ done := false } 
  action addtask() { 
    user.tasks.add(newTask); 
    newTask.user := user;
    newTask.save(); 
  }
  form{
    row{
      column{}
      input(newTask.name)
      action("Add Task", addtask())
    }
  }
}

define manageTask(task : Task) {
  action done() { 
    task.done := true; 
  } 
  action undo() { 
    task.done := false; 
    task.archived := false;
  } 
  action archive() { 
    task.archived := true;
  }
  action revive() { 
    task.archived := false;
  }
  action delete() {
    var user := task.user;
    user.tasks.remove(task);
    task.delete();
    return tasks(user);
  }
  form{ " " 
    navigatebutton(edittask(task), "Edit")
    if(!task.done) {
      action("Done", done())
    } else { 
      action("Undo", undo())
      if(!task.archived) {
        action("Archive", archive())
      } else {
        action("Revive", revive())
      }
    }
    action("Delete", delete())
  }
}

define page task(task : Task) {
  main{
    section{
      header{output(task.name)}
      output(task.description)
      <p/>
      "Done: " output(task.done)
      <p/>
      "Archived: " output(task.archived)
      <p/>
      "Assigned to: " output(task.user)
      <p/>
      manageTask(task)
    }
  }
}

define page edittask(task : Task) {
  action save() {
    task.save();
    return task(task);
  }
  main{
  //  derive editPage from task
    section{
      header{output(task.name)}
      form{
        input(task.name)
        <p/>
        input(task.description)
        <p/>
        "Assigned to: " input(task.user)
        <p/>
        action("Save", save())
        navigatebutton(task(task), "Cancel")
      }
    }
  }
}

section authentication

function principal() : User {
  return securityContext.principal;
}

function auth(username : String, password : Secret) : Bool {
  var user : User := getUniqueUser(username);
  if(user != null) {
    if(user.password.check(password)) {
      securityContext.principal := user;
      securityContext.loggedIn := true;
      return true;
    }
  }
  securityContext.loggedIn := false;
  return false;
}

function signoff() {
  securityContext.loggedIn := false;
  securityContext.principal := null;
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
    if(auth(username, password)) {
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
  action signoffAction() {
    signoff();
  }
  if(!loggedIn()) {
    <li>navigate(signin()){"Sign in"}</li>
  } else {
    <li>navigate(tasks(principal())){output(principal().username)}</li>
    form{ 
      <li>actionLink("Sign off", signoffAction())</li>
    }
  }
}

access control rules

principal is User with credentials username, password

access control rules

rule template *(*) { true }

rule page *(*) { true }

rule template manageTask(task : Task) {
  securityContext.loggedIn
  && task.user == securityContext.principal
}

rule template removeUser(user : User) {
  securityContext.loggedIn
  && user == securityContext.principal
}

rule template userrow(user : User) {
  securityContext.loggedIn
}
