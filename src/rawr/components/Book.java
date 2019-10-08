package rawr.components;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The Book class helps organizing the chapters in FIFO form
 * Also ensures the past of information between chapters
 * @author Temp
 *
 */
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
	 * @return Boolean
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
