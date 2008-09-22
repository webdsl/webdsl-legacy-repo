module layout

layout

  main() {
    top();
    mainbody: [ sidebar() | body() ];
  }
  
  customerDetails(c : Customer) {
    float { group() };
    list();
  }

style mainStyle

  sidebar() {
    width := 8em;
    height := 500px;
    background-color := #fdcdaa;
  }

  top() {
    width := 100%;
    height := 78px;
    background-color := #feece0;
  }

  body() {
    margin-top := 1em;
    margin-left := 1em;
  }
