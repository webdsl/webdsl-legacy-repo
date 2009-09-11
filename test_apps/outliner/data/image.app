module image

section templates


define viewImage(item: ImageNode) {
  block[id := viewImage, class :="scopediv viewText", style := "background-color : #CCCCCC; "] {
    if (item.image== null) {
      "[click to provide an image]"
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


define no-span editImage(item: ImageNode) {
  form {
    input(item.image)
    navigate[onclick:= save(item),id:= submit]{"[Save]"}
  }
  action save(item: ImageNode) {
    replace(this, viewImage(item));
  }	
}
