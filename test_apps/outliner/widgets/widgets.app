module widgets

section templates

define inputtemplate inplaceTextArea(value: Text, name: String) {
  <div ondblclick="this.innerHTML='<textarea onblur=\"this.form.onsubmit();\" name=\""+name+"\" style=\"width:'+(this.clientWidth-10)+'px;height:'+(this.clientHeight+40)+'px;\">"+value+"</textarea>';">
    output(value)
  </div>
}

define template no-span collapsePanel(collapsed: Bool) requires caption(), contents() {
  var initv : String;
  init {
    if (collapsed) { initv := "block"; } else { initv := "hidden"; }
  }
    
  block[style:=" border: 1px dashed #666666; width: 100%", class:="collapsePanel"] { 
    block[style:= "background-color:#CCCCCC; width: 100%; border: 1px solid #666666"] {
      navigate()[onclick := action{ visibility(collapsetarget, toggle); }]{ ">   |" } 
      caption()
    }
    break
    block[style:= "padding-left: 20px; visiblity:"+initv, id := collapsetarget] {
      contents()
    }
  }
}

define template collapseUp() {
  block[width:="100%", id:= collapsecontentsup] {
    elements()
  }
  block[style := "width:100%; height: 8px; background-color: gray; text-align: center"]{
    navigate()[onclick := action { visibility (collapsecontentsup, toggle); }]{ "^^^" }
  }
}

define template collapseLeft() {
  twoColumns[right:= "16px", width:= ""] with {
    left() {
      block[id:= collapsecontentsleft] {
        elements()
      }
    }
    right() {
      block[style := "display:inline; height:100%; width: 100%; background-color: gray; text-align: center; vertical-align: center;"]{
        navigate()[onclick := action { visibility (collapsecontentsleft , toggle); }]{ "<\n<\n<" }
      }
    }
  }
}


define template no-span twoColumns() requires left(), right() {
  table[width := attribute("width","100%")] {
    row {
      column[width:= attribute("left","*"),  style:="vertical-align:top"] {
        left()
      }
      column[width:= attribute("right","*"),  style:="vertical-align:top"] {
        right()		
      }
    }
  }
}

style widgetsStyle

twoColumns() { }

