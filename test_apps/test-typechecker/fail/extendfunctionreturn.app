application test

section datamodel

globals {
  function getA(i : Int) : String {
    return "a";
  }
  
  extend function getA(i : Int) : String {
    return "b";
  }
}
