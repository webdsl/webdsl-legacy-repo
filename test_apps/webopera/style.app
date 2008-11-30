module style


style globalStyle
    .body {
        background-color    := #1f78b7;
        font                := Font.sans-serif;
        font-size           := 10pt;
        font-color          := Color.white;
        vertical-align      := VerticalAlign.top;
        padding             := 15px;
    }

    .sidebar {
        background-color    := #6599cc;
        vertical-align      := VerticalAlign.top;
    }

    .header {
        background-color    := #6599cc;
//        vertical-align      := VerticalAlign.bottom;
//        align               := Align.left;
//        width               := 100%;
    }

    .productlist {
        vertical-align      := VerticalAlign.top;
        padding             := 10px;
    }

    navigate() {
        font-color          := Color.white;
        font-size           := 10pt;
        font                := Font.sans-serif;
    }

    groupitem() {
        font                := Font.sans-serif;
        font-size           := 10pt;
        font-color          := Color.white;
    }

    .sideMenu {
        font                := Font.sans-serif;
        font-size           := 10pt;
        font-color          := Color.white;
        width               := 100%;
        padding-left        := 10px;
    }

    .selectedMenuItem {
        font                := Font.sans-serif;
        font-size           := 10pt;
        font-color          := Color.white;
        background-color    := #1f78b7;
        vertical-align      := VerticalAlign.middle;
        height              := 30px;
    }

    menuheader() {
        font                := Font.sans-serif;
        font-size           := 10pt;
        font-color          := Color.white;
    }

    span.menuheader:hover {
        background-color    := #1f78b7;
    }        