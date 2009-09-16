application imageresize

description {
  A simple app that uses CRUD page generation for managing a Person entity
}

section pages

define page root() {
  header { "All pictures" }
  for(pic : Picture) {
    navigate(editPicture(pic)) { output(pic.pic) }
  }
  header { "Upload picture!" }
  form {
    var p : Picture := Picture{};

    input(p.pic)

    action("Add", add())

    action add() {
      p.save();
    }
  }
}

define page editPicture(p : Picture) {
  header { output(p.pic.fileName()) }

  navigate(root()) { "Home" }

  par { output(p.pic) }

  form {
    header { "Resize" }
    var width : Int := p.pic.getWidth();
    var height : Int := p.pic.getHeight();

    "New max width: " input(width)
    "New max height: " input(height)

    action("Resize", resize())

    action resize() {
      p.pic.resize(width, height);
    }
  }

  form {
    header { "Crop" }
    var x : Int := 0;
    var y : Int := 0;
    var cropWidth : Int := p.pic.getWidth();
    var cropHeight : Int := p.pic.getHeight();

    "X: " input(x)
    "Y: " input(y)
    "Width: " input(cropWidth)
    "Height: " input(cropHeight)

    action("Crop", crop())

    action crop() {
      p.pic.crop(x, y, cropWidth, cropHeight);
    }
  }

  form {
    header { "Duplicate" }
    action("Duplicate", duplicate())

    action duplicate() {
      var newP := Picture{};
      newP.pic := p.pic.clone();
      newP.save();
      return editPicture(newP);
    }
  }
}

entity Picture {
  pic :: Image
}

