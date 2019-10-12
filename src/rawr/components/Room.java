package rawr.components;

import java.util.HashMap;
import java.util.Map;

import rawr.components.Transitional;

public class Room extends PersonalEntity {
	
	private final Room[] connections;
	private final Transitional[] transitions;
	private Map<String, Thing> thingsOnRoom;
	private String roomObjectDescription;
	private String roomPositionInSpace;
	private String roomDescription;

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
		if(thingsOnRoom.isEmpty()) {
			roomObjectDescription = "The room is totally empty";
			return;
		}
		
		
		roomObjectDescription = "The room has: \n";

		for (Map.Entry<String, Thing> entry : thingsOnRoom.entrySet()) {
			Thing object = entry.getValue();
			if(object.isVisible())
				roomObjectDescription += "- " + object.getDescription() + "\n";
		}
		
	}
	
	private String getRoomObjectDescription() {
		return roomObjectDescription;
	}
	
	private void updateRoomPositionInSpaceDescription() {
		roomPositionInSpace = "";
		// NORTH room
		if(connections[0] != null)
			roomPositionInSpace += "To the NORTH there is the " + connections[0].getName() + ".\n"; 
		// EAST room
		if(connections[1] != null)
			roomPositionInSpace += "To the EAST there is the " + connections[1].getName() + ".\n"; 
		// SOUTH room
		if (connections[2] != null)
			roomPositionInSpace += "To the SOUTH there is the " + connections[2].getName() + ".\n"; 
		// WEST room
		if (connections[3] != null)
			roomPositionInSpace += "To the WEST there is the " + connections[3].getName() + ".\n"; 
	}
	
	private String getRoomPositionInSpaceDescription() {
		return roomPositionInSpace;
	}
	
	public void updateDescription() {
		roomDescription = super.getDescription() + "\n";
		roomDescription += getRoomObjectDescription() + "\n";
		roomDescription += getRoomPositionInSpaceDescription() + "\n";
	}
	
	@Override
	public String getDescription() {
		return roomDescription;
	}
	
	public void setConnection(Room connect, Transitional transition, int coordinate) {
		this.connections[coordinate] = connect;
		this.transitions[coordinate] = transition;
		updateRoomPositionInSpaceDescription();
	}
	
	public boolean canTransition(Character character, int coordinate) {
		
		if(transitions[coordinate] == null) return true;
		
		Transitional roomTransition = transitions[coordinate];
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
