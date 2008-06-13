module init

section wiki init

  globals
  {
    var u0 : User := User
    {
      name := "user0"
    };
    var u1 : User := User
    {
      name := "user1"
    };
    var u2 : User := User
    {
      name := "user2"
    };
    var u3 : User := User
    {
      name := "user3"
    };

    var firstpage : Page := Page
    {
      name := "firstpage"
      text := "This is the first page.\n\nHere's a link to [[page(secondpage)|the second page]]"
    };
    var secondpage : Page := Page
    {
      name := "secondpage"
      text := "This is the second page\n\nHere's a link to [[page(firstpage)|the first page]]"
    };
  }