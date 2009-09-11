module image

section templates


define viewImage(item: ImageNode) {
  block[id := viewImage, class :="scopediv viewText", style := "background-color : #CCCCCC; "] {
    if (item.image== null) {
      navigate[onclick := editac(item)] {
        "[click to provide an image]"
      }
    }
    else {
      navigate[onclick := editac(item)] {
        output(item.image)
      }
    }
  }
  action editac(item: ImageNode) {
    replace (this, editImage(item));
  }
}

define editImage(item: ImageNode) {
  form {
    input(item.image)
    action("save")[onclick:= save(item),id:= submit]
  }
  action save(item: ImageNode) {
    replace(this, viewImage(item));
  }	
}
