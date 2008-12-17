module ui

imports arbeid1

section pages

define page testpagina() {
    text("Deze test is geslaagd")
}

define page enterClient() {
    var cli : Client := Client {};
    derive createPage from cli
}

