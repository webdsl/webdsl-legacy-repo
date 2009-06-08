module static

section pages
  
    var globaln := Note{name := 'hoi'}
    
  define page home() {
    table {
      row {
         image("/images/gohome.png")
         text("Welcome to your notes!")
      }
      row {
        menubar {
           navigate()[onclick := action { append (folderlist , 
             quicksearch()
             ); }]
             {  output(globaln.name) }
           menuheader { navigate()[onclick :=  action {
             visibility (folderlist , toggle);
           }] {"/" } }
           menuheader { navigate(home()) { "the notes"  } }
        }
        quicksearch()
      }
      row {
        block[id := folderlist] {
          folders()
        }
        block[id := notelist] {
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