application tasks

define page task (task : Task, tab : String) {
  var caseval0 : String := tab ;
  if (caseval0 == "view") {
    viewTask(task){}
  }
  else {
    if (caseval0 == "edit") {
      editTask(task){}
    }
    else { }
  }
}