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
    }

    .header {
        background-color    := #6599cc;
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

