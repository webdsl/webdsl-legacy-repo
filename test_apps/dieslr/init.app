module init

section global data

    var user1 : User :=
      User {
        username := "zef"
        password := "secret"
        //following := { user2 }
      };
    
    var user2 : User :=
      User {
        username := "eelco"
        password := "secret"
      };

    var message1 : Message :=
      Message {
        sender := user1
        recipient := user1
        text := "This is my first message"
        original := true
      };

    var message2 : Message :=
      Message {
        sender := user1
        recipient := user1
        replyTo := message1
        text := "This is a reply to that first message."
        original := true
      };

