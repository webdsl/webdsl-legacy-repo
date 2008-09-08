application com.example.diesler

description {
	This is an automatically generated description
}

imports ac
imports templates
imports datamodel
imports init
imports pages
imports style
imports layout
imports logic

section pages

function recentMessages(pg : Int) : List<Message> {
  var lst : List<Message> := List<Message>();
  for(m : Message where m.original = true order by m.date desc limit 2 offset pg*2) {
    lst.add(m);
  }
  return lst;
}

define page home() {
  title { "Dieslr" }
	main()
	define body() {
    if(securityContext.loggedIn) {
      header { "Latest updates from your friends" }
      for(m : Message in securityContext.principal.receivedMessages order by m.date desc per 15) {
        displayMessage(m)
      }
    } else {
      header { "Latest updates" }
      var pg : Int := 0;
      var messages : List<Message> := recentMessages(pg);
      for(m : Message in messages) {
        displayMessage(m)
      }
      if(messages.length = 2) {
        form {
          inputHidden(pg)
          if(pg > 0) {
            action("previous", previous())
          }
          action("next", next())

          action previous() {
            pg := pg - 1;
          }
          action next() {
            pg := pg + 1;
          }
        }
      }
    }
	}
}


