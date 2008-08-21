module layout

layout

  template main() {
    top();
    topmenubar();
    body: [ sidebar() | mainbody() ];
    footer();
  }
  
  template top() {
    logo_area: [ logos | text ];
  }
  
  template footer() {
    f: [ footer_links | footer_text ];
  }
