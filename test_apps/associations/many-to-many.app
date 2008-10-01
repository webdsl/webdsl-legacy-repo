module many-to-many

description {
  This is an automatically generated description
}

section data

entity Author {
  name :: String (name)
  books -> Set<Book>
}

entity Book {
   title :: String (name)
   authors -> Set<Author> (inverse=Author.books)
}

entity Uauthor {
  name :: String (name)
}

entity Ubook {
   title :: String (name)
   authors -> Set<Uauthor>
}

define page editAuthor(a:Author){
  derive editPage from a
}
define page editBook(a:Book){
  derive editPage from a
}
define page editUauthor(a:Uauthor){
  derive editPage from a
}
define page editUbook(a:Ubook){
  derive editPage from a
}
define page author(a:Author){
  derive viewPage from a
}
define page book(a:Book){
  derive viewPage from a
}
define page uauthor(a:Uauthor){
  derive viewPage from a
}
globals {
  var zef : Author := Author {
    name := "Zef"
  };

  var danny : Author := Author {
    name := "Danny"
  };

  var jippe : Author := Author {
    name := "Jippe"
  };

  var book1 : Book := Book {
    title := "Street language part I"
    authors := {zef, danny}
  };
  
  var book2 : Book := Book {
    title := "Advanced street Slang"
    authors := {zef, danny, jippe}
  };
  
  var a1 : Uauthor := Uauthor {
    name := "Zef"
  };

  var a2 : Uauthor := Uauthor {
    name := "Danny"
  };
  
  var b1 : Ubook := Ubook {
    title := "Street language part I"
    authors := {a1, a2}
  };
  
  var b2 : Ubook := Ubook {
    title := "Advanced street Slang"
    authors := {a1}
  };
}

section pages

define page manytomany() {
  action test(){
    book1.authors.add(zef);
  }
  
  main()
  
  define body() {
    div("div1") {
      header() { "Books" }
      list {
        for(p : Book) {
          listitem { navigate(editBook(p)) { output(p.title) output(p.authors) } }
        }
      }
    }
    
    div("div2") {
      header() { "Authors" }
      list {
        for(p : Author) {
          listitem { navigate(editAuthor(p)) { output(p.name) output(p.books)} }
        }
      }
    }
  }
}

define page umanytomany() {
  main()
  define body() {
    div("div1") {
      header() { "Books" }
      list {
        for(p : Ubook) {
          listitem { navigate(editUbook(p)) { output(p.title) output(p.authors)} }
        }
      }
    }
    
    div("div2") {
      header() { "Authors" }
      list {
        for(p : Uauthor) {
          listitem { navigate(editUauthor(p)) { output(p.name) } }
        }
      }
    }
  }
}