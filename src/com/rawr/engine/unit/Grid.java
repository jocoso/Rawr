package com.rawr.engine.unit;

import com.rawr.engine.GameContainer;

/**
 * Grid class will give the user complete control in the way units will be displayed
 * in the main window, while also keeping all components organized and visible. 
 * 
 * In addition, Grid class will also be responsible for providing centralized rendering which 
 * will do all the heavy lifting regarding component scaling for the GameContainer 
 * before the program begins.
 * 
 * @author jocoso
 *
 */
public class Grid {
	
	public class TNode {
		public String data;
		public TNode left;  // Rows
		public TNode right; // Columns
		
		public TNode(String data) {
			this.data = data;
			left = right = null;
		}
		

		public void addRows(int qty) {
			TNode head = this;
				
			while(head.left != null) {
				head = head.left;
			}
			
			for(int i = 0; i < qty; i++) {
				head.left = new TNode(null);
				head = head.left;
			}
		}
		
		public void addColumns(int qty) {
			TNode head = this;
			
			while(head.right != null) {
				head = head.right;
			}
			
			for(int i = 0; i < qty; i++) {
				head.right = new TNode(null);
				head = head.right;
			}
		}
		
		public TNode getRow(int jumps) {
			// No jumps, no problem
			if(jumps == 0) return this;
			
			TNode head = this;
			
			for(int i = 0; i < jumps; i++) {
				head = head.left;
			}
			
			return head;
		}
		
		public TNode getColumn(int jumps) {
			// No jumps, no problem
			if(jumps == 0) return this;
			
			TNode head = this;
			
			for(int i = 0; i < jumps; i++) {
				head = head.right;
			}
			
			return head;
		}
		
		public void setUnit(String data) {
			this.data = data;
		}
		
		public String getUnit() {
			return data;
		}
		
	}
	
	private int screenWidth, screenHeight;
	private TNode root = null;
	
	public Grid(GameContainer gc) {
		
		screenWidth = gc.getWidth();
		screenHeight = gc.getHeight();
		
	}
	
	public TNode getRow(int jumps) {
		return root.getRow(jumps);
	}
	
	public TNode getColumn(int jumps) {
		return root.getColumn(jumps);
	}
	

	public void addRows(int qty) {
		// Root is considered row 0 column 0
		// To avoid index confusion the root will serve
		// as any other node.
		if(root == null) { 
			root = new TNode(null);
			qty--;
		}
		
		root.addRows(qty);
	}
	
	public void addColumns(int qty) {
		// Root is considered row 0 column 0
		// To avoid index confusion the root will serve
		// as any other node.
		if(root == null) {
			root = new TNode(null);
			qty--;
		}
		
		root.addRows(qty);
	}
	
}
