module init

section MAC init

  globals
  {
    var u0 : User := User
    {
      name := "Alice"
      clearance := 0
    };
    var u1 : User := User
    {
      name := "Bob"
      clearance := 1
    };
    var charlie : User := User
    {
      name := "Charlie"
      clearance := 2
    };
    var u3 : User := User
    {
      name := "Dave"
      clearance := 3
    };

    var d0 : Document := Document
    {
      title := "d0"
      text := "d0 text"
      classification := 0
    };
    var d1 : Document := Document
    {
      title := "d1"
      text := "d1 text"
      classification := 1
    };
    var d2 : Document := Document
    {
      title := "d2"
      text := "d2 text"
      classification := 2
    };
    var d3 : Document := Document
    {
      title := "d3"
      text := "d3 text"
      classification := 3
    };

    var mis0 : Mission := Mission
    {
      title := "mission classification 0"
      text := "mission 0 text"
      classification := 0
    };
    var mis1 : Mission := Mission
    {
      title := "mission classification 1"
      text := "mission 1 text"
      classification := 1
    };
    var mis2 : Mission := Mission
    {
      title := "mission classification 2"
      text := "mission 2 text"
      classification := 2
    };
    var mis3 : Mission := Mission
    {
      title := "mission classification 3"
      text := "mission 3 text"
      classification := 3
    };
  }