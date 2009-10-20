application webtasks

imports layouts
imports user-data
imports task-data
imports task-ui
imports user-ui
imports access-control

define page root() {
  main{
    section{
      header{"Users"}
      usertable{
        for(user : User) { userrow(user) }
      }
    }
  }
}


