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

    rules page editGrants(d:Document)
    {
      d.owner=securityContext.principal || securityContext.principal in d.grantingRights
    }

    rules page editGrantingRights(d:Document)
    {
      d.owner=securityContext.principal
    }
  }