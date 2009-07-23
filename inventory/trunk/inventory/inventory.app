module inventory/inventory

imports inventory/stock

entity Inventory {
	name		:: String (name)
	stock 	-> Set<ItemStock>
	itemSet 	-> ItemSet
	
	function getStock(name : String) : ItemStock {
		for(is:ItemStock in stock){
			if(is.item.name == name) {
				return is;
			}
		}
		return null;
	}

	function increaseStock(i : Item) {
		// TODO Check item against item set
		var itemStock := getStock(i.name);
		if(itemStock == null) {
			stock.add(
				ItemStock{ item := i, amount := 1 }
			);
		}
		else {
			itemStock.amount := itemStock.amount + 1;
		}
	}
	
	function decreaseStock(i : Item) {
		var itemStock := getStock(i.name);
		if(itemStock != null) {
			itemStock.amount := itemStock.amount - 1;
		}
		if(itemStock.amount == 0) {
			stock.remove(itemStock);
		}
	}
}

define page inventory(i:Inventory) {
	main()
	define body() {
		header{ output(i.name) }
		table{
			row{
				stock(i)
				inventoryMenu(i)
			}
		}
	}
}

define inventoryMenu(i:Inventory) {
	table {
		row{	navigate(incomingItems(i)) {"Incoming items"} 	}
		row{	navigate(editItemSet(i)) {"Edit item set"}		}
	}
}

