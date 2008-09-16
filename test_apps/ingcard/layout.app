module layout

layout

  template main() {
    top();
    mainbody: [ sidebar() | body() ];
  }
  
  template customerDetails(c : Customer) {
    float { group() };
    list();
  }

style mainStyle

  template sidebar() {
    width := 8em;
    height := 500px;
    background-color := #fdcdaa;
  }

  template top() {
    width := 100%;
    height := 78px;
    background-color := #feece0;
  }

  template body() {
    margin-top := 1em;
    margin-left := 1em;
  }
