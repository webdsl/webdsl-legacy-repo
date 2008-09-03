application com.example.verysimple

description {
  This is an automatically generated description
}

section data model

  entity Message {
    text :: String
    replyTo -> Message
  }

  var rootMessage : Message := Message { text := "I am root!" };

section whatever

  function createMessages(replyTo : Message, msg : String) {
    var l : List<Int> := [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    for(i : Int in l) {
      var m : Message := Message{};
      m.replyTo := replyTo;
      m.text := msg;
      m.save();
    }
  }


  define page home() {
    form {
      action("Schedule it!", schedule())

      action schedule() {
        schedule createMessages(rootMessage, "I was scheduled!");
      }
    }
  }
