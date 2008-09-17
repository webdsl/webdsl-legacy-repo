module layout

layout

  template main() {
    top();
    topmenu();
    mainbody: [ sidebar() | body() ];
    footer();
  }
  
  template topmenu() {
    menu: [ mainNavigation() | adminNavigation() ];
  }

style mainStyle

  const houseColor : Color := #70630d;

  template top() > image() {
    align := Align.right;
  }

  template mainNavigation() {
    align := Align.left;
  }
  
  template adminNavigation() {
    align := Align.right;
  }

  template top() {
    width := 100%;
  }
  
  template topmenu() {
    width := 100%;
    background-color := houseColor;
    padding-top := 1em;
    padding-bottom := 1em;
  }

  
  template sidebar() {
    width := 20em;
    margin-left := 1em;
    margin-right := 1em;
    margin-top := 3em;
    margin-bottom := 3em;
  }
  
  template body() {
    margin-left := 1em;
    margin-right := 1em;
    margin-top := sidebar().margin-top;
    margin-bottom := sidebar().margin-bottom;
  }

  template footer() {
    background-color := houseColor;
    padding-top := topmenu().padding-top;
    padding-bottom := topmenu().padding-bottom;
    width := topmenu().width;
  }
