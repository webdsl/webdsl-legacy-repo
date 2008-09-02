module init

section global data

    var user1 : User :=
      User {
        username := "zef"
        password := "secret"
      };

    var update1 : Update :=
      Update {
        user := user1
        text := "This is my first message"
      };

    var update2 : Update :=
      Update {
        user := user1
        replyTo := update1
        text := "This is a reply to that first message."
      };

