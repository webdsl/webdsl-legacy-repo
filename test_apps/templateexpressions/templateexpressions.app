application com.example.templateexpressions

description {
  This is an automatically generated description
}

imports templates

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
      output([x*x for(x : Int in [1, 2, 3, 4])])
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
