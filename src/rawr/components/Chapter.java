package rawr.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rawr.components.tools.TextUtilities;
import rawr.components.utilities.CommandManager;


/**
 * Chapter class has all the tools to display the story
 * in the GUI. Commands, Events, Narrative, Character creation...
 * all goes in here.
 * 
 * @author Joshua Collado Soler
 *
 */
public abstract class Chapter extends Entity{
	// 
		protected boolean finished;
		
		// Classes
		protected String intro;
		protected CommandManager cm;
		protected Character protagonist;
		
		// Lists
		protected List<GameMap> maps;
		protected List<Event> events;
		

		public Chapter(String name, String description) {
			super(name, description);
			
			intro = "";
			
			maps = new LinkedList<GameMap>();
			events = new ArrayList<Event>();
			cm = new CommandManager();
			protagonist = null;
			
			init();
		}
		
		/**
		 * First thing to be presented to the player
		 * when they start a new chapter of the game, followed by the 
		 * name and the description of the protagonist whereabouts
		 * @param intro
		 */
		public void setIntro(String intro) {
			this.intro = intro;
		}

		/**
		 * Returns the Chapter intro
		 * 
		 * @return intro
		 */
		public String getIntro() {
			return intro;
		}
		
		// TODO: Find out how to make the protagonist a mandatory field for Chapter
		/**
		 * Protagonist are the player's skin. 
		 * The protagonist contains very important information.
		 * 
		 * @param protagonist
		 */
		public void setProtagonist(Character protagonist) {
			this.protagonist = protagonist;
		}

		/**
		 * Takes one command and ensures that it gets executed when
		 * the user input its name
		 * 
		 * @param command_name 
		 * @param command 
		 */
		public void addCommand(String command_name, Command command) {
			String c = TextUtilities.oneWordSanitizer(command_name);
			cm.addCommand(c, command);
		}
		
		/**
		 * Takes an array of commands and ensures that they get executed when
		 * the user input their name.</br> </br>
		 * 
		 * <b>NOTE:</b> The index on the command_names should be the same as the respective index
		 * of their commands counterpart
		 * 
		 * @param command_names 
		 * @param commands 
		 */
		public void addCommands(String[] command_names, Command[] commands) {
			cm.addCommands(command_names, commands);
		}

		/**
		 * Add a map to the chapter for Book to manage information more efficiently.
		 * @param map 
		 */
		protected void addMap(GameMap map) {
			// TODO: Create an ErrorHandler for this case
			if (map == null)
				return;
			this.maps.add(map);
		}
		
		/**
		 * Add multiple maps to the chapter for Book to manage information more efficiently.
		 * @param maps 
		 */
		protected void addMaps(GameMap[] maps) {
			for (GameMap map : maps)
				addMap(map);
		}
		
		/**
		 * A chapter must finished in an event. In this event, the chapter
		 * is notified it is finished by using this method. 
		 */
		public void setFinished() {
			this.finished = true;
		}
		
		/**
		 * Book will execute this method to know when to move on to the next chapter
		 * @return Boolean
		 */
		public boolean hasFinished() {
			return finished;
		}
		
		/**
		 * Add an Event to the queue for the Book to execute it 
		 * every iteration.
		 * @param event
		 */
		protected void addEvent(Event event) {
			// TODO: Create an ErrorHandler for this case
			if (event == null) {
				System.out.println("ERROR: Event is null");
				return;
			}
			events.add(event);
		}
		
		/**
		 * Add an array of events to the queue for the Book to execute them
		 * every iteration.
		 * @param events 
		 */
		protected void addEvents(Event[] events) {
			for (Event event : events)
				addEvent(event);
		}

		
		/**
		 * Removes an event from the queue. 
		 * @param event
		 */
		protected void removeEvent(Event event) {
			if (event == null) {
				System.out.println("ERROR: Event is null");
				return;
			}
			events.remove(event);
		}

		/**
		 * Check all the events in queue. If one return true
		 * eliminate it from queue.
		 */
		protected void checkEvents() {
			Iterator<Event> iter = events.iterator();

			while (iter.hasNext()) {
				Event event = iter.next();

				if (event.check())
					iter.remove();
			}
		}
		
		/**
		 * Returns the protagonist of the story. 
		 * 
		 * @return Person protagonist
		 */
		public Person getProtagonist() {
			return protagonist;
		}
		
		/**
		 * Overridable method. 
		 * The Library user will create or execute their stories in here.
		 */
		public abstract void init();


}
