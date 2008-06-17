// expression "no bool" should be of type bool

application test

section functions

globals {
  function do() {
    if ("no bool") {
      var a : String := "dinges";
    }
  }
}