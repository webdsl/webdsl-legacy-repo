module style

style globalStyle

  const mainColor : Color := #330099;
  const globalFont : Font := Font.Lucida.Grande;
  const globalFontSize : Length := 0.75em;
  const layoutBorderColor : Color := #c0c0c0;

  template main() {
    width := 800px;
    align := Align.center;
    padding-top := 4px;
    font := globalFont;
    font-size := globalFontSize;
  }

  section() {
    font := globalFont;
  }
  
  /*
  navigate() {
    font := globalFont;
  }
  */
  
  header() {
    font := globalFont;
    font-size := globalFontSize * 1.5em;
    margin-top := 0px;
  }
  
  table() {
    font := globalFont;
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
  }
  

style templateStyle

  const layoutBorderColor : Color := #c0c0c0;
  const globalFont : Font := Font.Lucida.Grande;

  template topmenu() {
    width := 100%;
    margin-bottom := 1em;
  }
  
  template top() {
    background-color := Color.white;
    width := 100%;
  }
  
  template top() >> .logos {
    width := 20em;
  }
  
  template top() > .logo_area {
    width := 100%;
    align := Align.center;
  }

  template top() >> .inputText {
    width := 30em;
    height := 3em;
  }

  template sidebar() {
    width := 12em;
    padding := 1em;
  }

  template sidebar() >> list() {
    orientation := Orientation.vertical;
    separator := Separator.none;
  }


  template main() > body() {
    width := 80%;
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
    font-size := 0.9em;
    width := top().width;
    margin-top := topmenu().margin-bottom;
  }
  
  template displayMessage(m : Message) {
    border-width := 1px;
    border-color := layoutBorderColor;
    border-style := BorderStyle.solid;
    margin-bottom := 5px;
    padding-left := 20px;
    padding-top := 5px;
    padding-bottom := 5px;
  }

  template displayMessage(m : Message) >> .metadata {
    font-weight := Weight.bold;
  }

style formStyle

  const labelWidth : Length := 8em;
  const formItemSpacing : Length := 0.3em;

  group() > groupitem() {
    spacing := formItemSpacing;
  }
  
  group() {
    image := url("images/background_gradient.png");
    border-top-color := #003399;
    border-bottom-color := #003399;
    background-color := #ccccff;
    width := 100%;
  }

  group() >> label() {
    width := labelWidth;
  }

  inputText() {
    width := 400px;
    height := 3em;
  }
  
style menuStyle

  const globalBorderColor : Color := #c0c0c0;
  const fontColor : Color := #505050;

  template topmenu() {
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
    font := Font.Lucida.Grande; 
    font-color := fontColor;
    //font-size := 0.75em;
    font-line := Line.none;
  }
  
  template topmenu() >> menu() >> navigate():hover {
    image := url("images/menubar_bg_hover.png");
    image-repeat := Repeat.horizontal;
  }

style footerStyle

  template footer() >> .footer_text {
    align := Align.right;
  }
  
  template footer() >> .footer_links {
    align := Align.left;
  }

  template footer() >> navigate() {
    font-size := 1.0em;
  }
  
  template footer() >> list() {
    orientation := Orientation.horizontal;
    separator := Separator.pipe;
    spacing := 2em; 
  }
