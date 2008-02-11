module accesscontrol

section MAC AccessControl

  extend session securityContext{
    clearance :: Int
  }
 

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
    predicate mayViewDocument (d:Document) {
      //loggedImn check should be automatic here too
      securityContext.loggedIn&&securityContext.clearance>-1&&
      securityContext.clearance >= d.classification
    }

    predicate mayCreateDocument (d:Document) {
      //loggedImn check should be automatic here too
      securityContext.loggedIn&&securityContext.clearance>-1&&
      securityContext.clearance <= d.classification
    }
    
    //predicate mayCreateDocumentClass (u:User, cl:Int) {
    //  u.clearance <= cl
    //}

    predicate mayViewMission (u:User, m:Mission) {
      u.clearance <= m.classification
    }

    predicate mayCreateMission(u:User, m:Mission) {
      u.clearance >= m.classification
    }


    principal is User with credentials name

    pointcut openSections()
    {
      page home(),
      template sidebar()
    }

    rules pointcut openSections()
    {
      true
    }

    rules page viewDocument(d:Document)
    {
      mayViewDocument(d)
    }

    rules page createDocument()
    {
      securityContext.loggedIn
      rules action save(d:Document)
      {
        mayCreateDocument(d)
      }
    }
    
    rules page createClassificationDocument(d:Document){
      mayCreateDocument(d)
    }
    
    //rules page createClassificationDocument(class:Int){
    //  mayCreateDocumentClass(securityContext.principal,class)
    //}

    rules page viewMission(m:Mission)
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
    
    
    
    rules template sidebar()
    {
      true
      rules action activateCL(clear:Int)
      {
        securityContext.principal.clearance >= clear
      }
    }
  }