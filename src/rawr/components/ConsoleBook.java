package rawr.components;

/**
 * Books are made to micromanage Chapters in a way it is easier to understand
 * 
 * @author Joshua Collado Soler
 *
 */
public abstract class ConsoleBook extends Book {

	public ConsoleBook(String name, String description) {
		super(name, description);
		init();
	}

	@Override
	protected void addChapter(Chapter chapter) {
		if (chapter == null) {
			System.out.println("Error: Chapter is null and cannot be added");
			System.exit(1);
		}
		
		// TODO: Write error handling for this case
		ConsoleChapter newChapter;
		
		if(chapter instanceof ConsoleChapter) {
			newChapter = (ConsoleChapter) chapter;
			chapters.add(newChapter);
		}
		

	}

	@Override
	protected void addChapters(Chapter[] chapters) {
		for (Chapter chapter : chapters) {
			addChapter(chapter);
		}

	}
	
	/**
	 * Get next chapter in line
	 * 
	 * @return ConsoleChapter
	 */
	public ConsoleChapter getNextInLine() {
		return (ConsoleChapter) chapters.poll();
	}

}
