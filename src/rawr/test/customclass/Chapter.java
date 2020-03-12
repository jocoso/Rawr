package rawr.test.customclass;

import java.util.ArrayList;

import rawr.displayer.Console;
import rawr.internal.Event;
import rawr.utilities.ActionContainer;
import rawr.utilities.Engine;
import rawr.utilities.GameMap;

public abstract class Chapter {
	private Engine en;

	public Engine getEngine() {
		return en;
	}

	public void setEngine(Engine en) {
		this.en = en;
	}
	
	
	protected void loadEngine(GameMap map, ActionContainer ac, ArrayList<Event> e, Console c) {
		this.en = new Engine();
		en.load(map, ac, e, c);
	}
	
	
}
