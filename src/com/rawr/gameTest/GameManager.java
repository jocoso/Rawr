package com.rawr.gameTest;

import com.rawr.engine.AbstractGame;
import com.rawr.engine.GameContainer;
import com.rawr.engine.Renderer;
import com.rawr.engine.audio.SoundClip;
import com.rawr.engine.unit.Console;
import com.rawr.engine.unit.Grid;

public class GameManager implements AbstractGame {
	private SoundClip clip;
	private Console console;
	private Grid grid;
	
	public GameManager() {
		clip = new SoundClip("/audio/test.wav");
		clip.setVolumen(-20);
		console = new Console();
		
	}
	
	@Override
	public void set(GameContainer gc) {
		gc.addUnit(console, GameContainer.CENTER);
		grid = new Grid(gc);
		/*
		 * +-------------+
		 * +   r0 c0     +
		 * +-------------+
		 * +   r1 c0     +
		 * +-------------+
		 */
		grid.addRows(2);
		
		
		/*
		 * +---------------+
		 * + r0 c0 | r0 c1 +
		 * +---------------+
		 * + r1 c0 | r1 c1 +
		 * +---------------+
		 */
		grid.getRow(0).addColumns(1);
		grid.getRow(1).addColumns(1);
		
		grid.getRow(0).setUnit("r0 c0");
		grid.getRow(1).setUnit("r1 c0");
		
		grid.getRow(0).getColumn(1).setUnit("r0 c1");
		grid.getRow(1).getColumn(1).setUnit("r1 c1");
		
		System.out.println(grid.getRow(0).getUnit());
		System.out.println(grid.getRow(1).getUnit());
		System.out.println(grid.getRow(0).getColumn(1).getUnit());
		System.out.println(grid.getRow(1).getColumn(1).getUnit());
	}
	
	@Override
	public void update(GameContainer gc) {
	
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		
	}
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
