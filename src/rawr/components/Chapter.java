package rawr.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rawr.components.tools.TextUtilities;
import rawr.components.utilities.CommandManager;

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
		public void setProtagonist(Character protagonist) {
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
			if (event == null) {
				System.out.println("ERROR: Event is null");
				return;
			}
			events.add(event);
		}

		protected void addEvents(Event[] events) {
			for (Event event : events)
				addEvent(event);
		}

		

		protected void removeEvent(Event event) {
			if (event == null) {
				System.out.println("ERROR: Event is null");
				return;
			}
			events.remove(event);
		}

		protected void checkEvents() {
			Iterator<Event> iter = events.iterator();

			while (iter.hasNext()) {
				Event event = iter.next();

				if (event.check())
					iter.remove();
			}
		}
		
		public Person getProtagonist() {
			return protagonist;
		}
		
		public abstract void init();


}
