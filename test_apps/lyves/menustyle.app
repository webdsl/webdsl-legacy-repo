module menustyle

style menuStyle

  const lgrayColor : Color := #cccccc;
  const dgrayColor : Color := #333333;
  const ngrayColor : Color := #999999;
  const plainColor: Color := #000099;
  const overColor : Color := #ff0000;

  quicksearch() {
    align := Align.right;
  }

  menubar() {
    width := 1024px;
    image := url("images/menubar_bg.png");
    image-repeat := Repeat.horizontal;
    height := 28px;
    border-color := dgrayColor;
    border-style := BorderStyle.solid;
    border-width:= 1px;
  }

  menuheader() {
    border-color := Color.transparent;
     border-style := BorderStyle.solid;
    border-width:= 1px;
  }

  span.menuheader:hover {
    image := url("images/menubar_bg_hover.png");
    image-repeat := Repeat.horizontal;
    border-color := Color.black;
  }

  menuheader() >> navigate() {
    font-color := plainColor;
    font-line := Line.none;
  }
  
  span.menuheader:hover > navigate () {
    font-color := overColor;
  }
  
  .menuitems {
    border-color := dgrayColor;
    border-style := BorderStyle.solid;
    border-width:= 1px;    
    background-color := lgrayColor;
  }
  
  .menuitems >> navigate() {
    font-color := plainColor;
    font-line := Line.none;
  }
  
  
  li.menuitem:hover {
    border-color := overColor;
    border-style := BorderStyle.solid;
    border-width:= 1px;      
    background-color := ngrayColor;
  }
    
  li.menuitem:hover > navigate() {
    font-color := overColor;
  }