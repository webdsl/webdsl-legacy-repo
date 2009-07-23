module inventory/stock

define stock(i:Inventory){
	group("Stock"){
    		table{
			for(is:ItemStock in i.stock order by is.item.name){
				row{
					output(is.item.name)
					output(is.amount)
					form { action("-", decreaseStock(is.item)) }
				}
			}
		}
	}
	action decreaseStock(it:Item){
		i.decreaseStock(it);
		return inventory(i);
	}
}

define page incomingItems(i:Inventory) {
	main()
	define body() {
		navigate(inventory(i)) {"<< return to inventory"}
		
		header{ "Select incoming item" }
		table{
			for(it:Item in i.itemSet.items order by it.name){
				row{
					output(it.name) form{ action("select",incoming(it)) }
				}
			}
		}
	}
	action incoming(it:Item){
		i.increaseStock(it);
		message(it.name + " added to inventory");
		return inventory(i);
	}
}
