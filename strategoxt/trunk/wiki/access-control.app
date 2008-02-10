module wiki/access-control

section ac data

  extend entity Web {
    acl -> ACL
  }
  
  extend entity Topic {
    acl -> ACL
  }
  
section acr

  access control rules
  {
    predicate mayViewWeb(w : Web, user : User) {
      (w.acl.view.length = 0) || memberOf(w.acl.view, user)
    }
    
    predicate mayEditWeb(w : Web, user : User) {
      memberOf(w.acl.edit, user)
    }
    
    predicate mayViewTopic(t : Topic, user : User) {
      memberOf(t.acl.view, user) 
      || (t.acl.view.length = 0) && mayViewWeb(t.web, user)
    }
    
    predicate mayEditTopic(t : Topic, user : User) {
      memberOf(t.acl.edit, user) 
      || (t.acl.edit.length = 0) && mayEditWeb(t.web, user)
    }
  }
  
  access control rules
  {
    rules page home() {
      true
    }
  
    rules page wiki() {
      true
    }

    rules page wikiIndex() {
      true
    }
    
    rules page web(web : Web) {
      mayViewWeb(web, securityContext.principal)
    }
    
    rules page webIndex(web : Web) {
      mayViewWeb(web, securityContext.principal)
    }
    
    rules page editWeb(web : Web) {
      mayEditWeb(web, securityContext.principal)
    }
    
    rules page newWeb() {
      webCreateGroup in securityContext.principal.groups
    }
    
    rules page topic(topic : Topic) {
      mayViewTopic(topic, securityContext.principal)
    }
    
    rules page editTopic(topic : Topic) {
      mayEditTopic(topic,  securityContext.principal)
    }
    
    rules page newTopic(web : Web) {
      mayEditWeb(web,  securityContext.principal)
    }
    
    rules page topicDiff(d : TopicDiff) {
      mayViewTopic(d.topic,  securityContext.principal)
    }
    
    rules page editTopicDiff(d : TopicDiff) {
      mayEditTopic(d.topic,  securityContext.principal)
    }

    rules template topicOperationsMenuItems(topic : Topic) {
      mayEditTopic(topic,  securityContext.principal)
    }

  }
