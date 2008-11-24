
// http://www.w3.org/WAI/GL/css2em.htm

module style

style globalConstants

  const bgColor          : Color  := Color.white;
  
  const bodyFont         : Font   := Font.Helvetica;
  const bodyFontColor    : Color  := Color.Black;
  const linkFontColor    : Color  := Color.Navy;
  const bodyFontSize     : Length := 11pt;
  const headerFontSize   : Length := 12pt;
  const footnoteFontSize : Length := 10pt;
  const bigFontSize      : Length := 36pt;
  
  const layoutBorderColor : Color := #c0c0c0;
  const globalBorderColor : Color := #c0c0c0;

  const labelWidth      : Length := 8em;
  const formItemSpacing : Length := 0.3em;
  
  const canvasWidth : Length := 700px;
  
style globalStyle

  pre() {
    font      := Font.Courier;
    font-size := bodyFontSize;
  }

  main() {
    font             := bodyFont;
    font-size        := bodyFontSize;
    font-color       := bodyFontColor;
    background-color := bgColor;
    //align          := Align.center;
  }
  
  .mainContent {
    display := Display.block;
    //align   := Align.left;
  }
  
  section() {
    display := Display.block;
  }

  header() {
    font-size  := headerFontSize;
    font-style := FontStyle.bold;
  }
  
  navigate() {
    font-line  := Line.none;
    font-color := linkFontColor;
  }
  
  table() > header() {
    font-style   := FontStyle.bold;
    font-color   := #404040;
    //image        := url("images/menubar_bg.png");
    //image-repeat := Repeat.horizontal;
    border-color := layoutBorderColor;
  }

  textarea {
    width := 50em;
    height := 35em;
  }
  
style templateStyle

  main() {
    display  := Display.block;
    width    := 100%;
  }

  top() {
    display        := Display.block;
    width          := canvasWidth;
    //align          := Align.center;
    border-style   := BorderStyle.solid;
    border-width   := 0px;
    border-color   := Color.red;
    padding-top    := 0px;
    padding-bottom := 10px;
  }

  top() >> .brand {
    font-size := bigFontSize;
  }
  
  top() >> .brand >> navigate() {
    font-color := Color.FireBrick;
    font       := Font.Times;
  }
  
  top() >> .brand > .brandsub {
    font-size := footnoteFontSize;
  }
  
  topRight() {
    display   := Display.block;
    align     := Align.right;
    font-size := footnoteFontSize;
  }
  
  body() {    
    display        := Display.block;
    width          := canvasWidth;
    //align          := Align.center;
    border-style   := BorderStyle.solid;
    border-width   := 0px;
    border-color   := Color.purple;
    padding-top    := 10px;
    padding-bottom := 20px;
  }

  footer() {
    display        := Display.block;
    font-size      := footnoteFontSize ;
    width          := canvasWidth;
    //align          := Align.center;
    border-top-style   := BorderStyle.dotted;
    border-top-width   := 1px;
    border-top-color := Color.grey;
    padding-top    := 10px;
    padding-bottom := 10px;
  }
  
  /*
  output(c : List<Specialty>) >> list() {
    orientation := Orientation.horizontal;
    separator := Separator.comma;
    spacing-right := 0.2em;
  }
  
  page allOwner() >> ownerDetails(o : Owner) {
    spacing-right := 1em;
  }
  
  page allPet() >> list() {
    orientation := Orientation.vertical;
    separator := Separator.none;
  }
  
  ownerDetails(o : Owner) > group() > groupitem() {
    width := 22em;
  }
  */
  
style formStyle

  group() > groupitem() {
    spacing := formItemSpacing;
  }
  
  group() {
    display             := Display.block;
    // image               := url("images/background_gradient.png");
    //border-top-color    := #003399;
    //border-bottom-color := #003399;
    //background-color    := #ccccff;
  }
  
  //group() >> label() {
  //  align := Align.left;
  //  width := labelWidth;
  //}
  
style menuStyle

  topmenu() {
    display          := Display.block;
    width            := canvasWidth;
    align            := Align.center;

    background-color := Color.black;
    font-color       := Color.white;
    font             := bodyFont;
    font-size        := bodyFontSize;
    
    padding          := 0px;
  }
     
  .menubar {
     width            := canvasWidth;
     background-color := Color.black;
     border-color     := Color.black;
     border-style     := BorderStyle.solid;
     border-width     := 1px;
  }
  
  .menuheader {
     background-color := Color.black;
     font-color       := Color.white;
     border-color     := Color.black;
   }
   
  .menuheader > navigate() {
     background-color := Color.black;
     font-color       := Color.white;
     border-color     := Color.black;
   }
   
   span.menuheader:hover {
     background-color := Color.white;
     font-color       := Color.black;
     border-color     := Color.black;
   }
   
   span.menuheader:hover > navigate() {
     background-color := Color.white;
     font-color       := Color.black;
     border-color     := Color.black;
   }
   
   .menuitems {
     font-color       := Color.white;
     background-color := Color.black;
     border-color     := Color.black;
     border-style     := BorderStyle.solid;
     border-width     := 1px;
   }
   
   .menuitem {
     background-color := Color.black;
     font-color       := Color.white;
     border-color     := Color.black;
   }
   
   .menuitem > navigate() {
     background-color := Color.black;
     font-color       := Color.white;
     border-color     := Color.black;
   }
   
   li.menuitem:hover {
     background-color := Color.white;
     font-color       := Color.black;
     border-color     := Color.black;
   }

   li.menuitem:hover > navigate() {
     background-color := Color.white;
     font-color       := Color.black;
     border-color     := Color.black;
   }


   
style footerStyle

  footer() {
    font-size := footnoteFontSize;
  }

  footer() >> .footer_text {
    align := Align.right;
  }
  
  footer() >> .footer_links {
    //align := Align.left;
  }

  footer() >> list() {
    orientation := Orientation.horizontal;
    separator   := Separator.pipe;
    spacing     := 1em; // @todo: rename to spacing and set on listitem()??
  }

