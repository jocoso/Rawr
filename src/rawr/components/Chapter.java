package rawr.components;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import rawr.components.tools.TextUtilities;
import rawr.components.utilities.CommandManager;

/**
 * Chapter is a class created as a stand-alone for computer terminals
 * as well as a displayable component for the Console class. It will only 
 * display a Window to display text and an Input text to get user orders
 * @author Joshua Collado Soler
 *
 */
public abstract class Chapter extends Entity {
	
	// 
	private boolean finished;
	
	// Classes
	private String intro;
	private Scanner scnr;
	private CommandManager cm;
	private Person protagonist;
	
	// Lists
	private List<GameMap> maps;
	private List<Event> events;
	

	public Chapter(String name, String description) {
		super(name, description);
		
		intro = "";
		
		maps = new LinkedList<GameMap>();
		events = new ArrayList<Event>();
		cm = new CommandManager();
		protagonist = null;
		scnr = new Scanner(System.in);
		
		init();
	}
	
	/**
	 * Intro are the first thing that will be presented to the player
	 * when they start a new chapter.
	 * @param intro
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getIntro() {
		return intro;
	}
	
	/**
	 * Protagonist are the players at the moment. 
	 * @param protagonist
	 */
	public void setProtagonist(Person protagonist) {
		this.protagonist = protagonist;
	}

	/**
	 * Commands are interfaces created to modify a piece of data inside the 
	 * Chapter class. 
	 */
	public void addCommand(String command_name, Command command) {
		if(command == null || command_name == null) {
			System.err.println("Error: none of the parameters can be null");
			System.exit(1);
		}
		String c = TextUtilities.oneWordSanitizer(command_name);
		cm.addCommand(c, command);
	}

	public void addCommands(String[] command_names, Command[] commands) {
		cm.addCommands(command_names, commands);
	}

	protected void addMap(GameMap map) {
		// TODO: Create an ErrorHandler for this case
		if (map == null)
			return;
		this.maps.add(map);
	}

	protected void addMaps(GameMap[] maps) {
		for (GameMap map : maps)
			addMap(map);
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean hasFinished() {
		return finished;
	}
	
	/**
	 * Events are interfaces created to keep looking for certain conditions
	 * Events can run many times but once they server their purpose they are
	 * eliminated from the queue
	 */
	protected void addEvent(Event event) {
		// TODO: Create an ErrorHandler for this case
		if (event == null)
			return;
		this.events.add(event);
	}

	protected void addEvents(Event[] events) {
		for (Event event : events)
			addEvent(event);
	}

	

	protected void removeEvent(Event event) {
		if (event == null)
			return;
		this.events.remove(event);
	}

	private void checkEvents() {
		Iterator<Event> iter = events.iterator();

		while (iter.hasNext()) {
			Event event = iter.next();

			if (event.check(null))
				iter.remove();
		}
	}

	public abstract void init();

//	public void run(Console console) {
//		// code to run a chapter goes here
//	}

	// TODO: Replace this with the commented out one once testing is over
	/**
	 * This method is called only when the book will be manually rendered in
	 * a system terminal. It should NEVER be used if intended to be rendered 
	 * in the GUI console.
	 */
	public void run() {
		init();

		// TODO: Create an ErrorHandler for these case
		if (maps.isEmpty())
			return;
		if (protagonist == null)
			return;

		System.out.println(TextUtilities.inChapterTitleFormat(getName()));
		System.out.println("");
		System.out.println(intro);
		System.out.println("");

		while (!hasFinished()) {
			System.out.println("> ");
			String command = scnr.nextLine();
			String response = cm.resolve(command);
			System.out.println(response);
			checkEvents();
		}
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
