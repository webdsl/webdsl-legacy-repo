module widgets

section templates

define template collapsePanel(header: String, collapsed: Bool) {
  ">" output(header)
  spacer
  container[style:= "padding-left: 20px"] {
    elements()
  }
}