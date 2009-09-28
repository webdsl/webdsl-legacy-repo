module collapse

section templates

define template no-span collapsePanel(collapsed: Bool) requires caption(), contents() {
  var initv : String;
  init {
    if (collapsed) { initv := "block"; } else { initv := "hidden"; }
  }
  block[class:=collapsePanelOuter] { 
    block[class:= collapsePanelHeader] {
      image("/images/right.png")
        [onclick := action{ visibility(collapsetarget, toggle); }] 
      caption()
    }
    block[class:= collapsePanelContent, style:= "visiblity:"+initv, id := collapsetarget] {
      contents()
    }
  }
}

define template collapseUp() {
  block[style:="width: "+attribute("width","auto"), id:= collapsecontentsup] {
    elements()
  }
  block[
    class:=hdivider,
    onclick := action { visibility (collapsecontentsup, toggle); }
  ]
}

define template collapseLeft() {
  twoColumns[right:= "16px", width:= ""] with {
    left() {
      block[id:= collapsecontentsleft] {
        elements()
      }
    }
    right() {
      image("/images/dividerv.png")[
        onclick := action { visibility (collapsecontentsleft , toggle); }
      ]
    }
  }
}