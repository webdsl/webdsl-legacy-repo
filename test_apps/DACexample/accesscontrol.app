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
    principal is User with credentials name

    rules page home()
    {
      true
    }

    rules template sidebar()
    {
      true
    }

    pointcut documentViewing(d:Document)
    {
      template navigateLinkListItemDoc(d),
      page viewDocument(d)
    }

    rules pointcut documentViewing(d:Document)
    {
      mayViewDocument(securityContext.principal,d)
    }

    predicate mayViewDocument (u:User, d:Document) {
      d.owner = u || u in d.viewAccess
    }

    rules page createDocument()
    {
      securityContext.loggedIn
    }

    pointcut documentEditing(d:Document)
    {
      template navigateLinkListItemEditDoc(d),
      page editDocument(d)
    }

    rules pointcut documentEditing(d:Document)
    {
      mayEditDocument(securityContext.principal,d)
    }

    predicate mayEditDocument (u:User, d:Document) {
      d.owner = u || u in d.editAccess
    }


    rules template div("loggedIn")
    {
      securityContext.loggedIn
    }


    pointcut grantsedit(d:Document)
    {
      template navigateLinkListItemGrants(d),
      page editGrants(d)
    }

    rules pointcut grantsedit(d:Document)
    {
      d.owner=securityContext.principal || securityContext.principal in d.grantingRights
    }


    pointcut grantingrightssedit(d:Document)
    {
      template navigateLinkListItemGrantingRights(d),
      page editGrantingRights(d)
    }

    rules pointcut grantingrightssedit (d:Document)
    {
      d.owner=securityContext.principal
    }
  }