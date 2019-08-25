package rawr.components;

import java.util.Queue; 
import java.util.LinkedList;

/**
 * Books are made to micromanage Chapters in a way it
 * is easier to understand
 * @author Temp
 *
 */
public abstract class Book extends Entity {
	private Queue<Chapter> chapters;
	

	public Book(String name, String description) {
		super(name, description);
		chapters = new LinkedList<Chapter>();
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
