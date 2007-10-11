module app/flickr

description

  Store references to flickr images and corresponding pages.
  
end

section images.

  entity FlickrImage {
    photoid   :: String (primary)
    title     :: String (name)
    username  :: String
    photourl  :: URL
    square    :: URL
    thumbnail :: Image 
    small     :: URL
    medium    :: URL
    large     :: URL
    original  :: URL
    
    // http://farm2.static.flickr.com/1316/533515146_faa91c3e41_s.jpg
    // http://farm2.static.flickr.com/1316/533515146_faa91c3e41_t.jpg
    // http://farm2.static.flickr.com/1316/533515146_faa91c3e41_m.jpg
    // http://farm2.static.flickr.com/1316/533515146_faa91c3e41.jpg
    // http://farm2.static.flickr.com/1316/533515146_faa91c3e41_b.jpg
    // http://farm2.static.flickr.com/1316/533515146_faa91c3e41_o.jpg
  }
  

  