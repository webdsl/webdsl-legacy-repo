module app/forum

description

  A forum is a collection of discussion threads.
  
end

section domain.

  Forum { 
    title       :: String (name)
    discussions -> List<Discussion>  // note : test what happens if this is also composite (breaks inlining)
  }
  
  Discussion {
    topic    :: String (name)
    author   -> Person
    posted   :: Date
    forum    :: Forum (back)
    text     :: Text
    replies  <> List<Reply> (inline)
  }
  
  Reply {
    subject    :: String (name)
    author     -> Person  
    posted     :: Date
    discussion -> Discussion (back)
    text       :: Text
  }
    
  Post { // legacy
    subject    :: String (name)
    author     -> Person
    posted     :: Date
    discussion -> Discussion (back)
    text       :: Text
  }
  
note

  @acl Access control requirements: 
  - the author of a reply is Person associated to the logged in User
    this may only be changed by an admin
  - a reply may only be edited by its author
  - a reply may be deleted by its author or by the author of the discussion

end

note 

  Some forums have a notion of nested threads, i.e., replies can be replies
  to an earlier reply.

end
  
section pages.
  
  define page viewDiscussion(discussion : Discussion) {
    main()
  
    title{text(discussion.forum.name) " : " text(discussion.name)}
  
    define body() {
    output(discussion.forum) " Forum"
    
    section{ 
      header{text(discussion.name)}
      "by " output(discussion.author) " posted " output(discussion.posted)
      
      par{output(discussion.text)}
      
      for ( reply : Reply in discussion.replies ) {
        div("reply") {
          section{
            header{output(reply.name)}
            "by " output(reply.author) " posted " output(reply.posted)
          
            par{output(reply.text)}
          
            par{ 
              form{
                navigate("Edit", editReply(reply))
                " | "
                actionLink("Delete", delete(reply))
                action delete(reply : Reply) {
                  discussion.replies.remove(reply);
                }
              }
            }
          }
        }
      }
      
      section { 
        header{"Reply"}
        
        var newReply : Reply := Reply { discussion := discussion };
        
        form { 
          table {
            row{ "Subject: " input(newReply.subject) }
            row{ "Author: " input(newReply.author) } // should be the username of the logged in user
            row{ "" input(newReply.text) }
          }
          action("Post", post())
          action post() {
            newReply.save();
            discussion.replies.add(newReply);
            newReply := Reply { discussion := discussion };
          }
        }
      }
    }
    }
  }


  
  

