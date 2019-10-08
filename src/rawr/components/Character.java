package rawr.components;

import java.util.HashMap;
import java.util.Map;

import rawr.components.tools.TextUtilities;


public class Character extends Person{
	Map<String, Item> inventory;

	public Character(String name, String description, Room currentRoom) {
		super(name, description, currentRoom);
		inventory = new HashMap<String, Item>();
	}
	
	public void addToInventory(Item item) {
		String name = TextUtilities.oneWordSanitizer(item.getName());
		inventory.put(name, item);
	}
	
	public Item getFromInventory(String itemName) {
		if(inventory.containsKey(itemName))
			return inventory.get(itemName);
		return null;
	}
	
	public boolean isInventoryEmpty() {
		return inventory.isEmpty();
	}
	
}
