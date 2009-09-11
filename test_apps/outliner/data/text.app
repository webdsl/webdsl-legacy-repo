module text

section templates


define no-span viewText(item: TextNode) {
  block[id := viewText, class :="scopediv viewText", style := "background-color : #CCCCCC; "] {
    form {
      inplaceTextArea(item.contents)
      navigate[id := submit, onclick:= saveac()]{"[save]"}
    }
  }
  action saveac() {
    replace(this, viewText(item));
  }
}