application tasks

define page home() {
  section("Users") {
    list{ 
      for(user : User) {
        listitem{ 
          navigate(user.username,tasks(user))
          text(" (", user.tasks.length, ")"){}
        }
      }
    }
    navigate(newuser()){"Add new user"}
  }
  navigate(url("http://webdsl.org"))
}

define page testif(user : User) {
  if(securityContext.principal.username == "Eelco") {
    navigate(adminpage()){"[Admin]"}
  }
}

entity Log {
  function log(x : String) {
    print(x);
  }
}

define page task(task : Task, tab : String) {
  case(tab) {
    "view" { viewTask(task) }
    "edit" { editTask(task) }
  }
}
