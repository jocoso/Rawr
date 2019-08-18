package rawr.components;

import java.util.ArrayList;

import rawr.components.Transitional;
import rawr.components.tools.TextUtilities;

public class Room extends PersonalEntity {
	
	private final Room[] connections;
	private final Transitional[] transitions;
	private ArrayList<Thing> thingsOnRoom;

	public Room(String name, String description) {
		super(name, description);
		connections = new Room[4];
		transitions = new Transitional[4];
		thingsOnRoom = new ArrayList<Thing>();
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
		thingsOnRoom.add(object);
	}
	
	public void removeObjectOnRoom(Thing object) {
		thingsOnRoom.remove(object);
	}
	
	public Thing search(String thingName) {
		for(int i = 0; i < thingsOnRoom.size(); i++) {
			Thing thing = thingsOnRoom.get(i);
			String itsName = TextUtilities.oneWordSanitizer(thing.getName());
			if(itsName.equals(thingName))
				return thing;
		}
		return null;
	}

}
