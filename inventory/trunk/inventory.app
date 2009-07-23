application com.example.inventory

imports access
imports login
imports style/main
imports frame/main
imports user/main
imports inventory/main

define page home() {
	main()
	define body() {
		inventoryList()
	}
}
