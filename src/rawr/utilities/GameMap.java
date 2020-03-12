package rawr.utilities;

import java.util.ArrayList;
import java.util.List;

import rawr.utilities.tools.Tools;

public class GameMap {
	private List<Room> map;
	private Room currentRoom;
	
	// Coordinates
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	
	public GameMap() {
		map = new ArrayList<Room>();
		currentRoom = null;
	}
	
	public GameMap(Room room) {
		map = new ArrayList<Room>();
		addRoom(room);
		currentRoom = room;
	}
	
	public void addRoom(Room room) {
		if(currentRoom == null) currentRoom = room; 
		map.add(room);
	}
	
	public void addRooms(Room[] rooms) {
		for(Room room : rooms) {
			addRoom(room);
		}
	}
	
	public Room getRoom(int index) {
		return map.get(index);
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void updateCurrentRoom() {
		currentRoom.updateRoomItemsDescription();
	}
	
	public static int getOpposite(int coordinate) {
		return (coordinate - 2 < 0) ? coordinate + 2 : coordinate - 2;
	}
	
	public static boolean isValidMove(int coordinate) {
		return coordinate == 0 || coordinate == 1 || coordinate == 2 || coordinate == 3;
	}
	
	public int move(int coordinate) {
		if(isValidMove(coordinate)) {
			currentRoom = currentRoom.getRoomAt(coordinate);
			return 0;
		}
		return -1;
	}
	
	public int move(String coordinate) {
		int coor = getCoordinateFromName(coordinate);
		if(coor > 0) return move(coor);
		else return -1;
	}
	
	public int getCoordinateFromName(String coordinate) {
		coordinate = Tools.sanitize(coordinate);
		if(coordinate.equals("north")) return 0;
		else if(coordinate.equals("east")) return 1;
		else if(coordinate.equals("south")) return 2;
		else if(coordinate.equals("weed")) return 3;
		return -1;
	}
	
	public boolean canMove(int coordinate) {
		return currentRoom.hasRoomAt(coordinate);
	}
	
	public String getCurrentRoomShowName() {
		return currentRoom.getShowName();
	}
	
	public String getCurrentRoomName() {
		return currentRoom.getName();
	}
	
	public String getCurrentRoomDescription() {
		return currentRoom.getDescription();
	}
	
	public void map(Room from, Room to, int coordinate) {
		if(isValidMove(coordinate) && to != null) {
			from.addRoomAt(to, coordinate);
			to.addRoomAt(from, getOpposite(coordinate));
		}
		
	}
	
	public boolean currentRoomHasItem(String it) {
		return currentRoom.hasItem(it);
	}
	
	public Item getItemInCurrentRoom(String it) {
		return currentRoom.getItem(it);
	}
	
	public ArrayList<String> getCurrentRoomItemList() {
		return currentRoom.getRoomItemDescription();
	}
	
}
