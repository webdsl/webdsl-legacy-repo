module task-ui

define page tasks() {
  main{
    section{
      header{"Tasks"}
      list{
        for(task : Task where !task.archived) {
          listitem{
            showTask(task)
          }
        }
      }
      form{
        var t := Task{};
        input(t.name)
        action("Add", add())
        action add() {
          t.save();
          return task(t);
        }
      }
    }
  }
}

define showTask(task : Task) {
  output(task.done)
  output(task)
  manageTask(task)
}

define manageTask(t : Task) {
  action done() { t.done := true; }
  action archive() { t.archived := true; }
  form{
    action("Done", done())
    if(t.done) { action("Archive", archive()) }
  }
}

define output(t : Task){
  navigate(task(t)){output(t.name)}
}

define page task(t : Task) {
  main{
    section{
      header{output(t.name)}
      par{output(t.description)}
      par{ 
        navigate(edittask(t)){"Edit"} " "
        navigate(tasks()){"All tasks"}
      }
      manageTask(t)
    }
  }
}

define page edittask(t : Task) {
  main{
    section{
      header{"Edit: " output(t.name)}
      form{
        par{input(t.name)}
        input(t.description)
        par{ action("Save", action{ return task(t); }) }
      }
    }
  }
}