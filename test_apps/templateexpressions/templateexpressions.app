application com.example.templateexpressions

description {
  This is an automatically generated description
}

imports templates

section hello

entity Test {
  function bla() : Int {
    var l : List<Int> := [4, 3, 2, 1];
    return 8;
  }
}

section pages

define page home() {
  main()
  define body() {
    section {
      header{"Count to 4"}
      for(i : Int in [1, 2, 3, 4]) {
        output(i)
      }
      header{"Count to 4"}
      output([1, 2, 3, 4])
      header{"Count to 4^2"}
      output([x*x | x : Int in [1, 2, 3, 4]])
      header{"Equivalence"}
      for(i : Int in [1, 2, 3, 4, 5] where i % 2 = 0) {
        output(i)
      }
      header{"Is the same as"}
      for(i : Int in [i_0 for(i_0: Int in [1, 2, 3, 4, 5] where i_0 % 2 = 0)]) {
        output(i)
      }
      header{"Print some numbers"}
      output(8)
      output(2*4)
    }
  }
}
