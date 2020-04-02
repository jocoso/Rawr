package com.rawr.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JTextField;
/**
 * Window console of the game.
 * @author jocoso
 *
 */
public class Console {
	private JFrame frame;
	private BufferedImage image;
	private Canvas canvas;
	private Graphics g;
	private BufferStrategy bs;
	private JTextField textField;
	
	
	public Console(GameContainer gc) {
		
		image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
		canvas = new Canvas();
		textField = new JTextField(20);
		
		Dimension d = new Dimension((int) (gc.getWidth() * gc.getScale()), (int) (gc.getHeight() * gc.getScale()));
		
		canvas.setPreferredSize(d);
		canvas.setMaximumSize(d);
		canvas.setMinimumSize(d);
		
		frame = new JFrame(gc.getTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(textField, BorderLayout.SOUTH);
		frame.add(canvas, BorderLayout.CENTER); 
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
	}
	
	public void update() {
		// XXX: awt.Graphics class has problems displaying canvas
		// Without this block of code it will display a blank Frame
		// Every other execution.
		do {
			g = bs.getDrawGraphics();
			g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		} while(bs.contentsRestored() || bs.contentsLost()); 
		
		bs.show();
		
	}

	public BufferedImage getImage() {
		return image;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public JTextField getTextField() {
		return textField;
	}

}
