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
      for(m : Message where m.original = true order by m.date desc limit 15) {
        displayMessage(m)
      }
    }
	}
}


