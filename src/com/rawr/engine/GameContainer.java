package com.rawr.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.rawr.engine.unit.Unit;

/**
 *  Works as a central hub for the entire engine.
 * @author jocoso
 *
 */
public class GameContainer implements Runnable {
	private Thread thread;
	private Window window;
	private Renderer renderer;
	private GeneralInput input;
	private AbstractGame game;
	
	// UNITS
	private final int MAX_NUMBER_OF_UNITS_PER_WINDOW = 5;
	private HashMap<Integer, Unit> units = new HashMap<Integer, Unit>();
	private int unitsOccupiedSpace = 0;
	
	// UNIT COMPONENT LOCATIONS
	public static final int NORTH  = 0;
	public static final int EAST   = 1;
	public static final int SOUTH  = 2;
	public static final int WEST   = 3;
	public static final int CENTER = 4;
	
	private boolean running = false;
	private final double UPDATE_CAP = 1.0 / 60.0;
	private int width = 320, height = 240;
	private float scale = 4f;
	private String title = "Josh's Text-Adventure Engine v1.0";
	
	
	public GameContainer(AbstractGame game) {
		this.game = game;
	}
	
	private boolean isAcceptedLocation(int location) {
		return location >= 0 && location < MAX_NUMBER_OF_UNITS_PER_WINDOW;
	}
	
	public void addUnit(Unit u, int location) {
		if(isAcceptedLocation(location)) {
			units.put(location, u);
		}
	}
	
	private void setUnits() {
		Iterator<Integer> itr = units.keySet().iterator();
		while (itr.hasNext()) 
		{
			Integer key = itr.next();
			units.get(key).set(this);
		}
	}
	
	private void renderUnits(Renderer r) {
		Iterator<Integer> itr = units.keySet().iterator();
		while (itr.hasNext()) 
		{
			Integer key = itr.next();
			units.get(key).render(r);
		}
	}
	
	private void updateUnits() {
		Iterator<Integer> itr = units.keySet().iterator();
		while (itr.hasNext()) 
		{
			Integer key = itr.next();
			units.get(key).update(this);
		}
	}
	
	public void start() {
		window = new Window(this);
		thread = new Thread(this);
		renderer = new Renderer(this);
		input = new GeneralInput(this);
		
		game.set(this);
		setUnits();
		
		thread.run(); // Main thread
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public void stop() {
		
	}
	
	public void dispose() {
		
	}

	public void run() {
		
		running = true;
		
		boolean render = false;
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		
		double frameTime = 0;
		int frames = 0;
		int fps = 0;
		
		while(running) {
			render = false;
			
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			
			while(unprocessedTime >= UPDATE_CAP) {
				unprocessedTime -= UPDATE_CAP;
				render = true;
				
				game.update(this);
				updateUnits();
				input.update();
				
				// TODO: Update game
				if(frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0;
				}
			}
			// TODO: Add code to only allow render when something changes
			if(render) {
				window.update();
				renderer.clear();
				game.render(this, renderer);
				renderUnits(renderer);
				window.update();
				frames++;
				
			} else {
				
				try {
					
					Thread.sleep(1);
					
				} catch(InterruptedException e) {
					
					e.printStackTrace(); 
					
				}
				
			}
		}
		
		dispose();
	}
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public Window getWindow() {
		return window;
	}

	public GeneralInput getInput() {
		return input;
	}
	
	public Renderer getRenderer() {
		return renderer;
	}

}
