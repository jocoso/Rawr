package rawr.components;

import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

public abstract class Book extends Entity {
	private Queue<Chapter> chapters;
	//private Console console;
	private HashMap<String, Command> commands;
	

	public Book(String name, String description) {
		super(name, description);
		
		chapters = new LinkedList<Chapter>();
		commands = new HashMap<String, Command>();
	}
	
	protected void addChapter(Chapter chapter) {
		// TODO: Write error handling for this case
		if(chapter == null) return;
		this.chapters.add(chapter);
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
		return this.chapters.size() > 0;
	}
	
	protected abstract void init();
	
	public void begin() {
		init();
		if(hasChapters())
			chapters.poll().run();
		System.out.println("THE END!");
	}

}
