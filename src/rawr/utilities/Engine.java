package rawr.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import rawr.displayer.Console;
import rawr.internal.Event;

public class Engine {
	GameMap map;
	List<Event> events;
	ActionContainer actions;
	Console mainWindow;
	private boolean isPlaying = true;
	private boolean stop = false;
	Thread ev;

	public Engine() {
		this.map = null;
		this.actions = null;
		this.events = null;
		this.mainWindow = null;
		
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	public void setPlay(boolean playing) {
		isPlaying = playing;
	}
	
	public void load(GameMap map, ActionContainer actions, ArrayList<Event> e, Console mainWindow) {
		this.map = map;
		this.actions = actions;
		this.events = e;
		this.mainWindow = mainWindow;
		
		ev = new Thread() {
			public void run() {
				while(isPlaying)
					eventManager();
				
				if(!stop) {
					eventManager();
					stop = true;
				}
			}
		};
		
		ev.start();
	}
	
	
	public void eventManager() {
		Iterator<Event> iter = events.iterator();

		while (iter.hasNext()) {
			Event event = iter.next();

			if (event.check()) {
				event.occurence(mainWindow);
				iter.remove();
			}
		}
	}
	
	private void actionManager(String verb, String[] command) {
		if(actions.containsKey(verb)) {
			mainWindow.println(actions.getAction(verb).act(command));
		} else {
			mainWindow.println("I don't understand what you are trying to say");
		}
	}
	
	
	public void inputManager(String command, String error) {
		if(isPlaying) {	
			String[] input = command.split(" ");
			actionManager(input[0], input);
			map.updateCurrentRoom();
		} 
	}
	
}
