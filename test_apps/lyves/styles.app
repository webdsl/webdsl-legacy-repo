module styles

style globalstyle

  const globalFont : Font := Font.Verdana;
  const globalFontSize : Length := 0.8em;
  const globalTextColor : Color := #ff0000;
  const globalColor1 : Color := #cccccc;
  const globalColor2 : Color := #333333;
  const shadowColor 			 : Color := #ff0000;

  page home() {
      width:= 800px;
  }
  
  loginlogout() {
  		align := Align.right;
  }

  main()  {
    background-color:= #ffffff;	
    width:= 800px;
  }
  
  #body {
  	image := url("images/blue_bg.png");
  	image-repeat := Repeat.horizontal;
  }

	.error {
		font-color := Color.red;
		text-align := Align.left;
	}

  body {
    font := globalFont;
    font-size := globalFontSize;
    background-color:= #333333;
  }

	group() >> legend {
		font-color:=Color.navy;
		font-style:=FontStyle.bold;
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
    font-color := globalTextColor;
  }
  
  .topalign {
	  vertical-align:=VerticalAlign.top;
	}
  
  .maxwidth {
    width := 100%;
  }
  
  .maxheight {
    height:= 100%;
  }
  
  .whitebg {
    background-color:= Color.white;
  }
