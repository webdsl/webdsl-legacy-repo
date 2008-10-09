module styles

style globalstyle

  const globalFont : Font := Font.Arial;
  const globalFontSize : Length := 0.8em;
  const globalTextColor : Color := #ff0000;
  const globalColor1 : Color := #cccccc;
  const globalColor2 : Color := #333333;

  page home() >> topheader() {
    font := globalFont;
    font-size := 1em;
    font-color:= globalColor1;	
  }

  .mystyle {
    font-color:= globalTextColor;
  }
  