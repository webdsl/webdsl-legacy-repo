module templates

section main template.

  define main() {
    top()

    div("body") {
      form() {
        body()
      }
    }

    footer()
  }

section basic page elements.

  define top() {
    div("header") {}
    div("menubar") { 
      menutemp()
    }
  }

  define footer() {
    div("footer") {
      div("left_footer") {
        navigate(home()) { "Home" }
      }
      div("right_footer") {
        text("PetClinic :: a WebDSL demonstration")
      }
    }
  }

  define menutemp() {
  
    menubar {
      menu {
        menuheader{ navigate(home()) { "Home" } }
      }
      menu {
        menuheader{ navigate(vets()) { "Veterinarians" } }
        menuitem { navigate(vets()) { "Display veterinarians" } }
        menuitem { navigate(createVet()) { "Create veterinarian" } }
      }
    }

  }
  
//    list {
//      listitem { navigate(home()) { "Home" } }
//      listitem { navigate(vets()) { "Veterinarians" }
//        list {
//          listitem { navigate(vets()) { "Display veterinarians" } }
//          listitem { navigate(createVet()) { "Create veterinarian" } }
//        }
//      }
//    }
