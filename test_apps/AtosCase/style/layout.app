module layout

layout

  main() {
    top();
    topmenu();
    body();
    footer();
  }
  
  top() {
    logo_area: [ logos | text ];
  }
  
  footer() {
    f: [ footer_links | footer_text ];
  }
  
