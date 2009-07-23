module inventory/itemSet

imports inventory/item

entity ItemSet {
	name		:: String (name)
	items 	-> Set<Item>
}

define page editItemSet(i:Inventory) {
	main()
	define body() {
		navigate(inventory(i)) {"<< return to inventory"}
		
		header{ output(i.name) "'s item set" }
		
		table{
			for(it:Item in i.itemSet.items order by it.name){
				row{
					output(it.name)
					output(it.manufacturer)
					form{ action("delete",delete(it)) }
				}
			}
		}
		
		form{
			var newItem := Item{};
			label("Name"){input(newItem.name)}
			label("Manufacturer"){input(newItem.manufacturer)}
			action("add",addItem())
			
			action addItem(){
				newItem.save();
				i.itemSet.items.add(newItem);
				return editItemSet(i);
			}
		}
		
		action delete(it:Item){
			i.itemSet.items := [x | x : Item in i.itemSet.items where x != it];
			message("Item has been removed");
			return editItemSet(i);
		}
	}
}
