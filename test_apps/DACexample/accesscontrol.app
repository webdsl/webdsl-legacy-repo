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

    predicate mayChangeViewEditGrants (u:User, d:Document) {
      d.owner = u || u in d.grantingRights
    }

    predicate mayChangeGrantingRights (u:User, d:Document) {
      d.owner = u
    }


    principal is User with credentials name

    rules page home()
    {
      true
    }
    rules template sidebar()
    {
      true
    }


    rules page viewDocument(d:Document)
    {
      mayViewDocument(securityContext.principal,d)
    }

    rules page createDocument()
    {
      securityContext.loggedIn
    }

    rules page editDocument(d:Document)
    {
      mayEditDocument(securityContext.principal,d)
    }

    rules page editViewEditGrants(d:Document)
    {
      mayChangeViewEditGrants(securityContext.principal,d)
    }

    rules page editGrantingRights(d:Document)
    {
      mayChangeGrantingRights(securityContext.principal,d)
    }
  }