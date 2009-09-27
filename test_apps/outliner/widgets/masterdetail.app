module masterdetail

section templates

define no-span template masterdetail() requires detailview(),masterview()  {
  twoColumns[width:= "100%", left := attribute("left",""), right:= "100%"] with {
    left() {
      collapseLeft() {
        masterview()
      }
    }
    right() {
      detailview()
    }
  }  
}

