module widgets

section templates

define template collapsePanel(header: String, collapsed: Bool) {
  ">" output(header)
  spacer
  container[style:= "padding-left: 20px"] {
    elements()
  }
}

define template no-span twoColumns() requires left(), right() {
  table[width := ""+attribute("width","100%")] {
    row {
      column[width:= ""+attribute("left","*")] {
        left()
      }
      column[width:= ""+attribute("right","*")] {
      
        right()		
      }
    }
  }
}