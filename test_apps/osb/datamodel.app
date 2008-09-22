module datamodel

section entities

  entity homeEntity {
    doelcontract -> Contract
    broncontract -> Contract
    jmscontract :: String
  }
  
  entity Contract {
    name :: String
  }
