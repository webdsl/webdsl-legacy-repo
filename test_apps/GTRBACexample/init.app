module init

section RBAC init

  globals
  {
    var u0 : User := User
    {
      name := "user0"
      roles := {admin}
    };
    var u1 : User := User
    {
      name := "user1"
      roles := {editor}
    };
    var u2 : User := User
    {
      name := "user2"
      roles := {editor,viewer}
    };
    var u3 : User := User
    {
      name := "user3"
      roles := {viewer}
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

  }