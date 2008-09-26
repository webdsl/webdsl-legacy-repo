module static


section pages
  
  define page home() {
    table {
      row {
         image("/images/gohome.png")
         text("Welcome to your notes!")
      }
      row {
        menubar {
            menuheader { navigate()[onclick := @visibility folderlist << "toggle"] {"/" } }
            menuheader { navigate(home()) { "the notes"  } }
        }
        quicksearch()
      }
      row {
        block[id := "folderlist"] {
          folders()
        }
        block[id := "notelist"] {
          "please select a folder"
        }
      }
      row {
        text("MyNote :: a WebDSL AJAX demonstration")
        navigate(url("mailto:mweststrate@gmail.com")){ "by Michel Weststrate" }
        navigate(url("http://www.webdsl.org")) { "About WebDSL" }
      }
    }
  }

section templates
  
  
 