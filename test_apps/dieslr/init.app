module init

section global data

    var user1 : User :=
      User {
        username := "zef"
        password := "secret"
      };
    
    var user2 : User :=
      User {
        username := "eelco"
        password := "secret"
      };

/*
    var message1 : Message :=
      Message {
        user := user1
        text := "This is my first message"
      };

    var message2 : Message :=
      Message {
        user := user1
        replyTo := message2
        text := "This is a reply to that first message."
      };

*/
