package rawr.components;

import java.util.Map;

import rawr.components.tools.TextUtilities;

import java.util.HashMap;

// TODO: Consider named it Setting or Place

public class GameMap {
	private Map<String, Room> rooms;
	public static final int NORTH = 0;
	public static final int EAST  = 1;
	public static final int SOUTH = 2;
	public static final int WEST  = 3;
	
	public GameMap() {
		rooms = new HashMap<String, Room>();
	}
	
	public boolean isEmpty() {
		return rooms.isEmpty();
	}
	
	public String addRoom(String identifier, Room room) {
		rooms.put(identifier, room);
		
		return identifier;
	}
	
	public String addRoom(String identifier, String description) {
		Room room = new Room(identifier, description);		
		rooms.put(identifier, room);
		return identifier;
	}
	
	public Room getRoom(String room_name) {
		if(!rooms.containsKey(room_name)) return null;
		return rooms.get(room_name);
	}
	
	public Room removeRoom(String room_name) {
		return rooms.remove(room_name);
	}
	
	public Map<String, Room> getAllRooms() {
		return rooms;
	}
	
	// Gets the room at the opposite side
	private int opposite(int coordinate) {
		if(coordinate - 2 < 0) return coordinate + 2;
		return coordinate - 2;
	}
	
	public void connect(String from, String to, int coordinate, Transitional transition) {
		Room to_connect = rooms.get(from);
		Room to_be_connected = rooms.get(to);
		
		connect(to_connect, to_be_connected, coordinate, transition);
		
	}
	
	public void connect(Room from, Room to, int coordinate, Transitional transition) {
		
		from.setConnection(to, transition, coordinate);
		int opposite_coordinate = opposite(coordinate);
		to.setConnection(from, transition, opposite_coordinate);
	}
	
	public void severe(String name, int coordinate) {
		// Code to disconnecting a redirect with String
		String nill = null;
		connect(nill, nill, coordinate, null);
	}
	
	public static int translateCoordinate(String coordinate) {
		coordinate = TextUtilities.oneWordSanitizer(coordinate);
		switch(coordinate) {
		case "north":
			return 0;
		case "east":
			return 1;
		case "south":
			return 2;
		case "west":
			return 3;
		default:
			return -1;
		}
	}
	
}
