module layout

layout

  main() {
    top();
    mainbody: [ sidebar() | body() ];
    footer();
  }
  
  /*
  top() {
    logo_area: [ logos | text ];
  }
  */
  
  footer() {
    f: [ footer_links | footer_text ];
  }
  
