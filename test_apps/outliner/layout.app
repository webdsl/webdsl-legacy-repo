module layout

section templates

define no-span rndButton(kind: String, showCaption: Bool) {
  block[class:="scopediv rounded border rndButton", onclick:= attribute("onclick","")] {
    var url:= "../images/"+kind.toLowerCase()+".png";
    if (showCaption == true) { 
       break
       output(caption)
    }
  }
}