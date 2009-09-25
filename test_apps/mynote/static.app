module static

section pages
  
define page root() {
  <script>loadCSS("~(baseURL())"+"/stylesheets/mytodo.css"); </script>
  block[class := mytodo] {
    table {
      row[class := headerrow]{
         navigate(root()) { image("/images/gohome.png") }
         header[id := testHeading]{"MyToDo"}
      }
      row[class := graybg] {
        image("/images/toggle.png")
          [onclick :=  action {visibility (folderlist , toggle); }] 
        quicksearch()
      }
      row[class := middlerow] {
        placeholder folderlist {
          folders()
        }
        placeholder todolist {
          "(please select a folder)"
        }
      }
      row[class := footerrow] {
        text("MyToDo :: a WebDSLx demonstration")
        navigate(url("http://www.webdsl.org")) { "About WebDSL(x)" }
      }
    }
  }
}