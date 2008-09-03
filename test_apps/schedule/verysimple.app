application com.example.verysimple

description {
  This is an automatically generated description
}

section data model

  entity Message {
    text :: String
  }

section whatever

  function createMessages(msg : String) {
    var l : List<Int> := [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    for(i : Int in l) {
      var m : Message := Message{};
      m.text := msg;
      m.save();
    }
  }


  define page home() {
    form {
      action("Schedule it!", schedule())

      action schedule() {
        schedule createMessages("I was scheduled!");
      }
    }
  }
