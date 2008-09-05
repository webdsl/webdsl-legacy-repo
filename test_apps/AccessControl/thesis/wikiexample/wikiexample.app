application wikiexample

description {
  wiki with simple access control policy
}

imports templates
imports accesscontrol
imports init

section data model

entity Page {
  name :: String (name, id)
  text :: WikiText
}

entity User {
  name :: String(name)
}

section pages

define page editPage(p:Page){
  derive editPage from p
}

define page createPage(){
  var p:Page := Page{}
  derive createPage from p
}

define page home() {
  main()
  define body() {
    section {
       header {"All pages"}
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
