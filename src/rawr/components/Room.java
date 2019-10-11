package rawr.components;

import java.util.HashMap;
import java.util.Map;

import rawr.components.Transitional;

public class Room extends PersonalEntity {
	
	private final Room[] connections;
	private final Transitional[] transitions;
	private Map<String, Thing> thingsOnRoom;
	private String roomObjectDescription;

	public Room(String name, String description) {
		super(name, description);
		connections = new Room[4];
		transitions = new Transitional[4];
		thingsOnRoom = new HashMap<String, Thing>();
		updateRoomObjectDescription();
	}
	
	// Creates a description about the room objects and update
	// The actual description of the room acordingly everytime an Object
	// is added or removed
	private void updateRoomObjectDescription() {
		if(thingsOnRoom.isEmpty()) 
			roomObjectDescription = "The room is totally empty";
		
		roomObjectDescription = "The room has: \n";

		for (Map.Entry<String, Thing> entry : thingsOnRoom.entrySet()) {
			Thing object = entry.getValue();
			roomObjectDescription += "- " + object.getDescription() + "\n";
		}
		
	}
	
	public String getRoomObjectDescription() {
		return roomObjectDescription;
	}
	
	public void setConnection(Room connect, Transitional transition, int at) {
		this.connections[at] = connect;
		this.transitions[at] = transition;
	}
	
	public boolean canTransition(Character character, int at) {
		Transitional roomTransition = transitions[at];
		if(roomTransition.allowsTransition(character)) {
			return true;
		}
		
		return false;
	}
	
	public Room[] getConnections() {
		return connections;
	}
	
	public Room getConnectionAt(int coordinate) {
		// TODO: Make an erroHandling thing for this
		if(coordinate > 3 || coordinate < 0) return null;
		
		return connections[coordinate];
	}
	
	public boolean hasConnectionAt(int coordinate) {
		return connections[coordinate] != null;
	}
	
	public Transitional[] getTransitions() {
		return transitions;
	}
	
	public Transitional getTransitionAt(int coordinate) {
		return transitions[coordinate];
	}
	
	public void addObjectToRoom(Thing object) {
		thingsOnRoom.put(object.getName(), object);
		updateRoomObjectDescription();
	}
	
	public void removeObjectOnRoom(String objectName) {
		thingsOnRoom.remove(objectName);
		updateRoomObjectDescription();
	}
	
	public Thing search(String thingName) {
		return thingsOnRoom.get(thingName);
	}

}
