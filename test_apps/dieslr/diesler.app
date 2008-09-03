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
    header { "Latest updates" }
    for(m : Message where m.recipient = null order by m.date desc limit 10) {
      displayMessage(m)
    }
	}
}


