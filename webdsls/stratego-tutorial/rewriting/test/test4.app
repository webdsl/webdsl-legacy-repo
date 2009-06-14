application tasks

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
