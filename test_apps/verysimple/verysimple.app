application com.example.verysimple

description {
  This is an automatically generated description
}

imports templates

section pages

section data model

entity User {
  username :: String
  password :: Secret
  message :: String := "Hello " + this.username
}

entity Page {
  name :: String
  text :: WikiText

  function perform() : String {
     return "Page performed"; 
  }
}

entity SpecialPage: Page {
  extraField :: String
  function perform() : String {
     return "SpecialPage performed"; 
  }
}

entity SuperSpecialPage : Page {
  anotherField :: String

  function perform() : String {
     return this.anotherField + "SuperSpecialPage performed"; 
  }
}

var ssp : SuperSpecialPage := SuperSpecialPage {};
var p : Page := Page {};

function doSomething(i : Int) : Int {
  return 1 + 2;
}

function doSomething(s : String) : Int {
  return 3 + 4;
}

define page home() {
  ""
  /*
  main()
  define body() {
    //output(doSomething(3))
    //output(p.perform())
    //output(ssp.perform())
    list {
      for(u : User) {
        listitem { text(u.message) }
      }
    }
  }
  */
}

define page newUser() {
  ""
  /*
  main()
  define body() {
    var u : User := User{};
    form {

      input(u.username)
      input(u.password)
      action("Create", save(u))

      action save(usr : User) {
        u.persist();
        return home();
      }
    }
  }
  */
}
