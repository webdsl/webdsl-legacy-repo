module logic

section logic

  function broadcastMessage(sender : User, msg : String) {
    // Add message to inbox of each follower
    for(user : User in sender.followers) {
      var m : Message := Message{};
      m.sender := sender;
      m.recipient := user;
      m.text := msg;
      m.save();
    }
  }
