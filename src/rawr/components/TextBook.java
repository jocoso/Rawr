package rawr.components;

public abstract class TextBook extends Book {

	public TextBook(String name, String description) {
		super(name, description);
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
	
	@Override
	protected void addChapter(Chapter chapter) {
		if (chapter == null) {
			System.out.println("Error: Chapter is null and cannot be added");
			System.exit(1);
		}
		
		// TODO: Write error handling for this case
		TextChapter reformattedChapter;
		
		if(chapter instanceof TextChapter) {
			reformattedChapter = (TextChapter) chapter;
			chapters.add(reformattedChapter);
		}
		

	}

	@Override
	protected void addChapters(Chapter[] chapters) {
		for (Chapter chapter : chapters) {
			addChapter(chapter);
		}

	}
	
	
	public boolean hasChapters() {
		return !chapters.isEmpty();
	}
	
	
	public void begin() {
		if(hasChapters()) {
			TextChapter currentChapter = (TextChapter) chapters.poll();
			currentChapter.run();
		}
	}
}
