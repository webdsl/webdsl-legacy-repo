module style

style globalStyle

  const mainColor : Color := #330099;
  const globalFont : Font := Font.Lucida.Grande;
  const globalFontSize : Length := 0.75em;
  const layoutBorderColor : Color := #c0c0c0;

  section() {
    font := globalFont;
    font-size := globalFontSize;
  }
  
  navigate() {
    font := globalFont;
    font-size := globalFontSize;
  }
  
  header() {
    font := globalFont;
    font-size := globalFontSize * 1.3333em;
    margin-top := 0px;
  }
  
  table() {
  	font := globalFont;
    font-size := globalFontSize;
  }
  
  table() > header() {
    font := globalFont;
    font-size := 1em;
    font-weight := Weight.bold;
    font-color := #404040;
    image := url("images/menubar_bg.png");
    image-repeat := Repeat.horizontal;
    border-color := layoutBorderColor;
  }
  
  table() > row() {
    background-color := Color.white;
  }
  
  block() {
    font := globalFont;
    font-size := 1em;
  }
  
  form() {
    font := globalFont;
    font-size := globalFontSize;
  }


style templateStyle

  const layoutBorderColor : Color := #c0c0c0;
  const globalFont : Font := Font.Lucida.Grande;
  
  template top() {
    background-color := Color.white;
    width := 100%;
  }
  
  template top() >> #logos {
    width := 25em;
  }
  
  template top() > #logo_area {
    width := 80%;
    align := Align.center;
  }
  
  template main() > #mainbody {
    width := 80%;
    align := Align.center;
    padding-top := 4px;
  }
  
  template sidebar() {
  	width := 15em;
  	padding := 1em;
  }
  
  template body() {
    padding := 1em;
   	border-left-color := layoutBorderColor;
  	border-left-style := BorderStyle.solid;
  	border-left-width := 1px;
  }

  template footer() {
    background-color := Color.white;
    border-top-width := 1px;
    border-top-color := layoutBorderColor;
    border-top-style := BorderStyle.solid;
    font := globalFont;
    font-size := 0.7em;
    width := top().width;
    margin-top := topmenu().margin-bottom;
  }

style formStyle

  const labelWidth : Length := 8em;
  const formItemSpacing : Length := 0.3em;

  group() > groupitem() {
    spacing := formItemSpacing;
  }
  
  form() >> group() {
    image := url("images/background_gradient.png");
    border-top-color := #003399;
    border-bottom-color := #003399;
    background-color := #ccccff;
  }
    

style menuStyle

  const globalBorderColor : Color := #c0c0c0;
  const fontColor : Color := #505050;

/*  template topmenu() {
    background-color := #ffff00;
    image := url("images/menubar_bg.png");
    image-repeat := Repeat.horizontal;
    border-color := globalBorderColor;
    border-width := 1px;
    border-style := BorderStyle.solid;
  }

  template topmenu() >> menubar() {
    width := 80%;
    align := Align.center;
  }

  template topmenu() >> menu() > menuheader() > navigate() {
    padding := 0.3em;
    font := Font.Lucida.Grande; // | Font.Verdana | Font.sans-serif;
    font-color := fontColor;
    font-size := 0.75em;
    font-line := Line.none;
  }
  
  template topmenu() >> menu() >> navigate().hover {
    image := url("images/menubar_bg_hover.png");
    image-repeat := Repeat.horizontal;
  }*/

style footerStyle

  template footer() >> #footer_text {
    align := Align.right;
  }
  
  template footer() >> #footer_links {
    align := Align.left;
  }

  template footer() >> navigate() {
    font-size := 1.0em;
  }
  
  template footer() >> list() {
    orientation := Orientation.horizontal;
    separator := Separator.pipe;
//    separator-spacing := 2em; // @todo: rename to spacing and set on listitem()
  }
