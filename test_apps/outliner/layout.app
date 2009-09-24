module layout

section templates

define no-span rndButton(kind: String, showCaption: Bool) {
  container[
    class:="scopediv border rndButton "+attribute("class",""), 
    onclick:= attribute("onclick",""),
    style := attribute("style","")	
  ] {
    image("images/"+kind.toLowerCase()+".png")
    if (showCaption == true) { 
       break
       output(kind)
    }
  }
}