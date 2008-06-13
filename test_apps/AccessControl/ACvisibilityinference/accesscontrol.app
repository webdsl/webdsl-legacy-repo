module accesscontrol

section DAC AccessControl

  extend entity Document
  {
    owner :: User (inverse=User.ownedDocuments)
    viewAccess :: Set<User>
    editAccess :: Set<User>
    grantingRights :: Set<User>
  }

  extend entity User
  {
    ownedDocuments -> Set<Document> //(inverse=Document.owner)
  }

  access control rules
  {
    predicate mayViewDocument (u:User, d:Document) {
      d.owner = u || u in d.viewAccess
    }

    predicate mayEditDocument (u:User, d:Document) {
      d.owner = u || u in d.editAccess
    }


    principal is User with credentials name

    rules page home()
    {
      true
      rules action save(d:Document)
      {
        mayEditDocument(securityContext.principal,d)
      }
    }
    
    rules page formwithtemplates()
    {
      true
    }
    rules template saveaction(d:Document)
    {
      mayEditDocument(securityContext.principal,d)
    }
    
    rules page formwithfor()
    {
      true
      rules action save(d:Document)
      {
        mayEditDocument(securityContext.principal,d)
      }
    }  
    rules page formwithforall()
    {
      true
      rules action save(d:Document)
      {
        mayEditDocument(securityContext.principal,d)
      }
    }
    rules page formwithfunctioncall()
    {
      true
      //action simply allowed, function will do the check
    }
    
    rules template sidebar()
    {
      true
    }
    
    rules page document(d:Document)
    {
      mayViewDocument(securityContext.principal,d)
    }
    
    rules page editDocument(d:Document)
    {
      mayEditDocument(securityContext.principal,d)
    }
    
    rules template editRowsDocument(d:Document)
    {
      mayEditDocument(securityContext.principal,d)
    }
    
    rules function aSaveFunction(d:Document)
    {
      mayEditDocument(securityContext.principal,d)
    }
    rules function dummyfunction(d:Document)
    {
      mayEditDocument(securityContext.principal,d)
    }
    
    
    rules page nogo()
    {
      false
    }
    rules page go()
    {
      true
    }    
    rules page stress()
    {
      true
    }
  } 