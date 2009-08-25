module popup

section template


define popup(header : String) {
  container[class:=popupbg, style := "position: absolute; top: 0px; left:0px; background-image: url(images/popup_bg.png);"] {
    container[id:=popupcontents, class:=popupcontents] {
      container[class:=popupheader] {
        table {
          row { 
            column[width:= 600px]{	output(header) } 
            column { actionLink("X")[onclick:=  action{ visibility(this,hide); }, class:= right] }
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

style popupStyle

//required to get the css included...
popup(header: String) { }


.popupbg {
  height	:= 100%;
  width		:= 100%;
//  image		:= url("./images/popup_bg.png");
  image-repeat:= Repeat.both;
}

.popupcontents {
  display := Display.block;
  align				 := Align.center;
  background-color := #cccccc;
  border-style := BorderStyle.solid;
  border-width := 1px;
  border-color := Color.navy;
  padding			 := 2px;
  width				 := 640px;
  margin-top	 := 50px;
}

.popupheader {
  background-color := #e2eaff;	
  border-style := BorderStyle.solid;
  border-width := 1px;
  font-color := Color.White;
  font-style := FontStyle.bold;
  display:= Display.block;
}

.right {
  text-align := Align.right;
}
