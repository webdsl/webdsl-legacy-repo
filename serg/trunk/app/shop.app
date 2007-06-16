module app/shop

sections domain.

  Shop {
    name     :: String (name)
    products -> List<Product>
    carts    -> List<Cart>
    first    -> Product
    last     -> Product
  }
  
  Product {
    name     :: String
    price    :: Int
    photo    :: Image
    shop     -> Shop
    previous -> Product
    next     -> Product
  }
  
  Cart {
    shopper  -> Person
    products -> List<Product>
  }
  
sections ideal template.

  define mainShopIdeal(shop : Shop) {
    main()
    define logo() {
      //image("http://farm1.static.flickr.com/72/162651609_026c457b83_s.jpg")
      image("http://farm1.static.flickr.com/72/162651609_026c457b83_t.jpg")
    }
    define sidebar() {
    }
    define menu() {
      list{ listitem{ navigate("Shop", viewShop(shop)) list {
        listitem { navigate("Edit", editShop(shop)) }
        listitem { form{ actionLink("Add Product", addProduct(shop)) } }
      } } }
      list{ listitem{ "Photo" list {
        photoMenu()
      } } }
    }
    action addProduct(shop : Shop) {
      var p : Product := Product { };
      p.shop         := shop;
      p.previous     := shop.last;
      p.next         := shop.first;
      shop.last.next := p;
      shop.last      := p;
      shop.products.add(p);
      shop.persist(); // these modifications should only be committed when product is saved
      return editProduct(p);
    }
  }
  
section template .

  define mainShop(shop : Shop) {
    div("outersidebar") {
      shopLogo()
      div("sidebar"){
        shopSidebar()
      }
    }
    div("outerbody") {
      div("menubar") { div("menu") {
        list{ listitem{ navigate("Shop", viewShop(shop)) list {
          listitem { navigate("Edit", editShop(shop)) }
          listitem { addPhoto(shop) }
        } } }
        list{ listitem{ "Photo" list {
          photoMenu()
        } } }
      } }
      body()
      footer()
    }
  }
  
  define shopLogo() {
    //image("http://farm1.static.flickr.com/72/162651609_026c457b83_s.jpg")
    image("http://farm1.static.flickr.com/72/162651609_026c457b83_t.jpg")
  }

  define addPhoto(shop : Shop) {
    form{ actionLink("Add Product", addProduct(shop)) }
    action addProduct(shop : Shop) {
      var p : Product := Product { };
      p.shop         := shop;
      p.previous     := shop.last;
      p.next         := shop.first;
      shop.last.next := p;
      shop.last      := p;
      shop.products.add(p);
      shop.persist(); // these modifications should only be committed when product is saved
      return editProduct(p);
    }
  }
    
  define photoMenu() { }
    
section shop page.

  define page viewShop(shop : Shop) {
    mainShop(shop)
    define body() {
      section{
        header{text(shop.name)}
        section{ 
          header{"Start Browsing"}
          par{navigate(viewProduct(shop.last)){image(shop.last.photo)}}
          par{navigate(viewProduct(shop.first)){image(shop.first.photo)}}
        }
        section {
          header{"Catalogue"}
          output(shop.products)
        }
      }
    }
  }
  
section photopage.

  define page viewProduct(product : Product) {
    mainShop(product.shop)
    define photoMenu() {
      listitem{ navigate("Edit", editProduct(product)) }
    }
    define body() {
      output(product.shop)
      section{
        header{text(product.name)}
        
        par{image(product.photo)}
        
        par{navigate("previous", viewProduct(product.previous)) " "
            navigate("next", viewProduct(product.next))}
        
        par{"Buy this photo for " text(product.price)}
      }
    }
  }
