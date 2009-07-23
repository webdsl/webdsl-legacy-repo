module user/data

entity ItemStock {
	item		-> Item
	amount 	:: Int
}

entity Item { 
	name		:: String (name)
	manufacturer :: String
}
