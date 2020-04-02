package com.rawr.gameTest;

import java.awt.event.KeyEvent;

import com.rawr.engine.AbstractGame;
import com.rawr.engine.GameContainer;
import com.rawr.engine.Renderer;

public class GameManager implements AbstractGame {
	
	public GameManager() {
		
	}

	@Override
	public void update(GameContainer gc) {
		if(gc.getInput().isKeyUp(KeyEvent.VK_ENTER)) System.out.println("Enter was pressed");
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
