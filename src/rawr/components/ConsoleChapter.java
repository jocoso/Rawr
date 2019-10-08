package rawr.components;

import java.awt.Color;
import java.util.List;

import rawr.components.utilities.CommandManager;
import rawr.displayer.R_Input;
import rawr.displayer.R_Window;

public abstract class ConsoleChapter extends Chapter{
	
	private R_Window canvas;
	private boolean listening;
	public static String response;

	public ConsoleChapter(String name, String description) {
		super(name, description);
		
		listening = false;
	}
	
	public void prepareTools(R_Window canvas, R_Input brush) {
		this.canvas = canvas;
	}
	
	public String read() {
		listening = true;
		String text;
		
		//System.out.println(response);
		
		if(response != null) {
			text = response;
			response = null;
			listening = false;
			return text;
		}
		
		return null;
		
	}
	
	public boolean isListening() {
		return listening;
	}
	
	public void write(String text, Color color) {
		canvas.print(text, color);
	}
	
	public void write(String text) {
		canvas.print(text);
	}
	
	public void writeln(String text, Color color) {
		canvas.println(text, color);
	}
	
	public void writeln(String text) {
		canvas.println(text);
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
