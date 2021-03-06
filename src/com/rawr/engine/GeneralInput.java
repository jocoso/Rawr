package com.rawr.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * A General Input manager. The class will grow/shrink in size depending on the 
 * requirement of the engine.
 * @author jocoso
 *
 */
public class GeneralInput implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	private GameContainer gc;
	
	private final int NUM_KEYS = 256;
	private boolean[] keys = new boolean[NUM_KEYS];
	private boolean[] keysLast = new boolean[NUM_KEYS];
	
	private final int NUM_BUTTONS = 5;
	private boolean[] buttons = new boolean[NUM_BUTTONS];
	private boolean[] buttonsLast = new boolean[NUM_BUTTONS];
	
	private int mouseX, mouseY;
	private int scroll;
	
	private StringBuilder userKeyboardInput;
	
	public GeneralInput(GameContainer gc) {
		this.gc = gc;
		userKeyboardInput = new StringBuilder();
		gc.getWindow().getCanvas().addKeyListener(this);
	}
	
	public void update() {
		scroll = 0;
		
		for(int i = 0; i < NUM_KEYS; i++) {
			keysLast[i] = keys[i];
		}
	}
	
	public boolean isKey(int keyCode) {
		return keys[keyCode];
	}
	
	public boolean isKeyUp(int keyCode) {
		return !keys[keyCode] && keysLast[keyCode];
	}
	
	public boolean isKeyDown(int keyCode) {
		return keys[keyCode] && !keysLast[keyCode];
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll = e.getWheelRotation();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println((int)e.getKeyChar());
		if(e.getKeyChar() - 32 >= 0 && e.getKeyChar() != 8) { // Avoid newLine character
			userKeyboardInput.append(e.getKeyChar());
		} else if(e.getKeyChar() == 8 && userKeyboardInput.length() > 0) { // Backspace
			userKeyboardInput.delete(userKeyboardInput.length() - 1, userKeyboardInput.length());
		}
	}
	
	public String getDump() {
		String foo = userKeyboardInput.toString();
		
		userKeyboardInput = new StringBuilder();
		return foo;
	}
	
	public String getRawDump() {
		return userKeyboardInput.toString();
	}
}
