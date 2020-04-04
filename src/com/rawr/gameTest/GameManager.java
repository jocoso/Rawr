package com.rawr.gameTest;

import java.awt.event.KeyEvent;

import com.rawr.engine.AbstractGame;
import com.rawr.engine.Console;
import com.rawr.engine.GameContainer;
import com.rawr.engine.Renderer;

public class GameManager implements AbstractGame {
	private Console console;
	
	public GameManager() {
		console = new Console();
		console.addBar("Health", 10, Console.STANDARD_COLOR);
		console.addBar("Mana", 10, 0xff0276fd);
		console.addBar("Sanity", 10, 0xff71C671);
		console.addBar("Hunger", 10, 0xffFFCC11);
	}

	@Override
	public void update(GameContainer gc) {
		if(gc.getInput().isKeyUp(KeyEvent.VK_ENTER)) {
			console.write(gc.getWindow().getTextField().getText(), Console.STANDARD_COLOR);
			gc.getWindow().getTextField().setText("");
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		console.render(r);
	}
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
