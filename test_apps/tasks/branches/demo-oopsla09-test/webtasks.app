application webtasks

imports layout
imports user
imports task

define page root() {
  main{
    header{"Users"}
    list{
      for(user : User) { 
        listitem{ output(user) " (" output(user.unfinished.length) ")" }
      }
    }
  }
}

