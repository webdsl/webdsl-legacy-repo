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
}

section pages

define page manytomany() {
  main()
  define body() {
    div("div1") {
      header() { "Books" }
      list {
        for(p : Book) {
          listitem { navigate(editBook(p)) { output(p.title) } }
        }
      }
    }
    
    div("div2") {
      header() { "Authors" }
      list {
        for(p : Author) {
          listitem { navigate(editAuthor(p)) { output(p.name) } }
        }
      }
    }
  }
}
