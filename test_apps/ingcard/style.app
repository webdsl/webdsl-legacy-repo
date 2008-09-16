module style

style menuStyle

  template sideMenu() > list() {
    orientation := Orientation.vertical;
    separator := Separator.none;
    align := Align.center;
    margin-top := 0.5em;
    spacing := 0.2em;
  }
  
  template sideMenu() > list() > listitem() {
    background-color := #f95807;
  }
  
  template sideMenu() > list() > listitem() > navigate() {
    font-color := Color.white;
    font-line := Line.none;
  }

style globalStyle 

  const globalFont : Font := Font.Arial;
  const globalFontSize : Length := 0.8em;
  
  navigate() {
    font := globalFont;
    font-size := globalFontSize;
  }
  
  form() {
    font := globalFont;
    font-size := globalFontSize;
  }
  
  form() >> group() {
    font := globalFont;
    font-size := 1em;
  }
  
  group() {
    font := globalFont;
    font-size := globalFontSize;
  }
  
  group() > groupitem() > label() {
    align := Align.left;
    width := 10em;
  }
  
  form() > group() > groupitem() {
    spacing := 0.2em;
  }
  
  table() {
    font := globalFont;
    font-size := globalFontSize;
  }
  
  table() >> navigate() {
    font := globalFont;
    font-size := 1em;
  }

style homeStyle

  page home() >> form() {
    margin-top := 1em;
  }
  
  page home() >> form() > group() {
    border-top-style := BorderStyle.none;
    border-bottom-style := BorderStyle.none;
  }

style templateStyle

  template customerDetails(c : Customer) > group() {
    width := 20em;
    background-color := #feece0;
  }

  template customerDetails(c : Customer) > list() {
    orientation := Orientation.horizontal;
    separator := Separator.tab;
    spacing := 1em;
    margin-top := 1em;
    margin-bottom := 1em;
  }
  
  template customerDetails(c : Customer) > list() >> navigate() {
    font-line := Line.none;
  }
