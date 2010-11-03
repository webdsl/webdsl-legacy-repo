application testsortable

  imports sortable

  entity User{
    name::String
    list -> List<User>
  }
  
  var u1 := User{name:="u1"}
  var u2 := User{name:="u2"}
  var u3 := User{name:="u3"}
  var u4 := User{name:="u4"}
    
  init{
    u1.list:=[u1,u2,u3,u4];
  }
    
  define page root(){
    form{
      sorter(u1.list)
      submit action{} {"save"}
    }
    output(u1.list)
  }
