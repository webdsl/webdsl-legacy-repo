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

  
  define homesidebar() {
    list { listitem{ navigate("Home", home()) } }
  }
  
  define sidebar() {
    "default sidebar"
  }
  
  define sidebar() {
    "default body"
  }
  
  
section menus
  
  define menu() {

  }
