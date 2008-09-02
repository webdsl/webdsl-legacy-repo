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

section pages

define page home() {
  title { "Dieslr" }
	main()
	define body() {
    header { "Latest updates" }
    for(u : Update order by u.date desc limit 10) {
      viewUpdate(u)
    }
	}
}


