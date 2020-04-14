package com.rawr.engine;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Organizes the text displayed in the console in a way
 * that resembles a text adventure game. 
 * 
 * @author jocoso
 *
 */
public class Console {
	// CONSTANTS
	public static final int STANDARD_COLOR = 0xffff0000;
	private final int MARGIN = 5;
	private final int BAR_MARGIN = 15;
	private final int BAR_SECTION_SIZE = 4;
	
	// Save the lines to be written in the console and in which color
	// Each line should have a color associated with it.
	private ArrayList<String> notebook;
	private ArrayList<Integer> lineColors;
	
	// These variables save information regarding bars at the top of the 
	// console
	private String[] barTitle;
	private int[] barData;
	private int totalBars = 0; 
	
	// Console-controlling variables. !Important
	private int screenWidth, screenHeight; 
	private Integer biggestLetter = null; // The letter in the image Font with the biggest width
	private Point range;                  // Used to keep track of the lines displayed in the screen
	private int maxNumberOfMsg;			  // How many lines can be displayed based on the console size
	
	public Console(GameContainer gc) {
		
		notebook = new ArrayList<String>();
		lineColors = new ArrayList<Integer>();
		
		barTitle = new String[BAR_SECTION_SIZE];
		barData = new int[BAR_SECTION_SIZE * 2];
		
		// Finding out which letter has biggest width
		// By calculating the average of all the sizes
		biggestLetter = 0;
		
		int[] widths = gc.getRenderer().getFont().getWidths();
			
		for(int i = 0; i < widths.length; i++) {
			biggestLetter += widths[i];
		}
		
		biggestLetter /= widths.length;
		// --------------------------------------------
		
		screenWidth = gc.getWidth();
		screenHeight = gc.getHeight();
		
		// Magic number 21 is the yOffset of every line + 1 for the line
		// TODO: Try different console sizes to see if magic number hold up
		maxNumberOfMsg = (screenHeight / gc.getRenderer().getFont().getFontImage().getHeight()) - 21;
		range = new Point(0, 0);
		
	}
	
	/**
	 * All things related to text manipulation prior to displaying are managed in 
	 * this function.
	 * 
	 * @param input
	 * @param charSize
	 * @param screenWidth
	 * @param color
	 */
	private void sanitize(String input, int charSize, int screenWidth, int color) {
		// Input do not surpass the screenWidth
		if(input.length() * charSize < screenWidth) {
			inputText(input, color);
			return;
		}
		
		// Separate lines that are too long to display in the current
		// screen width
		
		int charactersLeft = input.length();
		int charactersPerLine = screenWidth / charSize;
		int beg = 0;
		int end = charactersPerLine;
		
		while(true) {	
			inputText(input.substring(beg, end), color);
			charactersLeft -=  charactersPerLine;
			
			if(charactersLeft < charactersPerLine) {
				inputText(input.substring(end, input.length()), color);
				break;
			}
			
			beg = end;
			end += charactersPerLine;
			
		} 
		
		// -------------------------------------------------------------
		
	}
	
	/**
	 * Use this function when interacting with text
	 * to avoid input-relating errors
	 * 
	 * @param text
	 * @param color
	 */
	private void inputText(String text, int color) {
		notebook.add(text);
		lineColors.add(color);
		scrollDown();
	}
	
	/**
	 *  Modify the range of messages rendered in the console
	 *  to only display the last x message in the arrayList
	 *  where x can be found in maxNumOfMsg
	 */
	private void scrollDown() {
		if(range.y > maxNumberOfMsg) {
			range.x++;
			range.y++;
		} else {
			range.y++;
		}
	}
	
	/**
	 *  Modify the range of messages rendered in the console
	 *  to only display the first x message in the arrayList
	 *  where x can be found in maxNumOfMsg
	 */
	private void scrollUp() {
		if(range.y > 0) {
			range.x--;
			range.y--;
		} else {
			range.y--;
		}
	}
	
	/**
	 * Main way of interacting with the console
	 * to display written text
	 * 
	 * @param text
	 * @param color
	 */
	public void write(String text, int color) {
		if(!text.isEmpty()) {
			sanitize(text, biggestLetter, screenWidth-10, color); 
		}
	}
	
	/**
	 * Adds a status bar to an array which will be rendered later
	 * 
	 * @param title
	 * @param bars
	 * @param color
	 */
	public void addBar(String title, int bars, int color) {
		// More that 4 bars are not allowed at the moment
		// TODO: Allow more bars
		if(totalBars > 4) return; 
		
		// TODO: Allow user to decide which image to use as a bar count
		barTitle[totalBars] = title;
		
		// Bars are stored in two variables in the following way
		// [   title 1,       title 2     ]
		// [bar#, barColor, bar#, barColor]
			
		barData[totalBars * 2] = bars;
		barData[(totalBars * 2) + 1] = color;
		
		++totalBars;
	}
	
	/**
	 * Create render format for the bars
	 * 
	 * @param title
	 * @param barQty
	 * @param symbol
	 * @return
	 */
	private String renderBar(String title, int barQty, char symbol) {
		StringBuilder bar = new StringBuilder();
		bar.append(title);
		bar.append(": ");
		for(int i = 0; i < barQty; i++)
			bar.append(symbol);
		return bar.toString();
		
	}
	
	/**
	 * Helper function, draws the bars to the screen in a horizontal grid
	 * 
	 * @param r
	 */
	private void drawBars(Renderer r) {
		if(totalBars <= 0) return;
		// Create text
		int xOffset = 0;
		
		// Get average size of each individual letter to best calculate the 
		// horizontal space required by the bar
		
		for(int i = 0; i < totalBars; i++) {
			r.drawText(renderBar(barTitle[i], barData[i*2], '$'), xOffset, 0, barData[(i*2) + 1]);
			int multiplier = barTitle[i].length() + barData[i*2];
			xOffset += (multiplier * biggestLetter) + BAR_MARGIN;
		}
	}
	
	
	/**
	 * Renders the console
	 * 
	 * @param r
	 */
	public void render(Renderer r) {
		drawBars(r);
		
		if(notebook.isEmpty()) return; // Saves a bit of memory
		
		
		int yOffset = 20;
		
		for(int i = range.x; i < range.y; i++) {
			r.drawText(notebook.get(i), 0, yOffset, lineColors.get(i));
			yOffset += r.getFont().getFontImage().getHeight() + MARGIN; // XXX: Change into actual used font
		}
	}
	
}
