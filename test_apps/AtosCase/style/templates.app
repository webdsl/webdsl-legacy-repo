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
      navigate(home()){"AO"}
      block("brandsub"){"Atos Workflow Case"}
    }
  }

  define topRight() { 

  }
  
  define sidebar() {
    header { "Side Menu" }
    block  { navigate(home()) { "Home" } }
  }
  
  define body() {
    "Welcome to researchr"
  }

  define footer() {
    /*block("footer_text") {
      text("AO")
    }
    block("footer_links") {
      list {
  
        listitem { navigate(home()) { "Disclaimer" } }
        
      }
    }*/
  }

