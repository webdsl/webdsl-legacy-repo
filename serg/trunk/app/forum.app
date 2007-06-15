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
    topic  :: String (name)
    author -> Person
    posted :: Date
    forum  :: Forum (back)
    text   :: Text
    posts  <> List<Post> (inline)
  }
  
  Post {
    subject    :: String (name)
    author     -> Person
    posted     :: Date
    discussion -> Discussion (back)
    text       :: Text
  }
  
section pages.
  
  
  define page viewDiscussionNew(discussion : Discussion) {
  
  }


  
  

