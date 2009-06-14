application tasks

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
