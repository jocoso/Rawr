package rawr.components;

import java.util.List;  
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import rawr.components.tools.TextUtilities;
import rawr.components.utilities.CommandManager;

public abstract class Chapter extends Entity {
	private String intro;
	
	// Delete this as soon as console is on place
	Scanner scnr = new Scanner(System.in);
	
	private List<GameMap> maps;
	private List<Event> events;
	private Person protagonist;
	private boolean finished;
	private CommandManager manager;
	
	public Chapter(String name, String description) {
		super(name, description);
		intro = "";
		maps = new LinkedList<GameMap>();
		events = new ArrayList<Event>();
		manager = new CommandManager();
		protagonist = null;
	}
	
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public String getIntro() {
		return intro;
	}
	
	public void setProtagonist(Person protagonist) {
		this.protagonist = protagonist;
	}
	
	public void addCommand(String command_name, Command command) {
		String c = TextUtilities.oneWordSanitizer(command_name);
		manager.addCommand(c, command);
	}
	
	public void addCommands(String[] command_names, Command[] commands) {
		manager.addCommands(command_names, commands);
	}
	
	protected void addMap(GameMap map) {
		// TODO: Create an ErrorHandler for this case
		if(map == null) return;
		this.maps.add(map);
	}
	
	protected void addMaps(GameMap[] maps) {
		for(GameMap map : maps)
			addMap(map);
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished; 
	}
	
	public boolean hasFinished() {
		return finished;
	}
	
	protected void addEvent(Event event) {
		// TODO: Create an ErrorHandler for this case
			if(event == null) return;
		this.events.add(event);
	}
	
	protected void addEvents(Event[] events) {
		for(Event event : events) 
			addEvent(event);
	}
	
	protected void removeEvent(Event event) {
		if(event == null) return;
		this.events.remove(event);
	}
	
	private void checkEvents() {
		Iterator<Event> iter = events.iterator();

		while (iter.hasNext()) {
		    Event event = iter.next();

		    if (event.check())
		        iter.remove();
		}
	}
	
	public abstract void init();
	
//	public void run(Console console) {
//		// code to run a chapter goes here
//	}
	
	// TODO: Replace this with the commented out one once testing is over
	public void run() {
		init();
		// TODO: Create an ErrorHandler for these case
		if(maps.isEmpty()) return;
		if(protagonist == null) return;
		
		System.out.println(TextUtilities.inChapterTitleFormat(getName()));
		System.out.println("");
		System.out.println(intro);
		System.out.println("");
		
		while(!hasFinished()) {
			System.out.print("> ");
			String command = scnr.nextLine();
			String response = manager.resolve(command);
			System.out.println(response);
			checkEvents();
		}
	}

}
