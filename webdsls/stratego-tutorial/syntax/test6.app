application tasks

entity User {
  username :: String (id,name)
  tasks    -> Set<Task>
  log      -> Set<Task>
}

entity Task {
  description :: String (name)
  done        :: Bool
}

define page home() {
  section{
    header{"Users"}
    list{ 
      for(user : User) {
        listitem{ 
          navigate(tasks(user)){output(user.username)} 
          " (" output(user.tasks.length) ")" 
        }
      }
    }
    navigate(newuser()){"Add new user"}
  }
}

define page newuser() {
  var user : User := User {}
  section{
    header{"Add new user"}
    form{
      table{
        derive editRows from user for ( username )
      }
      action("Add", add())
      action add() {
        user.save(); 
        return tasks(user);
      }
    }
  }
}

define page tasks(user : User) {
  var newTask : Task := Task{ done := false } 
  action addtask() { 
    user.tasks.add(newTask); 
    newTask.save(); 
  }
  action done(task : Task) { 
    task.done := true; 
    task.save(); 
  }
  action archive(task : Task) { 
    user.tasks.remove(task); 
    user.log.add(task);
    user.save(); 
  }
  section{
    header{"Tasks for " output(user.username) }
    table{
      for(task : Task in user.tasks) {
        row{ 
          column{ output(task.done) }
          column{ navigate(task(user, task,"")){output(task.name)} }
          column{ 
            form{ 
              if(!task.done) {
                action("Done", done(task))
              } else {
                action("Archive", archive(task))
              }
            }
          }
        }
      }
      form{
        row{
          column{}
          column{ input(newTask.description) }
          column{ action("Add Task", addtask()) }
        }
      }
    }
    navigate(home()){"Home"}
  }
}

define page taskold(user : User, task : Task) {
  derive viewPage from task
  navigate(tasks(user)){output(user.name)}
}

define page task(user : User, task : Task, tab : String) {
  case(tab) {
    "" {
      derive viewPage from task
      navigate(tasks(user)){output(user.name)} " "
      navigate(task(user,task,"edit")){"Edit"}
    }
    "edit" {
      form{
        input(task.description)
        action("Save", save())
        action save() {
          task.save();
          return task(user,task,"");
        }
      }
      navigate(tasks(user)){output(user.name)} " "
      navigate(task(user,task,"")){"View"}
    }
  }
}
