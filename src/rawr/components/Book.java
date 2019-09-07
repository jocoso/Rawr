package rawr.components;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Book extends Entity{
	protected Queue<Chapter> chapters;
	

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
	
	protected abstract void addChapter(Chapter chapter);
	
	protected abstract void addChapters(Chapter[] chapters);
	
	
	public boolean hasChapters() {
		return !chapters.isEmpty();
	}
	
	protected abstract void init();

}
