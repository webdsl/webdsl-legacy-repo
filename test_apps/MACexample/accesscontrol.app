module accesscontrol

section MAC AccessControl

  extend entity Document
  {
    classification :: Int
  }

  extend entity Mission
  {
    classification :: Int
  }

  extend entity User
  {
    clearance :: Int
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

    rules page createDocument()
    {
      securityContext.loggedIn
      rules action save(d:Document)
      {
        mayCreateDocument(securityContext.principal,d)
      }
    }

    predicate mayViewDocument (u:User, d:Document) {
      u.clearance >= d.classification
    }

    predicate mayCreateDocument (u:User, d:Document) {
      u.clearance <= d.classification
    }


    pointcut MissionViewing(m:Mission)
    {
      template navigateLinkListItemMission(m),
      page viewMission(m)
    }

    rules pointcut MissionViewing(m:Mission)
    {
      mayViewMission(securityContext.principal,m)
    }

    rules page createMission()
    {
      securityContext.loggedIn
      rules action save(m:Mission)
      {
        mayCreateMission(securityContext.principal,m)
      }
    }

    predicate mayViewMission (u:User, m:Mission) {
      u.clearance <= m.classification
    }

    predicate mayCreateMission(u:User, m:Mission) {
      u.clearance >= m.classification
    }

    rules template div("loggedIn")
    {
      securityContext.loggedIn
    }
  }