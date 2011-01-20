application slimbox

  entity Photo {
    img :: Image

    thumbnail :: Image
    resizedThumbnail :: Bool //workaround for http://yellowgrass.org/issue/WebDSL/312
    function cloneThumbnail(){
      thumbnail := img.clone();
    }
    function resizeThumbnail(){
      if(!resizedThumbnail){
        thumbnail.resize(100,100);
        resizedThumbnail := true;
      }
    }
  }
  
  entity PhotoAlbum{
    photos -> List<Photo>
    function initThumbnails(){
      for(p:Photo in photos){
        p.resizeThumbnail();
      }
    }
  }
  
  define showPhotos(pa: PhotoAlbum){
    init{ pa.initThumbnails(); }
    var nrEmptyThumbnails := 4 - pa.photos.length;
    
    <div class="thumbnails">
      for(p:Photo in pa.photos){ 
        <div class="thumbnail">
          showLightboxImage(p)
        </div>
      }
      if(nrEmptyThumbnails > 0) {
        for(i : Int from 0 to nrEmptyThumbnails) {
          emptyThumbnail()
        }
      }
     </div>
  }
  
  define template emptyThumbnail() {
    includeCSS("elements.css")
    <div class="thumbnail emptyThumbnail">rawoutput{"&nbsp;"}</div>
  }
  
  
  define photoUpload(pa: PhotoAlbum){
    var photo : Photo := Photo{}
    form{
      input(photo.img)
      submit action{pa.photos.add(photo); photo.cloneThumbnail(); } {"add photo"}
    }
  }

  //uses slimbox2 (jquery implementation of lightbox)
  define showLightboxImage(p:Photo){
    includeJS("https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js")
    includeJS("slimbox2.js")
    includeCSS("slimbox2.css")
    
    var templateid := getUniqueTemplateId();
    
    <a id=templateid+"alink" rel="lightbox-photo">output(p.thumbnail)/*[height="100px"]*/</a>
  
    // use the generated action from built-in outputimage
    // @TODO replace with proper template instead of js hassle
    // @TODO this still loads the bigger image as well
    output(p.img)[style="display:none;", imgattr=""+templateid]
    <script>
      var theimage = $("*[imgattr=~templateid]");
      var imgsrc = theimage.attr("src");
      $("#"+"~templateid"+"alink").attr("href",imgsrc);
    </script>
    
    action showImage(p:Photo){
      p.img.download();
    }
  }
  
  var globalpa := PhotoAlbum{}
  
  define page root(){
    showPhotos(globalpa)
    photoUpload(globalpa)
  }
  