package rawr.test.customclass;

import rawr.test.customInterfaces.Consumable;
import rawr.utilities.Item;

public abstract class Drink extends Item implements Consumable {

	public Drink(String name, String showName, String description) {
		super(name, showName, description);
		// TODO Auto-generated constructor stub
	}

}
