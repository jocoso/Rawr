package com.rawr.engine;

import java.util.ArrayList;

/**
 * Organizes the text displayed in the console
 * 
 * @author jocoso
 *
 */
public class Console {
	public static final int STANDARD_COLOR = 0xffff0000;
	private final int MARGIN = 5;
	private final int BAR_MARGIN = 15;
	private final int BAR_SECTION_SIZE = 4;
	
	private Integer biggestLetter = null;
	
	private ArrayList<String> notebook;
	private ArrayList<Integer> lineColors;
	private String[] barTitle;
	private int[] barData;
	private int totalBars = 0;
	private int screenWidth;
	
	public Console(GameContainer gc) {
		notebook = new ArrayList<String>();
		lineColors = new ArrayList<Integer>();
		barTitle = new String[BAR_SECTION_SIZE];
		barData = new int[BAR_SECTION_SIZE * 2];
		
		biggestLetter = 0;
		int[] widths = gc.getRenderer().getFont().getWidths();
			
		for(int i = 0; i < widths.length; i++) {
			biggestLetter += widths[i];
		}
		
		biggestLetter /= widths.length;
		
		screenWidth = gc.getWidth();
		
	}
	
	public void add(String text) {
		write(text, STANDARD_COLOR);
	}
	
	private void sanitize(String input, int charSize, int screenWidth, int color) {
		// Input do not surpass the screenWidth
		if(input.length() * charSize < screenWidth) {
			notebook.add(input);
			lineColors.add(color);
			return;
		}
		
		int charactersLeft = input.length();
		int charactersPerLine = screenWidth / charSize;
		int beg = 0;
		int end = charactersPerLine;
		
		while(true) {	
			
			notebook.add(input.substring(beg, end));
			lineColors.add(color);
			charactersLeft -=  charactersPerLine;
			
			if(charactersLeft < charactersPerLine) {
				notebook.add(input.substring(end, input.length()));
				lineColors.add(color);
				break;
			}
			
			beg = end;
			end += charactersPerLine;
			
		} 
		
	}

	
	public void write(String text, int color) {
		if(!text.isBlank()) {
			sanitize(text, biggestLetter, screenWidth-10, color); 
		}
	}
	
	public void addBar(String title, int bars, int color) {
		barTitle[totalBars] = title;
		barData[totalBars * 2] = bars;
		barData[(totalBars * 2) + 1] = color;
		++totalBars;
	}
	
	private String generateBar(String title, int barQty, char symbol) {
		StringBuilder bar = new StringBuilder();
		bar.append(title);
		bar.append(": ");
		for(int i = 0; i < barQty; i++)
			bar.append(symbol);
		return bar.toString();
	}
	
	private void drawBars(Renderer r) {
		if(totalBars <= 0) return;
		// Create text
		int xOffset = 0;
		
		// Get average size of each individual letter to best calculate the 
		// horizontal space required by the bar
		
		for(int i = 0; i < totalBars; i++) {
			r.drawText(generateBar(barTitle[i], barData[i*2], '$'), xOffset, 0, barData[(i*2) + 1]);
			int multiplier = barTitle[i].length() + barData[i*2];
			xOffset += (multiplier * biggestLetter) + BAR_MARGIN;
		}
	}
	
	
	public void render(Renderer r) {
		drawBars(r);
		
		if(notebook.isEmpty()) return; // Saves a bit of memory
		
		int yOffset = 20;
		
		for(int i = 0; i < notebook.size(); i++) {
			
			r.drawText(notebook.get(i), 0, yOffset, lineColors.get(i));
			yOffset += r.getFont().getFontImage().getHeight() + MARGIN; // XXX: Change into actual used font
			
		}
	}
	
}
