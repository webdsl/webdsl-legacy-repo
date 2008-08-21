module style

style globalStyle

  const mainColor : Color := #330099;
  const globalFont : Font := Font.Lucida.Grande;
  const globalFontSize : Length := 0.75em;

  section() {
    font := globalFont;
    font-size := globalFontSize;
  }
  
  navigate() {
    font := globalFont;
    font-size := globalFontSize;    
  }


style templateStyle

  const layoutBorderColor : Color := #c0c0c0;
  const globalFont : Font := Font.Lucida.Grande;

  template topmenubar() {
    margin-bottom := 1em;
  }
  
  template topmenu() {
    width := 80%;
    align := Align.center;
  }
  
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
  
  template main() > #body {
    width := 80%;
    align := Align.center;
    padding-top := 4px;
  }
  
  template sidebar() {
  	width := 15em;
  }

  template footer() {
    background-color := Color.white;
    border-top-width := 1px;
    border-top-color := layoutBorderColor;
    border-top-style := BorderStyle.solid;
    font := globalFont;
    font-size := 0.7em;
    width := top().width;
    margin-top := topmenubar().margin-bottom;
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
  
  page createOwner() >> group() >> label() {
    align := Align.right;
    width := labelWidth;
  }

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

  const globalBorderColor : Color := #c0c0c0;
  const fontColor : Color := #505050;

  template topmenubar() {
    background-color := #ffff00;
    width := 100%;
    image := url("images/menubar_bg.png");
    image-repeat := Repeat.horizontal;
    border-color := globalBorderColor;
    border-width := 1px;
    border-style := BorderStyle.solid;
  }
  
  template topmenu() >> menu() > menuheader() {
    border-right-color := globalBorderColor;
    border-right-width := 1px;
    border-right-style := BorderStyle.solid;
  }
  
  template topmenu() >> menu() > menuheader().first-child {
    border-left-color := globalBorderColor;
    border-left-width := 1px;
    border-left-style := BorderStyle.solid;
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
  }

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
    separator-space := 2em; // @todo: rename to spacing and set on listitem()
  }
