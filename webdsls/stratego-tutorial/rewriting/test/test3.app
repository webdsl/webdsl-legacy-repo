application tasks

define page newuser() {
  var user : User := User {}
  section{
    header{"Add new user"}
    form{
      table{
        derive editRows from user for ( username )
      }
      action("Add", add())
      action add() {
        user.save(); 
        return tasks(user);
      }
    }
  }
}
