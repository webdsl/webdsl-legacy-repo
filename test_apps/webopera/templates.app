module templates

section templates

define main() {
    title("Offerteprogramma van De Amersfoortse Verzekeringen")
    table [width := 100%] {
     row {
      column [colspan := "2", class := header] {
       header()
      }
     }
     row {
      column [class := sidebar, width := 200px] {
       sidebar()
      }
      column {
       body()
      }
     }
    }
}

define header() {}
define sidebar() {}
define body() {}