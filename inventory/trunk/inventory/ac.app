module inventory/ac

access control rules
  
	rule page inventory(i:Inventory){ 
		loggedIn()
	}
	
	rule page createInventory(){ 
		loggedIn()
	}
	
	rule page editItemSet(i:Inventory){ 
		loggedIn()
	}
	
	rule page incomingItems(i:Inventory){ 
		loggedIn()
	}
	
	rule page item(i:Item){
		false
	}
	
	rule page editItemStock(is:ItemStock, i:Inventory) {
		loggedIn()
	}
