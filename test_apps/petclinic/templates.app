module templates

section main template.

  define main() {
    block("top") {
      top()
    }

    block("body") {
      block("left_innerbody") {
        sidebar()
      }
  
      block("main_innerbody") {
        form() {
          body()
        }
      }
    }

    block("footer") {
      footer()
    }
  }

section basic page elements.

  define top() {
    block("header") {}
    block("menubar") { 
      topmenu()
    }
  }
  
  define sidebar() {
    sidemenu()
  }

  define footer() {
    block("footer") {
      block("left_footer") {
        navigate(url("http://www.webdsl.org")) { "About WebDSL" }
      }
      block("right_footer") {
        text("PetClinic :: a WebDSL demonstration")
      }
    }
  }

  define topmenu() {
  
    menubar {
      menu {
        menuheader { navigate(home()) { "Home" } }
      }
      menu {
        menuheader { navigate(vets()) { "Veterinarians" } }
        menuitem { navigate(vets()) { "Display veterinarians" } }
        menuitem { navigate(createVet()) { "Create veterinarian" } }
      }
      menu {
        menuheader { navigate(allOwner()) { "Owners" } }
        menuitem { navigate(findOwner()) { "Find owner" } }
        menuitem { navigate(createOwner()) { "Create owner" } }
        menuspacer
        for (o : Owner) {
          menuitem { output(o) }
        }
      }
      menu {
        menuheader { navigate(allPet()) { "Pet" } }
        menuitem { navigate(createPet()) { "Create Pet" } }
        menuspacer
        for (p : Pet) {
          menuitem { output(p) }
        }
      }
      menu {
        if (false) {
          menuheader { navigate(allVisit()) { "Visit" } }
        }
        if (true) {
          menuitem { navigate(createVisit()) { "New Visit" } }
        }
      }
    }
  }
  
  define sidemenu() {
    
    menubar("vertical") {
      menu {
        menuheader { navigate(home()) { "Home" } }
      }
      menu {
        menuheader { navigate(vets()) { "Veterinarians" } }
        menuitem { navigate(vets()) { "Display veterinarians" } }
        menuitem { navigate(createVet()) { "Create veterinarian" } }
      }
      menu {
        menuheader { navigate(allOwner()) { "Owners" } }
        menuitem { navigate(findOwner()) { "Find owner" } }
        menuitem { navigate(createOwner()) { "Create owner" } }
        menuspacer
        for (o : Owner) {
          menuitem { output(o) }
        }
      }
      menu {
        menuheader { navigate(allPet()) { "Pet" } }
        menuitem { navigate(createPet()) { "Create Pet" } }
        menuspacer
        for (p : Pet) {
          menuitem { output(p) }
        }
      }
      menu {
        menuheader { navigate(allVisit()) { "Visit" } }
        menuitem { navigate(createVisit()) { "New Visit" } }
      }
    }
  }
