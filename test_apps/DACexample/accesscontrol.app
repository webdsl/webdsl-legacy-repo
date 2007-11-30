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
    rules template div("loggedIn")
    {
      securityContext.loggedIn
    }


    rules pointcut documentViewing(d:Document)
    {
      mayViewDocument(securityContext.principal,d)
    }

    rules page createDocument()
    {
      securityContext.loggedIn
    }

    rules pointcut documentEditing(d:Document)
    {
      mayEditDocument(securityContext.principal,d)
    }

    rules pointcut grantsedit(d:Document)
    {
      d.owner=securityContext.principal || securityContext.principal in d.grantingRights
    }

    rules pointcut grantingrightssedit (d:Document)
    {
      d.owner=securityContext.principal
    }


    pointcut documentEditing(d:Document)
    {
      template navigateLinkListItemEditDoc(d),
      page editDocument(d)
    }

    pointcut grantsedit(d:Document)
    {
      template navigateLinkListItemGrants(d),
      page editGrants(d)
    }

    pointcut documentViewing(d:Document)
    {
      template navigateLinkListItemDoc(d),
      page viewDocument(d)
    }

    pointcut grantingrightssedit(d:Document)
    {
      template navigateLinkListItemGrantingRights(d),
      page editGrantingRights(d)
    }
  }