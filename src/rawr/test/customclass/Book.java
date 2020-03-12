package rawr.test.customclass;

import rawr.utilities.Item;

public class Book extends Item {
	
	private Page[] pages;
	private int numberOfPages;

	public Book(String name, String showName, String description, int numberOfPages) {
		super(name, showName, description);
		initVariables();
	}
	
	public void initVariables() {
		pages = new Page[numberOfPages];
		numberOfPages = 0;
	}
	
	public void addPage(Page page) {
		pages[numberOfPages++] = page;
	}

	public String read(int pageNumber) {
		return pages[pageNumber].read();
	}
	

}
