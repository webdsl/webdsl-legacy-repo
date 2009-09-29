module popup

section template

define popup(header : String) {
  container[class:=popupbg, style := "position: absolute; top: 0px; left:0px; background-image: url("+baseURL()+"/images/popup_bg.png);"] {
    container[id:=popupcontents, class:=popupcontents] {
      container[class:=popupheader] {
        table {
          row { 
            column[width:= 600px]{	output(header) } 
            column { image("/images/close.png")[onclick:=  action{ visibility(this,hide); }, class:= right] }
          }
        }
      }
      container{ elements() }
    }
  }
}

define popupBody() {
  "<no contents to display>"	
}
