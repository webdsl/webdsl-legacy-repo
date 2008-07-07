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

entity SpecialPage: Page {
  extraField :: String
}

entity SuperSpecialPage : Page {
  anotherField :: String
}

entity Page {
  name :: String
  text :: WikiText
}

function doSomething(i : Int) : Int {
  return 1 + 2;
}

function doSomething(s : String) : Int {
  return 3 + 4;
}

define page home() {
  main()
  define template body() {
    //output(doSomething(3))
    list {
      for(u : User) {
        listitem { text(u.message) }
      }
    }
  }
}

define page user(u : User) {
  derive editPage from u for (username, password)
}

define page newUser() {
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
}
