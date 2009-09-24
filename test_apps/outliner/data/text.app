module text

section templates


define no-span viewText(item: TextNode) {
 block[id:= viewText, class:=[scopediv, viewText]] {
  inplaceTextArea(item.contents)[onblur:= saveac(null)]
  action saveac(value: String) {
    item.contents := value;
    replace(this, viewText(item));
  }
 }
}