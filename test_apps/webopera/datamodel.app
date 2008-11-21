module datamodel

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
        email :: Email
        accountnumber :: String
        reference :: String
    }
        
section quotations

    entity Quotation {
        number :: Int
        client -> Client
    }  
      

section products

    enum ProductCategory {
        Arbeid("Arbeidsongeschiktheid"),
        Ziekte("Ziektekostenverzekering"),
        Leven("Pensioenen en lijfrente")
    }

    entity Arbeid1 {
        name :: String
        category -> ProductCategory
        attrib1 :: String
        attrib2 :: Int
    }

    entity Pensioen1 {
        name :: String
    }

    entity Ziektekosten1 {
        name :: String
    }