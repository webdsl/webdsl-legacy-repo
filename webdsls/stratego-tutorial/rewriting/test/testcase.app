application tasks

define page task(task : Task, tab : String) {
  case(tab) {
    "view" { viewTask(task) }
    "edit" { editTask(task) }
  }
}