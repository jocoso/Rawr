package rawr.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import rawr.internal.Prop;
import rawr.utilities.tools.Tools;

public class Room extends Prop {
	
	private Map<String, Item> roomItems;
	
	private ArrayList<String> roomItemDescription;
	
	// Coordinates
	private Room[] rooms;
	
	private boolean illuminated;
	
	public Room(String name, String showName, String description) {
		super(name, showName, description);
		initVariables();
	}
	
	private void initVariables() {
		roomItems = new HashMap<String, Item>();
		roomItemDescription = new ArrayList<String>();
		rooms = new Room[4];
		illuminated = true;
	}
	
	public boolean isIlluminated() {
		return illuminated;
	}
	
	public void setIllumination(boolean illuminated) {
		this.illuminated = illuminated;
	}
	
	public void addAdjacentRooms(Room[] rooms) {
		if(rooms.length > 4) return;
		
		this.rooms = rooms;
	}
	
	public void addRoomAt(Room room, int coordinate) {
		if(GameMap.isValidMove(coordinate)) {
			rooms[coordinate] = room;
		}
	}

	
	public Room getRoomAt(int coordinate) {
		return rooms[coordinate];
	}
	
	public boolean hasRoomAt(int coordinate) {
		return GameMap.isValidMove(coordinate) && rooms[coordinate] != null; 
	}
	
	public void addItem(Item item) {
		roomItems.put(Tools.sanitize(item.getName()),item);
		updateRoomItemsDescription();
	}
	
	public void addItems(Item[] items) {
		for(Item item : items) {
			addItem(item);
		}
	}
	
	public String update() {
		return "";
	}
	
	public boolean hasItem(String itemName) {
		return roomItems.containsKey(Tools.sanitize(itemName));
	}
	
	public Item getItem(String itemName) {
		if(roomItems.containsKey(Tools.sanitize(itemName))) {
			return roomItems.get(itemName); 
		} 
		
		return null;
	}
	
	
	public void updateRoomItemsDescription() {
		
		roomItemDescription.clear();
		
		if(roomItems.isEmpty()) {
			roomItemDescription.add("There is nothing in the room"); 
			return;
		}
		
		Iterator<Entry<String, Item>> it = roomItems.entrySet().iterator();
		

	    while (it.hasNext()) {
	        Entry<String, Item> pair = (Map.Entry<String, Item>)it.next();
	        Item item = pair.getValue();
	        if(item.isShowing())
	        	roomItemDescription.add( item.getShowName() + "- " + item.getDescription());
	        //it.remove(); // avoids a ConcurrentModificationException
	    }
	}
	
	public ArrayList<String> getRoomItemDescription() {
		return roomItemDescription;
	}
}
