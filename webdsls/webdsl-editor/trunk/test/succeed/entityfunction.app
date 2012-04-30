application test
  
  entity SubUser:User{}
  entity Publication{}
  entity User{
    name :: String
    function test(a:Int, b:Int, u:User) : Bool
    {
      u.name == this.name;
      return a<b;
    }
    function test(a:Int, b:Int, u:SubUser) : Bool
    {
      u.name == this.name;
      return a>b;
    }
    function test2(a:Int, b:Int, u:User) : Bool
    {
      this.test(a,b,u);
      return test(a,b,u);
    }
    function test2()
    {
      test(45,65,User{});
    }
    
            
    function add(pubs : List<Publication>) {
      for(pub : Publication in pubs) {
        add(pub);
      }
    }
    function add(pub : Publication) {

    }
  }

  define page root(){}
  
  test one {
    var u := User{name := "testuser"};
    var su := SubUser{name := "testuser"};
    assert(u.test(4,8,u) == true);
    assert(u.test(4,8,su)== false);
    assert(test(4,8,u) == true);
    assert(test(4,8,su)== false);
  }

  
  
    function test(a:Int, b:Int, u:User) : Bool
    {
      
      return a<b;
    }
    function test(a:Int, b:Int, u:SubUser) : Bool
    {
      return a>b;
    }