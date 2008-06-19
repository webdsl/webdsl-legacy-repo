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

  globals {
      var item : NewsItem := NewsItem {
        name := "Hot new feature!"
        text := "WebDSL now contains a hot new feature: deriving pages and rows."
        comment := "Added as initial data"
      };
  }
  
  define main() {
    block("top") {
      top() }
    block("body") {
      block("left_innerbody") {
        sidebar() }
      block("main_innerbody") {
        body() } }
  }
  
  define top() {
    block("header") { }
  }
  
  define sidebar() { }

  define body() { }


  define page home() {
    main()
    define body() {
      section() {
        header(){"This is a testapp for derive functions"}
      }
      section() {
        table {
          row { "derive editpage from i for (text, comment): " navigate(editNewsItem(item)){"go"} }
          row { "derive editpage from i: " navigate(editNewsItemAll(item)){"go"} }
          row { "derive editRows from i: " navigate(editNewsRows(item)){"go"} }
          row { "derive viewPage from i: " navigate(viewCustomPage(item)){"go"} }
          row { "derive viewPage from i for (text): " navigate(viewCustomPageSmall(item)){"go"} }
          row { "derive viewRows from i: " navigate(viewCustom(item)){"go"} }
          row { "derive viewRows from i for (name): " navigate(viewCustomSmall(item)){"go"} }
        }
      }
    }
  }

section test pages

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
        text(i.name){
        }
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
  
  // test voor viewPage
  define page viewCustomPage (i : NewsItem) {
    derive viewPage from i
  }
  // test voor viewPage met paar velden
  define page viewCustomPageSmall (i : NewsItem) {
    derive viewPage from i for (text)
  }
  
  // test voor viewRows
  define page viewCustom (i : NewsItem) {
    section(){
      header(){
        "View "
        text(i.name){
        }
      }
      form(){
        table(){
          derive viewRows from i
        }
      }
    }
  }  
  // test voor viewRows met paar velden
  define page viewCustomSmall (i : NewsItem) {
    section(){
      header(){
        "View "
        text(i.name){
        }
      }
      form(){
        table(){
          derive viewRows from i for (name)
        }
      }
    }
  }