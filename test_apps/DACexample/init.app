module init

section MAC init

  globals
  {
    var u0 : User := User
    {
      name := "Alice"
    };
    var u1 : User := User
    {
      name := "Bob"
    };
    var u2 : User := User
    {
      name := "Charlie"
    };
    var u3 : User := User
    {
      name := "Dave"
    };

    var d0 : Document := Document
    {
      title := "d0"
      text := "d0 text"
      owner := u0
    };
    var d1 : Document := Document
    {
      title := "d1"
      text := "d1 text"
      owner := u1
    };
    var d2 : Document := Document
    {
      title := "d2"
      text := "d2 text"
      owner := u2
    };
    var d3 : Document := Document
    {
      title := "d3"
      text := "d3 text"
      owner := u3
    };

  }