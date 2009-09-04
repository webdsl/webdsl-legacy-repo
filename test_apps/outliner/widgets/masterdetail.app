module masterdetail

section templates

define template masterdetail() requires detailview(),masterview()  {
  twoColumns with {
    left() {
      masterview()
    }
    right() {   
      detailview()
    }
  }
}

