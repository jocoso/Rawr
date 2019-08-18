package rawr.components;

public abstract class Person extends PersonalEntity{
	private Room currentRoom;

	public Person(String name, String description, Room currentRoom) {
		super(name, description);
		this.currentRoom = currentRoom;
	}
	
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
}
