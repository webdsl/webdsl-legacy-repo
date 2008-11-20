module datamodel

section products
   
    entity ProductCategory {
        name :: String 
    }

    
    var cat1 : ProductCategory := ProductCategory { name := "Arbeidsongeschiktheid" };
    var cat2 : ProductCategory := ProductCategory { name := "Ziektekosten" };
    var cat3 : ProductCategory := ProductCategory { name := "Pensioenen en lijfrente" };

    var HerkomstKPS_option1 : HerkomstKPS_option := HerkomstKPS_option { name := "Gouden handdruk"};
    var HerkomstKPS_option2 : HerkomstKPS_option := HerkomstKPS_option { name := "Lijfrente"};    
    

    entity Product {
        name :: String 
        prodcategory -> ProductCategory
    }

    entity Dileur : Product {
        name :: String := "Directe lijfrente"
        prodcategory -> ProductCategory := ProductCategory { name := "Pensioenen en lijfrente" }
        herkomstKPS -> HerkomstKPS_option
    }

    entity HerkomstKPS_option {
        name :: String
    }

    entity Blaprod : Product {
        name :: String := "Arbeidsdinges"
        prodcategory -> ProductCategory := ProductCategory { name := "Arbeidsongeschiktheid" }
    }

    entity Dummy {
        name :: String := "Wouter" 
        lijstje -> ProductCategory
    }

section clients

    entity Client {
        surname :: String
        prefix :: String
        initials :: String
        title :: String
        gender :: String
        birthdate :: Date
        address :: String
        number :: Int
        addition :: String
        postalcode :: String
        city :: String
        telephone :: String
        fax :: String
        email :: String
        accountnumber :: String
        reference :: String
    }
        
section quotations

    entity Quotation {
        number :: Int
        client -> Client
        product -> Product
    }  
      