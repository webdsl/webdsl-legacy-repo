module templates

section templates

define main() {
    title("Offerteprogramma van De Amersfoortse Verzekeringen")    
    table [width := 100%, height := 100%] {
     row [height := 50px, class := header] {
      column [colspan := "2", class := header] {
       header()
      }
     }
     row {
      column [class := sidebar, width := 200px] {
       sidebar()
      }
      column [class := body] {
       body()
      }
     }
    }
}

define header() {}
define sidebar() {}
define body() {}