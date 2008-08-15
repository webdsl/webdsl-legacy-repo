application layouttest

description {
  This is an automatically generated description
}

imports templates
imports style

section pages

define page home() {
  title{ "Layout test application : Home" }
  
  main()
}

define page leftmenu() {
  
  main()
  
  define mainbody() {
    section{ "Hello world! Leftmenu mainbody" }
  }
  
}

layout

  template main() {
    top();
    topmenubar();
    body: [ sidebar() | mainbody() ];
    footer();
  }
  
  template top() {
    bla: [ logos | text ];
  }
  

  template mainbody() {
    main_body: [ main_mainbody() | main_sidebar() ];
  }
  
  template footer() {
    f: [ footer_links | footer_text ];
  }
