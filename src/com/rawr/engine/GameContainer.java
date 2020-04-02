package com.rawr.engine;

import java.awt.event.KeyEvent;

/**
 *  Works as a central hub for the entire program.
 * @author jocoso
 *
 */
public class GameContainer implements Runnable {
	private Thread thread;
	private Console window;
	private Renderer renderer;
	private GeneralInput input;
	private boolean running = false;
	private final double UPDATE_CAP = 1.0 / 60.0;
	private int width = 320, height = 240;
	private float scale = 4f;
	private String title = "Josh's Text-Adventure Engine v1.0";
	
	public GameContainer() {
		
	}
	
	public void start() {
		window = new Console(this);
		thread = new Thread(this);
		renderer = new Renderer(this);
		input = new GeneralInput(this);
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
				
				//if(input.isKeyUp(KeyEvent.VK_A)) System.out.println("A pressed");
				if(input.isKeyUp(KeyEvent.VK_ENTER)) System.out.println(window.getTextField().getText());;
				input.update();
				
				// TODO: Update game
				if(frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0;
				}
			}
			
			if(render) {
				renderer.clear();
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
	
	public static void main(String[] arg) {
		GameContainer gc = new GameContainer();
		gc.start();
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

	public Console getWindow() {
		return window;
	}
}
