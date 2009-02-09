module init
  
  init{
    var p_a := Person {
      fullName := "Alice"
      displayName := "Alice"
    };

    var p_b := Person {
      fullName := "Bob"
      displayName := "Bob"
    };
    
    var p_c := Person {
      fullName := "Charlie"
      displayName := "Charlie"
    };
    
    var p_d := Person {
      fullName := "Dave"
      displayName := "Dave"
    };
    
    var u_a : User := User{
      username := "alice"  
      person := p_a
    };
    
    var u_b : User := User{
      username := "bob"
      person := p_b  
    };
    
    var u_c : User := User{
      username := "charlie"
      person := p_c  
    };
    
    var u_d : User := User{
      username := "dave"  
      person := p_d
    };
    
    p_a.save();
    p_b.save();
    p_c.save();
    p_d.save();
    
    u_a.save();
    u_b.save();
    u_c.save();
    u_d.save();
    
    var g_a := UserGroup {
      name := "Example Group"
      owner := u_a
      memberLimit := 3
      moderators := {u_a, u_b}
      members := {u_a,u_b,u_c}
    };
    
    g_a.save();
  }
