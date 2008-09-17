module style

style navigationStyle

  template topmenu() >> navigate() {
    font-color := Color.white;
    font-line := Line.none;
  }
  
  template mainNavigation() >> list() {
    orientation := Orientation.horizontal;
    separator := Separator.none;
    spacing := 1em;
  }
  
  template adminNavigation() >> list() {
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
    border-top-style := BorderStyle.none;
    border-bottom-style := BorderStyle.none;
  }

style templateStyle

  const globalFont : Font := Font.Arial;
  const globalFontSize : Length := 0.8em;
  const houseColor : Color := #70630d;

  template sidebarText() {
    background-color := #c0c0c0;
    font := globalFont;
    font-size := globalFontSize;
    padding-left := 1em;
    padding-right := 1em;
    padding-top := 0.3em;
    padding-bottom := 1em;
  }
  
  template sidebarText()  > header() {
    font-color := houseColor;
    font := globalFont;
    font-size := 1.3em;
  }

  