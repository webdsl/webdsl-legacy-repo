module one-to-one

description {
  This is an automatically generated description
}

imports templates

section data

entity Man {
  name :: String (name)
  wife -> Woman //old (inverse=Woman.husband)
}

entity Woman {
   name :: String (name)
   husband -> Man (inverse=Man.wife)
}

define page editMan(a:Man){
  derive editPage from a
}
define page editWoman(a:Woman){
  derive editPage from a
}
define page man(a:Man){
  derive viewPage from a
}
define page woman(a:Woman){
  derive viewPage from a
}


globals {

  var woman1 : Woman := Woman {
    name := "Jantien"
//    husband := man1
  };
  
  var woman2 : Woman := Woman {
    name := "Anita"
//    husband := man2
  };
  
  var man1 : Man := Man {
    name := "Jan"
//    wife := woman1
  };
  
  var man2 : Man := Man {
    name := "Sjonnie"
//    wife := woman2
  };
  
}
section pages

define page onetoone() {
  main()
  define body() {
    div("div1") {
      header() { "Men" }
      list {
        for(p : Man) {
          listitem { navigate(editMan(p)) { output(p.name) output(p.wife) } }
        }
      }
    }
    
    div("div2") {
      header() { "Women" }
      list {
        for(p : Woman) {
          listitem { navigate(editWoman(p)) { output(p.name) output(p.husband) } }
        }
      }
    }
  }
}
