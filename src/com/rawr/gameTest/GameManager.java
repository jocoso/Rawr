package com.rawr.gameTest;

import java.awt.event.KeyEvent;

import com.rawr.engine.AbstractGame;
import com.rawr.engine.Console;
import com.rawr.engine.GameContainer;
import com.rawr.engine.Renderer;
import com.rawr.engine.audio.SoundClip;

public class GameManager implements AbstractGame {
	private Console console;
	private SoundClip clip;
	
	public GameManager() {
		clip = new SoundClip("/audio/test.wav");
		clip.setVolumen(-20);
		
		console = new Console();
		console.addBar("Health", 10, Console.STANDARD_COLOR);
		console.addBar("Mana", 10, 0xff0276fd);
		console.addBar("Sanity", 10, 0xff71C671);
		console.addBar("Hunger", 10, 0xffFFCC11);
	}

	@Override
	public void update(GameContainer gc) {
		if(gc.getInput().isKeyUp(KeyEvent.VK_ENTER)) {
			String input = gc.getWindow().getTextField().getText();
			console.write(input, Console.STANDARD_COLOR);
			gc.getWindow().getTextField().setText("");
			
			if(input.contentEquals("music")) {
				clip.play();
			}
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
