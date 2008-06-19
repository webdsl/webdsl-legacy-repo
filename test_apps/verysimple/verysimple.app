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
  message :: String := "Hello " + this.username
  messages -> Set<Message> (inverse=Message.author)
}

enum ApproveEnum { approved("Approved!"), rejected("Rejected!") }

entity Message {
  author -> User
  message :: Text
  status -> ApproveEnum
}

extend entity User {
  prop :: Int
}

globals {
  function test(z : Int) {
    var a : Set<Int>;
    var q : Int := 3;
    a := [ x * q | x : Int in {1, 2, 3, 4}]; 
    var q2 : Bool := And[x > 3 | x : Int in a];
  }
}

globals {
  extend function test(z : Int) {
    var b : Bool := z = 3;
    var m : Message := Message {};
    m.status := approved;
  }
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
    action("Create", save(u))

    action save(usr : User) {

    }
  }
}
