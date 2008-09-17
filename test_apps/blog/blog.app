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

define feed posts() {
  title{ "Zef.me blog" }
  link { url(home()) }
  for(p : Post order by p.date desc limit 10) {
    entry {
      title { output(p.title) }
      updated(p.date)
      id { url(post(p)) }
      link { url(post(p)) }
      author { output(p.author.name) }
      content {
        output(p.text)
      }
    }
  }
}


