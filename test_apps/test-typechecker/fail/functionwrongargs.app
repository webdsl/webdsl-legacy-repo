// Wrong arguments in call to getA

application test

section datamodel

globals {
  function getA(i : Int) : String {
    return "a";
  }
  
  function useA() {
    getA("Fiets");
  }
}
