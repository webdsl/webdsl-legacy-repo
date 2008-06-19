application com.example.derive

description {
  This is an automatically generated description
}

//imports templates

section data model

  entity NewsItem {
    name :: String
    text :: Text
    comment :: Text
  }

section pages

  define page home() {
    var item : NewsItem := NewsItem {
      name := "Hot new feature!"
      text := "WebDSL now contains a hot new feature: deriving pages and rows."
      comment := "Added as initial data"
    };
//    item.persist();
    section("TestApp for derive functionality") {
        table {
          row { "derive editpage from i for (text, comment): " navigate(editNewsItem(item)){"go"} }
          row { "derive editpage from i: " navigate(editNewsItemAll(item)){"go"} }
          row { "derive editRows from i: " navigate(editNewsRows(item)){"go"} }
        }
    }
  }

section test pages

  define page newsItem(i: NewsItem) {
    derive viewPage from i
  }

  define page editNewsItem(i: NewsItem) {
    derive editPage from i for (text, comment)
  }

  define page editNewsItemAll(i: NewsItem) {
    derive editPage from i
  }

  define page editNewsRows (i : NewsItem) {
    section(){
      header(){
        "Edit "
        text(i.name){}
      }
      form(){
        table(){
          derive editRows from i
        }
        action("Save", save()){
        }
      }
      action save ( )
      {
        i.save();
        return newsItem(i);
      }
    }
  }