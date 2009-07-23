module inventory/pages

imports inventory/inventory

define inventoryList()
{
	var u : User := securityContext.principal;
	group("Inventories"){
    		table{
			for(i:Inventory in u.inventories order by i.name){
				navigate(inventory(i)) {output(i)}
			}
		}
		navigate(createInventory()) {"Create New"}
	}
}

define page createInventory() {
	main()
	define body() {
		var i := Inventory{};
		form{
			formgroup("Create Inventory"){
				label("Name"){input(i.name)}
				break
				action("save",save())
				action save(){
					var u : User := securityContext.principal;
					i.itemSet := ItemSet{name := i.name};
					i.save();
					u.inventories.add(i);
					message("Inventory " + i.name + " created");
					return inventory(i);
				}
			}
		}
		
	}
}
  
