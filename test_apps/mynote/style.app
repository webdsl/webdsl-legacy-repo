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
  
  table() >> header() {
    font := globalFont;
    font-size := 1em;
    font-style := FontStyle.bold;
    font-color := #404040;
    image := url("images/menubar_bg.png");
    image-repeat := Repeat.horizontal;
    border-color := layoutBorderColor;
  }
  
  table() >> row() {
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
  
    
  form() >> group() {
    font-size := globalFontSize * 1.333em;
  }
  
  group() {
    font := globalFont;
    font-size := globalFontSize;
  }
  
  group() >> navigate() {
    font-size := 1.333em * globalFontSize;
  }

style templateStyle

  
  
  top() {
    background-color := Color.white;
    width := 80%;
  }
  
  top() >> .logos {
    width := 25em;
  }
  
  top() >> .logo_area {
    width := 80%;
    align := Align.center;
  }


  topmenu() {
    width := 20%;
  }
  
  quickadd() {
    width := 30%;
  }
  
  quicksearch() {
    width := 30%;
  }
  
  main()  {
    width := 80%;
    padding-top := 4px;
  }
  


  footer() {
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

  group() >> groupitem() {
    spacing := formItemSpacing;
  }
  
  group() {
    image := url("images/background_gradient.png");
    border-top-color := #003399;
    border-bottom-color := #003399;
    background-color := #ccccff;
  }
  
style menuStyle

  const globalBorderColor : Color := #c0c0c0;
  const fontColor : Color := #505050;

  topmenu() {
    background-color := #ffff00;
    image := url("images/menubar_bg.png");
    image-repeat := Repeat.horizontal;
    border-width := 1px;
    border-color := globalBorderColor;
    border-style := BorderStyle.solid;
  }

  topmenu() >>  navigate() {
    padding := 0.3em;
    font := Font.Lucida.Grande; // | Font.Verdana | Font.sans-serif;
    font-color := fontColor;
    font-size := 0.75em;
    font-line := Line.none;
  }
  
  topmenu() >> navigate().hover {
    image := url("images/menubar_bg_hover.png");
    image-repeat := Repeat.horizontal;
  }

style footerStyle

  footer() >> .footer_text {
    align := Align.right;
  }
  
  footer() >> .footer_links {
    align := Align.left;
  }

  footer() >> navigate() {
    font-size := 1.0em;
  }
  
 footer() >> list() {
    orientation := Orientation.horizontal;
    separator := Separator.pipe;
    spacing := 2em;
  }
