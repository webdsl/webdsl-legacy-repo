module templates

section main template.

  define main() {
    top()

    div("body") {
      form() {
        body()
      }
    }

    footer()
  }

section basic page elements.

  define top() {
    div("header") {}
  }

  define footer() {
    div("footer") {
      div("left_footer") {
        navigate(home()) { "Home" }
      }
      div("right_footer") {
        text("PetClinic :: a WebDSL demonstration")
      }
    }
  }
