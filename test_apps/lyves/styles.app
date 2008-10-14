module styles

style globalstyle

  const globalFont : Font := Font.Verdana;
  const globalFontSize : Length := 0.8em;
  const globalTextColor : Color := #ff0000;
  const globalColor1 : Color := #cccccc;
  const globalColor2 : Color := #333333;
  const shadowColor 			 : Color := #ff0000;

  main()  {
    width:= 800px;
    align:= Align.left;
    background-color:= #ffffff;	
  }

  body {
    font := globalFont;
    font-size := globalFontSize;
    background-color:= #666666;
  }

  .subtitle {
    font-color:= Color.black;
    font-style:= FontStyle.italic;
    display := Display.inline;
  }
  
  .title {
    font-color:=  globalColor1;
    font-style:= FontStyle.bold;
    display := Display.inline;
  }
  
  div.title:hover {
    font-color := Color.red;
  }
  
  .maxwidth {
    width := 100%;
  }
  
  .maxheight {
    height:= 100%;
  }
  
style menuStyle

  menubar() {
    width := 100%;
    image := url("images/menubar_bg.png");
    image-repeat := Repeat.horizontal;
    height := 28px;
  }

  menu() {
  }  
  
  .menuheader {
    font-color := Color.red;
    border-color := globalColor1;
  }
  
  span.menuheader:hover {
    image := url("images/menubar_bg_hover.png");
    image-repeat := Repeat.horizontal;
  }
  
  .menuitem {
    border-color := globalColor1;
    font-color := Color.black;
    background-color := globalColor1;
  }
  
  span.menuitem:hover {
    border-color := Color.red;
    font-color := Color.red;
    background-color := globalColor2;
  }