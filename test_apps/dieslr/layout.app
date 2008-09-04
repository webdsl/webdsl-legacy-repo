module layout

layout

  template main() {
    top();
    topmenu();
    mainbody: [ sidebar() | body() ];
    footer();
  }
  
  template top() {
    logo_area: [ logos | text ];
  }
  
  template footer() {
    f: [ footer_links | footer_text ];
  }
  
