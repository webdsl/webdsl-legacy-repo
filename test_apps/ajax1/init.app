module init

section MAC init

  globals
  {
    var alice : User := User
    {
      name := "Alice"
      password := "changeme"
    };
    var bob : User := User
    {
      name := "Bob"
      password := "changeme"
    };
    var charlie : User := User
    {
      name := "Charlie"
      password := "changeme"
    };
    var dave : User := User
    {
      name := "Dave"
      password := "changeme"
    };

    var d0 : Document := Document
    {
      title := "First Document"
      text := "This is the first text."
      author := alice
    };
    var d1 : Document := Document
    {
      title := "Second Document"
      text := "This is the second text."
      author := bob
    };
    var d2 : Document := Document
    {
      title := "Third Document"
      text := "This is the third text."
      author := charlie
    };
    var d3 : Document := Document
    {
      title := "Fourth Document"
      text := "This is the fourth text."
      author := dave
    };

  }