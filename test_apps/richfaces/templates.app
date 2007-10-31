module app/templates

section main template

  define main() {
    div("outersidebar") {
      sidebar()
    }
    div("outerbody") {
      div("menubar") {
        menu()
      }
      body()
    }
  }

section basic page elements
  
  define sidebar() {
    list { listitem{ navigate("Home", home()) } }
  }
  
  define body(){
    "default body"
  }
  
section menus
  
  define menu() {

  }
