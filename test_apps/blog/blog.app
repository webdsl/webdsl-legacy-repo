application com.zefhemel.blog

description {
	A simple WebDSL blogging application.
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
  title { "Blog" }
	main()
	define body() {
    for(p : Post order by p.date desc limit 10) {
      displayPost(p)
    }
	}
}


