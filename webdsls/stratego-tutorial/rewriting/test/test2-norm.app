application tasks

define page home () {
  section(){
    header(){
      output("Users"){
      }
    } 
    list(){
      for ( user : User ) {
        listitem(){
          navigate(tasks(user)){
            output(user.username){
            }
          } 
          text(" ("){
          } 
          output(user.tasks.length){
          } 
          text(")"){
          }
        }
      }
    } 
    navigate(newuser()){
      text("Add new user"){
      }
    }
  }
  navigate(url("http://webdsl.org")){
    output("http://webdsl.org"){
    }
  }
}

define page testif (user : User) {
  if ( securityContext.principal.username == "Eelco" ) {
    navigate(adminpage()){
      text("[Admin]"){
      }
    }
  }
  else
  {
  }
}

entity Log {
  function log ( x : String ) : Void
  {
    print(x);
  }
}