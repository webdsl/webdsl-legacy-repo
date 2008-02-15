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
    predicate mayViewWeb(w : Web) {
      w != null && w.acl != null 
      && ((w.acl.view.length = 0) || memberOf(w.acl.view)) 
    }
    
    predicate mayEditWeb(w : Web) {
      w != null && w.acl != null 
      &&  memberOf(w.acl.edit) 
    }
    
    predicate mayViewTopic(t : Topic) {
      t != null 
      && ((t.acl != null && memberOf(t.acl.view)) 
          || ((t.acl = null || t.acl.view.length = 0)
              && mayViewWeb(t.web)))
    }
    
    predicate mayEditTopic(t : Topic) {
      t != null 
      && ((t.acl != null && memberOf(t.acl.edit)) 
          || ((t.acl = null || t.acl.edit.length = 0)
              && mayEditWeb(t.web)))
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
      mayViewWeb(web)
    }
    
    rules page webIndex(web : Web) {
      mayViewWeb(web)
    }
    
    rules page editWeb(web : Web) {
      mayEditWeb(web)
    }
    
    rules page newWeb() {
      isWebCreator()
    }
    
    rules page topic(topic : Topic) {
      mayViewTopic(topic)
    }
    
    rules page editTopic(topic : Topic) {
      mayEditTopic(topic)
    }
    
    rules page editTopicPermissions(topic : Topic) {
      mayEditACL(topic.acl, topic.web.acl)
    }
    
    rules page newTopic(web : Web) {
      mayEditWeb(web)
    }
    
    rules page topicDiff(d : TopicDiff) {
      mayViewTopic(d.topic)
    }
    
    rules page editTopicDiff(d : TopicDiff) {
      mayEditTopic(d.topic)
    }

    rules template thisTopicMenu(topic : Topic) {
      mayEditTopic(topic)
    }

    rules template thisWebMenu(web : Web) {
      mayViewWeb(web)
    }

    rules template thisWikiMenu() {
      isWebCreator()
    }

  }
