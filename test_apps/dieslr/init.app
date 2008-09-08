module init

section global data

  var user1 : User :=
    User {
      username := "zef"
      password := "secret"
      isAdmin := true
    };
  
  var user2 : User :=
    User {
      username := "eelco"
      password := "secret"
      following := { user1 }
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

section pages

  var aboutPage : Page := 
    Page {
      name := "About"
      text := "Dieslr is awesome and was written by Zef Hemel."
    };

