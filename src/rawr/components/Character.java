package rawr.components;

import java.util.HashMap;
import java.util.Map;

import rawr.components.tools.TextUtilities;

/**
 * Character class is a Person class with the ability to have an inventory
 * Only members of the Item class can be added to the inventory
 * @author Joshua Collado Soler
 *
 */
public class Character extends Person{
	Map<String, Item> inventory;
	
	public Character(String name, String description, Room currentRoom) {
		super(name, description, currentRoom);
		inventory = new HashMap<String, Item>();
	}
	
	/**
	 * Adds an Item to the character inventory.
	 * @param item
	 */
	public void addToInventory(Item item) {
		// Make sure the given String follows the rules
		String name = TextUtilities.oneWordSanitizer(item.getName());
		inventory.put(name, item);
	}
	
	/**
	 * Gets a Item from the inventory. If it can't find the item returns null
	 * @param itemName
	 * @return Item
	 */
	public Item getFromInventory(String itemName) {
		return inventory.get(itemName);
	}
	
	/**
	 * @return Boolean
	 */
	public boolean isInventoryEmpty() {
		return inventory.isEmpty();
	}
	
}
