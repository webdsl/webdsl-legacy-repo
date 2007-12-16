application NestedForExpressions

description {
  Test Nested For Expressions
}

imports for0
imports for1
imports for2
imports for3
imports for4
imports forinif

section testing
 
  entity Document
  {
    title :: String
  }
  entity DocumentCabinet
  {
    documents -> List<Document>
  }
  
  
  globals
  {
    var d0: Document := Document{title := "doc 0"};
    var d1: Document := Document{title := "doc 1"};
    var d2: Document := Document{title := "doc 2"};
    var docs: DocumentCabinet := DocumentCabinet{documents := [d0,d1,d2]};
  }
 
  define page home()
  {

  }