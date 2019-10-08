package rawr.components;

public class UpdatableThing extends Thing {
	
	private String description2;
	private boolean updated;
	
	public UpdatableThing(String name, String description1, String description2) {
		super(name, description1);
		this.description2 = description2;
		updated = false;
	}
	
	public void update() {
		this.setDescription(description2);
		updated = true;
	}
	
	public boolean hasUpdated() {
		return updated;
	}
}
