module layout

layout

  page home() {
    top();
    menu: [ topmenu() | quickadd() | quickfind() ];
    main();
    footer();
  }
  
  top() {
    logo_area: [ logos | text ];
  }
  
  footer() {
    f: [ footer_links | footer_text ];
  }
  
  main() {
    main_contents: [ folders() | notes() | details() ];
  }
  
  