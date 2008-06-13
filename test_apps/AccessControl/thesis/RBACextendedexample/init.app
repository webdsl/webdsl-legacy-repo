module init

section RBAC init

  globals
  {
    var u0 : User := User
    {
      name := "user0"
      roles := {retrieveProjectRole(p0,viewer)}
    };
    var u1 : User := User
    {
      name := "user1"
      roles := {retrieveProjectRole(p0,editor)}
    };
    var u2 : User := User
    {
      name := "user2"
      roles := {retrieveProjectRole(p1,viewer),retrieveProjectRole(p1,editor)}
    };
    var u3 : User := User
    {
      name := "user3"
      roles := {retrieveProjectRole(p2,admin)}
    };

    var d0 : Document := Document
    {
      title := "d0"
      text := "d0 text"
    };
    var d1 : Document := Document
    {
      title := "d1"
      text := "d1 text"
    };
    var d2 : Document := Document
    {
      title := "d2"
      text := "d2 text"
    };
    var d3 : Document := Document
    {
      title := "d3"
      text := "d3 text"
    };
    
    
    var p0 : Project := Project
    {
      name := "p0"
      documents := {d0,d1}
    };
    var p1 : Project := Project
    {
      name := "p1"
      documents := {d2}
    };
    var p2 : Project := Project
    {
      name := "p2"
      documents := {d3}
    };
  }