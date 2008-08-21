module templates

section main template.

  define main() {
    top()
    topmenubar()
    sidebar()
    mainbody()
    footer()
  }

section basic page elements.

  define top() {
    block("logos") {
      image("images/webdsl_logo_small.png")
      image("images/webdsl_logo_text.png")
    }
    block("text") {
	  text("default header")
	}
  }
  
  define topmenubar() {
    topmenu()
  }
  
  define sidebar() {
    header { "Side Menu" }
    
    block { navigate(findOwner()) { "Find owner" } }
    block { navigate(vets()) { "Display veterinarians" } }
  }
  
  define mainbody() {
    "Welcome to the Pet Clinic"
  }

  define footer() {
    block("footer_links") {
      list {
        listitem { navigate(home()) { "About" } }
        listitem { navigate(home()) { "Privacy" } }
        listitem { navigate(home()) { "Disclaimer" } }
        listitem { navigate(url("http://www.webdsl.org")) { "About WebDSL" } }
      }
    }
    
    block("footer_text") {
      text("PetClinic :: a WebDSL demonstration")
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
        menuheader { navigate(allVisit()) { "Visit" } }
        menuitem { navigate(createVisit()) { "New Visit" } }
      }
    }
  }
