package rawr.components;

public abstract class Thing extends PersonalEntity {
	boolean visible;
	
	public Thing(String name, String description) {
		super(name, description);
		visible = true;
	}
	
	public Thing(String name, String description, boolean visible) {
		super(name, description);
		this.visible = visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
}
