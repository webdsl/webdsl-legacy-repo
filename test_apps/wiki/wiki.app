application com.example.wiki

description {
  This is an automatically generated description
}

imports templates

section data model

entity Page {
  name :: String (name, id)
  text :: WikiText
}

section global variables

globals {
   var mainPage : Page := Page {
      name := "HomePage"
      text := "This is text for the main page.\n\nHere's a link to [[page(AnotherPage)|another page]]"
   };
}

section pages

define page home() {
  main()
  define body() {
    section {
       header {"All page"}
       list {
         for(p : Page) {
           listitem { output(p) }
         }
       }
    }
  }
}

define page page(p : Page) {
  init {
    if(p.text = "") {
      goto editPage(p);
    }
  }
  main()
  define body() {
    section {
       header {output(p.name)}
       output(p.text)
       section { navigate(editPage(p)) { "Edit this page" } }
    }
  }
}
