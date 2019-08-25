package rawr.components;

import java.util.Queue;

import java.util.LinkedList;
import java.util.HashMap;

/**
 * Books are made to micromanage Chapters in a way it
 * is easier to understand
 * @author Temp
 *
 */
public abstract class Book extends Entity {
	private Queue<Chapter> chapters;
	//private Console console;
	private HashMap<String, Command> commands;
	

	public Book(String name, String description) {
		super(name, description);
		chapters = new LinkedList<Chapter>();
		commands = new HashMap<String, Command>();
		init();
	}
	
	/**
	 * Verify the book was created thoughtfully before 
	 * start using it or its components
	 * @return true if the book was created correctly false otherwise
	 */
	public boolean isReady() {
		return hasChapters();
	}
	
	protected void addChapter(Chapter chapter) {
		// TODO: Write error handling for this case
		if(chapter == null) {
			System.out.println("Error: Chapter is null and cannot be added");
			System.exit(1);
		}
		chapters.add(chapter);
	}
	
	protected void addChapters(Chapter[] chapters) {
		for(Chapter chapter: chapters) {
			addChapter(chapter);
		}
	}
	
	
	
	protected void addCommand(String name, Command command) {
		this.commands.put(name, command);
	}
	
	protected void addCommands(String[] names, Command[] commands) {
		// TODO: Display error message instead
		if(names.length != commands.length) return;
		
		for( int i = 0; i < commands.length; i++) {
			this.commands.put(names[i], commands[i]);
		}
	}
	
	protected Command removeCommand(String name) {
		return this.commands.remove(name);
	}
	
	
	public boolean hasChapters() {
		return !chapters.isEmpty();
	}
	
	protected abstract void init();
	
	public void begin() {
		init();
		if(hasChapters())
			chapters.poll().run();
		System.out.print("THE END!");
	}
	
	public Chapter getNextInLine() {
		return chapters.poll();
	}

}
