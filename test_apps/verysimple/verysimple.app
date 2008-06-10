application com.example.verysimple

description {
  This is an automatically generated description
}

imports templates

section pages

section data model

entity User {
  username :: String (name)
  password :: Secret
  message :: String := "Hello " + username
}

define page home() {
  main()
  define body() {
    list {
      for(u : User) {
        listitem { text(u.message) }
      }
    }
  }
}

define page newUser() {
  main()
  define body() {
    var u : User := User{};

    input(u.username)
    input(u.password)
    action("Create", save())

    action save() {

    }
  }
}
