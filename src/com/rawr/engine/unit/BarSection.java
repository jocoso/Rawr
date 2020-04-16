package com.rawr.engine.unit;

import com.rawr.engine.GameContainer;
import com.rawr.engine.Renderer;

public class BarSection extends Unit {
	private final int BAR_SECTION_SIZE = 4;
	private final int BAR_MARGIN = 15;
	private final int MARGIN = 5;
	
	private final int minimalSize = 100;
	private int height = 100;
	private int width;
	
	private String[] barTitle;
	private int[] barData;
	private int totalBars = 0;
	private Integer biggestLetter = null;
	
	public BarSection() {
		barTitle = new String[BAR_SECTION_SIZE];
		barData = new int[BAR_SECTION_SIZE * 2];
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
	
	
	public void render(Renderer r) {
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

	@Override
	public void set(GameContainer gc) {
		width = gc.getWidth();
	}

	@Override
	public void update(GameContainer gc) {
		// TODO Bar changing code goes here
		
	}

}
