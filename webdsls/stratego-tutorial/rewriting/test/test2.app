application tasks

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
