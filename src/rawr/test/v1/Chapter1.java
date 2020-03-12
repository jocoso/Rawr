package rawr.test.v1;

import java.util.ArrayList;
import java.util.Arrays;

import rawr.displayer.Console;
import rawr.internal.Action;
import rawr.internal.Event;
import rawr.test.customclass.Chapter;
import rawr.test.customclass.Protagonist;
import rawr.utilities.ActionContainer;
import rawr.utilities.GameMap;
import rawr.utilities.Item;
import rawr.utilities.Room;
import rawr.utilities.tools.Tools;

public class Chapter1 extends Chapter{
	private GameMap map;
	private ArrayList<Event> events;
	private ActionContainer ac;
	private Protagonist manolo;
	public Chapter1(Console window) {
		// Initializing the variables
		initVariables();
		
		setChapterMap();
		setIntro("He wakes up in a humid, empty room."
				+ " The smell of mud and blood making him gag. He thinks on STANDING up...");
		setEvents();
		setActions();
		loadEngine(map, ac, events, window);
	}
	
	private void initVariables() {
		map = new GameMap();
		events = new ArrayList<Event>();
		ac = new ActionContainer();
		manolo = new Protagonist();
	}
	
	private void setIntro(String intro) {
		events.add(new Event() {

			@Override
			public void occurence(Console con) {
				con.println(intro);
				
			}

			@Override
			public boolean check() {
				return true;
			}});
	}
	
	private void setEvents() {
		Event firstStand = new Event() {

			@Override
			public void occurence(Console con) {
				con.println("He pukes on the floor. 'Where am i?' "
						+ "he asks himself trying to concentrate and EXAMINE his sourroundings.");
				
			}

			@Override
			public boolean check() {
				return manolo.isStanding();
			}
			
		};
		
		events.add(firstStand);
		
	}
	
	private void setActions() {
		Action stand = new Action() {

			@Override
			public String act(String[] command) {
				if(manolo.isStanding()) return "He is already standing";
				
				else {
					manolo.setStanding(true); 
					return "He is now standing";
				}
			}
			
		};
		
		Action examine = new Action() {

			@Override
			public String act(String[] command) {
				if(command.length == 1) return Tools.roomExposition(map.getCurrentRoomShowName(), map.getCurrentRoomDescription(), map.getCurrentRoomItemList());
				else if(command.length > 2) { 
					command[1] = concat(Arrays.copyOfRange(command, 1, command.length));
					if(map.currentRoomHasItem(command[1])) {
						Item item = map.getItemInCurrentRoom(command[1]);
						return item.getDescription();
					}
				} else if(command.length == 2) {
					if(map.currentRoomHasItem(command[1])) {
						Item item = map.getItemInCurrentRoom(command[1]);
						return item.getDescription();
					}
				}
				return "Examine what?";
				
			}
			
		};
		
		ac.addAction("examine", examine);
		ac.addAction("stand", stand);
	}
	
	private String concat(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		for(String s : args)
			sb.append(s);
		
		return sb.toString();
	}
	
	private void setChapterMap() {
		// Rooms
		Room strangeRoom = new Room("strangeroom", "Strange Room", "A dark scary place.");
		
		map = new GameMap();
		map.addRoom(strangeRoom);
	}
	
}
