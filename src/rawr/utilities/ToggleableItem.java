package rawr.utilities;

public abstract class ToggleableItem extends Item {
	private boolean toggleState;
	
	public ToggleableItem(String name, String showName, String description, boolean initialState) {
		super(name, showName, description);
		toggleState = initialState;
	}
	
	
	public String toggle() {
		if(toggleState) {
			toggleState = false;
			return isOff();
		} else {
			toggleState = true;
			return isOn();
		}
	}
	
	public abstract String isOn();
	public abstract String isOff();
	
	public boolean getState() {
		return toggleState;
	}
}
