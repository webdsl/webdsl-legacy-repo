module style

style globalStyle

  const mainColor : Color := #330099;
  const globalFont : Font := Font.Gill.Sans; //Font.Lucida.Grande;
  //const globalFont : Font := Font.Lucida.Grande;
  const globalFontSize : Length := 1em;//0.75em;
  const layoutBorderColor : Color := #c0c0c0;

  main() {
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
    font-size := globalFontSize * 1.1em;
    margin-top := 15px;
    margin-bottom := 5px;
  }

  sidebar() >> header() {
    font := globalFont;
    font-size := globalFontSize * 1.1em;
    margin-top := 10px;
    margin-bottom := 3px;
  }
  
  table() {
  	font := globalFont;
  }
  
  table() > header() {
    font := globalFont;
    font-size := 1em;
    font-style := FontStyle.bold;
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

  //Gill Sans','Gill Sans MT','Ikarius ADF',Candara

  /*
  topmenu() {
    width := 100%;
    margin-bottom := 1em;
  }
  */
  
  top() {
    background-color := Color.white;
    width := 100%;
    font-size := 100px;
  }
  
  /*
  top() >> .logos {
    //width := 20em;
    //height := 100px;
    font-size := 100px;
  }
  */
  
  /*
  top() > .logo_area {
    width := 100%;
    align := Align.center;
  }
  */

  sidebar() {
  	width := 12em;
  	padding := 1em;
  }

  sidebar() >> list() {
    orientation := Orientation.vertical;
    separator := Separator.none;
  }


  main() > body() {
    width := 80%;
  }
  
  body() {
    padding := 1em;
    /*
   	border-left-color := layoutBorderColor;
  	border-left-style := BorderStyle.solid;
  	border-left-width := 1px;
    */
  }

  footer() {
    background-color := Color.white;
    border-top-width := 1px;
    border-top-color := layoutBorderColor;
    border-top-style := BorderStyle.solid;
    font-size := 0.9em;
    width := 100%;
  }
  
    /*
  displayPost(p : Post) {
    border-width := 1px;
    border-color := layoutBorderColor;
    border-style := BorderStyle.solid;
  }
    */

  displayPost(p : Post) >> header() {
    font-size := 1.3em;
  }

  displayPost(p : Post) >> header() > navigate() {
    font-line := Line.none;
    font-color := Color.black;
  }

  .metadata {
   	border-bottom-color := layoutBorderColor;
  	border-bottom-style := BorderStyle.solid;
  	border-bottom-width := 1px;
    //font-style := FontStyle.italic;
  }

  .commentmetadata {
   	border-top-color := layoutBorderColor;
  	border-top-style := BorderStyle.solid;
  	border-top-width := 1px;
    //font-style := FontStyle.italic;
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
    height := 8em;
  }

  .inputWikiText {
    width := 480px;
    height := 25em;
  }

  header() > .inputString {
    font-size := 1.2em;
    font-style := FontStyle.bold;
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
