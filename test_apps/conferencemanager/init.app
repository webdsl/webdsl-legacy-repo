module init

section RBAC init

  globals
  {
    var u0 : User := User
    {
      name := "user0"
      organization := o0
    };
    var u1 : User := User
    {
      name := "user1"
      organization := o0
    };
    var u2 : User := User
    {
      name := "user2"
      organization := o1
    };
    var u3 : User := User
    {
      name := "user3"
      organization := o2
    };
    var o0 : Organization := Organization
    {
      name := "organization1"
    };
    var o1 : Organization := Organization
    {
      name := "organization2"
    };
    var o2 : Organization := Organization
    {
      name := "organization3"
    };
  }