module widgets

section templates

define template collapsePanel(header: String, collapsed: Bool) {
  var initv : String;
  init {
    if (collapsed) { initv := "block"; } else { initv := "hidden"; }
  }
    
  navigate[
      onclick := action{ visibility(collapsetarget, toggle); },
      style:= "background-color:#CCCCCC"
    ]
    {">" output(header)}
  break

  block[style:= "padding-left: 20px; visiblity:"+initv, id := collapsetarget] {
    elements()
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
      block[style := "display:inline; height:100%; width: 100%; background-color: gray; text-align: center; valign: center;"]{
        navigate()[onclick := action { visibility (collapsecontentsleft , toggle); }]{ "<\n<\n<" }
      }
    }
  }
}


define template no-span twoColumns() requires left(), right() {
  table[width := attribute("width","100%")] {
    row {
      column[width:= attribute("left","*")] {
        left()
      }
      column[width:= attribute("right","*")] {
        right()		
      }
    }
  }
}