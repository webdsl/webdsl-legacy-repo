module style


style globalConstants
    const canvasWidth : Length := 700px;


style globalStyle
    page home() {
        width               := canvasWidth;
        background-color    := Color.black;
    }

    main() {
        font                := Font.sans-serif;
        font-size           := 10pt;
    }    

    main() > .mainbody {
        width               := canvasWidth;
    }

    .body {
        background-color    := #1f78b7;
        font                := Font.sans-serif;
        font-size           := 10pt;
        font-color          := Color.white;
        width               := 100%;
        height              := 100%;
        border-width        := 1px;
        border-color        := Color.black;
        border-style        := BorderStyle.solid;
    }

    .sidebar {
        background-color    := #6599cc;
        width               := 200px;
        height              := 100%;
    }

    .header {
        background-color    := #6599cc;
        height              := 100px;
        width               := 100%;      
        padding             := 0px;
        spacing             := 0px; 
    }

    .lightcolor {
        background-color    := #6599cc;
    }

    .darkcolor {
        background-color    := #1f78b7;
    }