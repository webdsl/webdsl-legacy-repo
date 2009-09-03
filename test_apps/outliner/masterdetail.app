module masterdetail

section templates

define template masterdetail() requires detailview(),masterview()  {
  table {
    row {
      column[width:= '50%'] {
        masterview()
      }
      column[width:= '50%', id:= masterdetailcontents] {
        detailview()
      }
    }
  }
}

