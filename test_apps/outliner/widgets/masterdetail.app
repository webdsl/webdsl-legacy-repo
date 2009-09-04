module masterdetail

section templates

define template masterdetail() requires detailview(),masterview()  {
  table [width:= "100%"] {
    row {
      column {
        collapseLeft() {
          "left"
          //FIX: masterview()
        }
      }
      column {
        detailview()
      }
    }
  }
}

