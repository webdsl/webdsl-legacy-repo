module layout

layout

  template main() {
    top();
    topmenu();
    body();
    footer();
  }
  
  template top() {
    logo_area: [ logos | text ];
  }
  
  template footer() {
    f: [ footer_links | footer_text ];
  }
  
