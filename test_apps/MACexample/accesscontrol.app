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
    predicate mayViewDocument (u:User, d:Document) {
      u.clearance >= d.classification
    }

    predicate mayCreateDocument (u:User, d:Document) {
      u.clearance <= d.classification
    }

    predicate mayViewMission (u:User, m:Mission) {
      u.clearance <= m.classification
    }

    predicate mayCreateMission(u:User, m:Mission) {
      u.clearance >= m.classification
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
      rules action save(d:Document)
      {
        mayCreateDocument(securityContext.principal,d)
      }
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


    pointcut MissionViewing(m:Mission)
    {
      template navigateLinkListItemMission(m),
      page viewMission(m)
    }

    pointcut documentViewing(d:Document)
    {
      template navigateLinkListItemDoc(d),
      page viewDocument(d)
    }

  }