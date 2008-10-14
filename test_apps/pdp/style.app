module style

style globalStyle

  const mainColor : Color := #330099;
  const globalFont : Font := Font.Lucida.Grande;
  const layoutBorderColor : Color := #c0c0c0;

  main() {
    font := globalFont;
  }

  section() {
    font := globalFont;
    //font-size := globalFontSize;
  }

  inputText() {
    width := 400px;
    height := 8em;
  }
  
  navigate() {
    font := globalFont;
    //font-size := globalFontSize;
  }
  
  header() {
    font := globalFont;
    font-size := 1.3333em;
    margin-top := 0px;
  }
  
  table() {
    font := globalFont;
    //font-size := globalFontSize;
  }
  
  table() > header() {
    font := globalFont;
    //font-size := 1em;
    font-style := FontStyle.bold;
    font-color := #404040;
    image := url("images/menubar_bg.png");
    image-repeat := Repeat.horizontal;
    border-color := layoutBorderColor;
  }
  
  table() > row() {
    background-color := Color.white;
  }
  
  //@todo: dealing with font-size must be easier!
  /*
  form() >> group() {
    font-size := globalFontSize * 1.333em;
  }
  */
  
  group() {
    font := globalFont;
    font-size := 1em;
    width := 45em;
  }
  
  /*
  group() >> navigate() {
    font-size := 1.333em * globalFontSize;
  }
  */

style templateStyle

  topmenu() {
    width := 100%;
    margin-bottom := 1em;
  }
  
  top() {
    background-color := Color.white;
    width := 100%;
  }
  
  top() >> .logos {
    width := 100%;
  }
  
  top() > .logo_area {
    width := 100%;
    align := Align.center;
  }
  
  main() > .mainbody {
    width := 80%;
    align := Align.center;
    padding-top := 4px;
  }
  
  sidebar() {
    width := 12em;
    padding := 1em;
    font-size := 0.8em;
  }

  sidebar() >> list() {
    orientation := Orientation.vertical;
    separator := Separator.none;
  }
  
  body() {
    padding := 1em;
     border-left-color := layoutBorderColor;
    border-left-style := BorderStyle.solid;
    border-left-width := 1px;
    font-size := 0.8em;
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
  
/*  output(c : List<Specialty>) >> list() {
    orientation := Orientation.horizontal;
    separator := Separator.comma;
    spacing-right := 0.2em;
  }
  
  page allPet() >> list() {
    orientation := Orientation.horizontal;
    separator := Separator.comma;
    spacing-right := 0.2em;
  }
  
  page allOwner() >> ownerDetails(o : Owner) {
    spacing-right := 4em;
  }*/

style formStyle

  const labelWidth : Length := 8em;
  const formItemSpacing : Length := 0.3em;

  group() > groupitem() {
    spacing := formItemSpacing;
    //width := 100%;
    //width := 42em;
  }
  
  group() {
    image := url("images/background_gradient.png");
    border-top-color := #003399;
    border-bottom-color := #003399;
    background-color := #ccccff;
  }
  
/*  page createOwner() >> group() >> label() {
    align := Align.left;
    width := labelWidth;
  }*/
  


/*
  width: 27.125em;#createOwner fieldset > ol.padding-left +			1  em = 16px
            #createOwner fieldset > ol.padding-right +		1  em = 16px
            #createOwner fieldset > ol label.margin-right +	1  em = 16px
            #createOwner fieldset > ol label.width			8  em = 128px
            ol input.width * ol input.font-size				16 em = 256px
            2 * 1px input border								0.125 em = 2px
                                      ==============
                                      27.125em  = 434px
*/  

style menuStyle

  const fontColor : Color := #505050;
  const globalBorderColor : Color := #c0c0c0;

  topmenu() {
    background-color := #ffff00;
    image := url("images/menubar_bg.png");
    image-repeat := Repeat.horizontal;
    border-color := globalBorderColor;
    border-width := 1px;
    border-style := BorderStyle.solid;
  }

  topmenu() >> menubar() {
    width := 80%;
    align := Align.center;
  }
/*  
  topmenu() >> menubar() > menu() > menuheader() {
    border-right-color := globalBorderColor;
    border-right-width := 1px;
    border-right-style := BorderStyle.solid;
  }
*/  
  topmenu() >> menu() > menuheader() > navigate() {
    padding := 0.3em;
    font := Font.Lucida.Grande; // | Font.Verdana | Font.sans-serif;
    font-color := fontColor;
    font-size := 0.75em;
    font-line := Line.none;
  }
  
  topmenu() >> menu() >> navigate():hover {
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
    spacing := 2em; // @todo: rename to spacing and set on listitem()??
  }
