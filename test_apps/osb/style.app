module style

style navigationStyle

  topmenu() >> navigate() {
    font-color := Color.white;
    font-line := Line.none;
  }
  
  mainNavigation() >> list() {
    orientation := Orientation.horizontal;
    separator := Separator.none;
    spacing := 1em;
  }
  
  adminNavigation() >> list() {
    orientation := (mainNavigation() >> list()).orientation;
    separator := (mainNavigation() >> list()).separator;
    spacing := (mainNavigation() >> list()).spacing;
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
    border-style := BorderStyle.none;
    spacing := 0.2em;
  }
  
  page home() >> form() > group() > groupitem() {
    border-bottom-style := BorderStyle.solid;
    border-bottom-width := 2px;
    border-bottom-color := houseColor;
    padding-top := 0.5em;
    padding-bottom := 0.5em;
    padding-right := 10em;
  }
  
  page home() >> form() >> group() >> action() {
    align := Align.left;
  }

style templateStyle

  sidebarText() {
    background-color := #c0c0c0;
    font := globalFont;
    font-size := globalFontSize;
    padding-left := 1em;
    padding-right := 1em;
    padding-top := 0.3em;
    padding-bottom := 1em;
  }
  
  sidebarText()  > header() {
    font-color := houseColor;
    font := globalFont;
    font-size := 1.3em;
  }

  