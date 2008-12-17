application webopera

imports datamodel
imports init
imports style
imports templates
imports ui

// import products
imports arbeid1
//imports Dil12

section test

 entity TestDing {
    name :: String
    dinges -> List<String>
 }

define page home() {
    main()
}