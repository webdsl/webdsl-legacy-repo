module inventory/item

define page item(i:Item)
{
	output(i.name)
	" - "
	output(i.manufacturer)
}
