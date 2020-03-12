package rawr.utilities;

import rawr.internal.Prop;

public abstract class Item extends Prop {
	private boolean showing;
	
	public Item(String name, String showName, String description) {
		super(name, showName, description);
		initVariables();
	}
	
	private void initVariables() {
		showing = true;
	}
	
	public boolean isShowing() {
		return showing;
	}
	
	public void setShowing(boolean showing) {
		this.showing = showing;
	}
	
}
