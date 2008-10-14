module style

style globalTemplateStyle
  
  const bgcolor1 : Color := #00ffdd;
  const bgcolor2 : Color := #00aadd;
  const bgcolor3 : Color := #ffeeaa;
  const mainColor : Color := #330099;
  const globalBorderColor : Color := #c0c0c0;
  const globalFont : Font := Font.Lucida.Grande;
  const globalFontSize : Length := 0.75em;
  
  page home() {
    
  }
  
  section() {
    font := globalFont;
    font-size := globalFontSize;
  }
  
  navigate() {
    font := globalFont;
    font-size := globalFontSize;    
  }
    
  template main_sidebar() {
    background-color := Color.red;
  }
  
  template mainbody() {
    background-color := Color.red;
  }
  
  template main_mainbody() {
//    background-color := sidebar().background-color;
    background-color := Color.green;
  }
  
  template topmenubar() {
    margin-bottom := 1em;
  }

  template footer() {
    background-color := Color.white;
    border-top-width := 1px;
    border-top-color := globalBorderColor;
    border-top-style := BorderStyle.solid;
    font := globalFont;
    font-size := 0.7em;
    width := top().width;
    margin-top := topmenubar().margin-bottom;
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
  
  
// template main() > body as newname {
// newname for template main() > body {
// newname for body in template main() {
// body in template main() {
  template main() > #body {
    width := 80%;
    align := Align.center;
    padding-top := 4px;
  }
  
  
  template top() >> #logos {
    width := 25em;
  }
  
  template top() > #bla {
    width := 80%;
    align := Align.center;
  }

  
  template main_sidebar() {
    width := 15em;
  }


style menuStyle

//  menuheader() in topmenu()
//  menuheader() of menu() of menubar() of topmenu()
//          #topmenu   #p7menubar li a

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
  
  template topmenu() >> menu() > menuheader():first-child {
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
  
  template topmenu() >> menu() >> navigate():hover {
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
    separator-space := 20px; // @todo: rename to spacing and set on listitem()
  }

/*  
  template footer() > list() > listitem() {
      float
  }
*/