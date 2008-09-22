module layout

layout

  main() {
    top();
    topmenu();
    mainbody: [ sidebar() | body() ];
    footer();
  }
  
  topmenu() {
    menu: [ mainNavigation() | adminNavigation() ];
  }

style mainStyle

  const houseColor : Color := #70630d;

  top() > image() {
    align := Align.right;
  }

  mainNavigation() {
    align := Align.left;
  }
  
  adminNavigation() {
    align := Align.right;
  }

  top() {
    width := 100%;
  }
  
  topmenu() {
    width := 100%;
    background-color := houseColor;
    padding-top := 1em;
    padding-bottom := 1em;
  }

  
  sidebar() {
    width := 20em;
    margin-left := 1em;
    margin-right := 1em;
    margin-top := 3em;
    margin-bottom := 3em;
  }
  
  body() {
    margin-left := 1em;
    margin-right := 1em;
    margin-top := sidebar().margin-top;
    margin-bottom := sidebar().margin-bottom;
  }

  footer() {
    background-color := houseColor;
    padding-top := topmenu().padding-top;
    padding-bottom := topmenu().padding-bottom;
    width := topmenu().width;
    height := 1em;
  }
