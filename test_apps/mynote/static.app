module static

section pages
  
   var globaln := Note{name := 'hoi'}
    
define page root() {
  <script>loadCSS("~(baseURL())"+"/stylesheets/mytodo.css"); </script>
  block[class := mytodo] {
    table {
      row[class := headerrow]{
         navigate(root()) { image("/images/gohome.png") }
         header[id := testHeading]{"MyToDo"}
      }
      row[class := graybg] {
        navigate()[onclick :=  action {visibility (folderlist , toggle); }] { image("/images/toggle.png") } 
        quicksearch()
      }
      row[class := middlerow] {
        placeholder folderlist {
          folders()
        }
        placeholder notelist {
          "(please select a folder)"
        }
      }
      row[class := footerrow] {
        text("MyNote :: a WebDSLx demonstration")
        navigate(url("http://www.webdsl.org")) { "About WebDSL(x)" }
      }
    }
  }
}