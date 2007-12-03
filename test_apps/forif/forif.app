application com.example.forif

description {
  This is an automatically generated description
}

imports templates

section data

entity SomeEntity {
  title :: String
  friends -> Set<SomeEntity>
  members -> Set<User>
  previous -> SomeEntity
  lead -> User
}

entity User {

}

globals {
  var u : User := User {  };
  var one : SomeEntity := SomeEntity { title := "One" members := {u} lead := u };
  var two : SomeEntity := SomeEntity { title := "Two" friends := {one} previous := one };
  var three : SomeEntity := SomeEntity { title := "Three" friends := {one, two} previous := two };

}

section pages

define bladie(se : SomeEntity) {
  if(se.previous != null) {
    navigate(home()){"&lt;-"}
  }
}

define page home() {
  init {
    for(e: SomeEntity in three.friendsList where e.title = "One") {
       // Bla
    }
    var lst : Set<Int> := {1, 2, 3, 4, 5};
    var sqrlist : Set<Int> := [x*x for(x : Int in lst)];
  }
  main()
  define body() {
    list {
      for(ae : SomeEntity where ae.title = "Three") {
        listitem {
          output(ae)
          for(f: SomeEntity in three.friendsList where f.title = "One") {
            output(f)
          }
        }
      }
    }
    section {
      header{"Another list"}
      list {
        for(ae : SomeEntity where "One" in [x.title for(x : SomeEntity in ae.friends)]) {
          listitem {
            output(ae)
          }
        }
      }
    }
   section{
          header{"Members"}
          list{ for(u : User in one.membersList) {
            listitem{ 
              output(u)
              if(u = one.lead) {
                "(lead)"
              }
            }
          } }
   } }
}

define page something(e : SomeEntity) {
  main()
  define body() {
    bladie(e)
  }
}

