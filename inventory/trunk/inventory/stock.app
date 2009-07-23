module inventory/stock

entity ItemStock {
	item			-> Item
	amount 		:: Int
	minimum		:: Int
	incrBatchSize	:: Int
	decrBatchSize	:: Int
}

define stock(i:Inventory){
	group("Stock"){
		table{
			for(is:ItemStock in i.stock order by is.item.name){
				row{
					output(is.item.name)
					output(is.amount)
					if(is.amount < is.minimum) {"!"}
					else {" "}
					form { action("+", increaseStock(is.item)) }
					form { action("-", decreaseStock(is.item)) }
					navigate(editItemStock(is,i)){">"}
				}
			}
		}
	}
	action decreaseStock(it:Item){
		i.decreaseStock(it);
		return inventory(i);
	}
	action increaseStock(it:Item){
		i.increaseStock(it);
		return inventory(i);
	}
}

define page editItemStock(is:ItemStock, i:Inventory) {
	main()
	define body() {
		header{output(is.item.name) " bounds"}
		form{
			table{
				row{	label("Minimum amount"){input(is.minimum)}			}
				row{	label("Increment batch size"){input(is.incrBatchSize)}	}
				row{	label("Decrement batch size"){input(is.decrBatchSize)}	}
			}
			action("Save",done())
			
			action done(){
				return inventory(i);
			}
		}
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
