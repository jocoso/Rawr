package rawr.components;

import java.util.List;

import rawr.components.utilities.CommandManager;

public abstract class ConsoleChapter extends Chapter{

	public ConsoleChapter(String name, String description) {
		super(name, description);
	}
	
	/**
	 * These functions are for use of Console when class 
	 * was given to GUI
	 */
	public CommandManager getCommandManager() {
		return cm;
	}
	
	public List<GameMap> getMaps() {
		return maps;
	}
	
	public List<Event> getEvents() {
		return events;
	}

}
