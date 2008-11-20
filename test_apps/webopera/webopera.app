application webopera

imports templates
imports datamodel
//imports layout
imports style
//imports init

section pages



var pr1 : Product := Product{ name := "Wouter" prodcategory := cat1 };


define page home() {
    main()
    define body() {
        table[width := 100%, class := body] {
          row { 
           column { 
            group("Arbeidsongeschiktheid") {
                table {
                < | image("/images/arbeidsongeschiktheid.png")
                  //| for (p : Product where (p.prodcategory.name == "Arbeidsongeschiktheid")) {
                  //      output(p.prodname)
                  //  }
                    text("Dit is tekst")
          
                    
                    //output(p)
                    //input(p)
 navigate("product",product(pr1))

                    navigatebutton(editProduct(pr1), "Edit product")
                    navigatebutton(viewDileur(), "Dileur")
                >
                }
            }
           }
          }
          row {
           column { 
            group("Ziektekosten") {
                image("/images/ziektekosten.png")                
            }
           }
          }
          row {
           column {
            group("Pensioenen en lijfrente") {
                image("/images/pensioenen.png")
            }
           }
          }
        }
    }
}


define page productCategory(p : ProductCategory) {
    derive viewPage from p
}

define page product(p : Product) {
    derive viewPage from p
}


define page editProduct(p : Product) {
    derive editPage from p
}

define page viewDileur() {
    var c : Dummy;
    derive createPage from c for (lijstje)
}