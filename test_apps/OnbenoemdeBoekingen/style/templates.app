module templates

section main template.

  define main() {
    block("mainContent"){
      top()
      topmenu()
      body()
      footer()
    }
  }
  


  define top() {
    topRight()
    block("brand") { 
      navigate(home()){"Amersfoortse"}
      block("brandsub"){"Onbenoemde boekingen"}
    }
  }

  define topRight() { 

  }
  
  define sidebar() {
    header { "Side Menu" }
    block  { navigate(home()) { "Home" } }
  }
  
  define body() {
    "Welkom bij de onbenoemde boekingen application"
  }

  define footer() {
    block("footer_text") {
      text("AO")
    }
    block("footer_links") {
      list {
  
        listitem { navigate(home()) { "Disclaimer" } }
        
      }
    }
  }

