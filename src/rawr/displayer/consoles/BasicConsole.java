package rawr.displayer.consoles;

import java.awt.Dimension;

import rawr.components.Book;
import rawr.displayer.Console;

public class BasicConsole extends Console {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int width, height;

	public BasicConsole(String consoleTitle, Book book) {
		super(consoleTitle, book);
	}
	
	

	@Override
	public void prepare() {
			int width = 700;
			int height = 500;
			
			setSize(new Dimension(width, height));
	}

}
