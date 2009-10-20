module task-ui

define output(user : User) {
  navigate(tasks(user)){output(user.username)}
}

define page tasks(user : User) {
  main{
    section{
      header{"Tasks for " output(user.username) }
      taskList(user.todo)
      par{addTask(user)}
      par{navigate(archive(user)){"Archive"}}
    }
  }
}

define page archive(user : User) {
  main{
    section{
      header{"Archive for " output(user.username) }
      taskList(user.archive)
      navigate(tasks(user)){"Tasks"}
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
      par{ output(task.description) }
      par{ "Done: " output(task.done) }
      par{ "Archived: " output(task.archived) }
      par{ "Assigned to: " output(task.user) }
      par{ manageTask(task) }
    }
  }
}

define page edittask(task : Task) {
  main{
    section{
      header{output(task.name)}
      form{
        par{ input(task.name) }
        par{ input(task.description) }
        par{ "Assigned to: " input(task.user) }
        par{ 
          action("Save", save())
          navigatebutton(task(task), "Cancel")
        }
        action save() {
          task.save();
          return task(task);
        }
      }
    }
  }
}