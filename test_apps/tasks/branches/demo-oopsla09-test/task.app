module task

imports user

section data model

  entity Task {
    name        :: String (name)
    description :: Text
    done        :: Bool
    archived    :: Bool
    user        -> User (inverse=User.tasks)
    due         :: Date
    
    function owns() : Bool { return loggedIn() && user == securityContext.principal; }
    function remove() { user.tasks.remove(this); this.delete(); }
  }

  extend entity User {
    tasks      -> List<Task>
    unfinished -> List<Task> := [t | t : Task in this.tasks where !(t.done)]
    todo       -> List<Task> := [t | t : Task in this.tasks where !(t.archived)]
    archive    -> List<Task> := [t | t : Task in this.tasks where t.archived]
  }

access control rules

  rule template addTask(u : User)    { loggedIn }
  rule page edittask(t : Task)       { t.owns() }
  rule template manageTask(t : Task) { t.owns() }

section task list

define page user(u : User) {
  main{
    header{"Tasks for " output(u.username) }
    taskList(u.todo)
    par{addTask(u)}
    par{navigate(archive(u)){"Archive"}}
    deleteUser(u)
  }
}

define page archive(u : User) {
  main{
    section{
      header{"Archive for " output(u.username) }
      taskList(u.archive)
      navigate(user(u)){"Tasks"}
    }
  }
}

define taskList(tasks : List<Task>) {
  table{
    for(task : Task in tasks) {
      row{ 
        output(task.done)
        output(task)
        column{ manageTask(task) }
      }
    }
  }
}

section task

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

define page task(task : Task) {
  main{
    header{output(task.name)}
    par{ output(task.description) }
    table{
      derive viewRows from task for (due, done, archived, user)
    }
    par{ manageTask(task) }
  }
}

define page edittask(task : Task) {
  main{
    header{output(task.name) " (Edit)"}
    form{
      group{ derive editRows from task for (name, description, due, user) }
      action("Save", action{ return task(task); })
    }
  }
}

section manage task

define manageTask(task : Task) {
  action done()    { task.done := true; }
  action undo()    { task.done := false; task.archived := false; } 
  action archive() { task.archived := true; }
  action revive()  { task.archived := false; }
  action delete()  { 
    var u : User := task.user; task.remove(); return user(task.user); 
  }
  form{
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