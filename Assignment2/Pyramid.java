/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		for (int i=BRICKS_IN_BASE; i > 0; i--) {
			fillInRowsOfBricks(i);
		}
	}
	
	/*
	 * Fills a row of bricks
	 * Pre-Condition: Takes in an int that specifies how many bricks to fill in the row
	 * Post-Condition: produces a row of bricks with an equal spacing to the 
	 * 				   left and right of the screen for the said number of bricks
	 */
	private void fillInRowsOfBricks(int numToFill) {
			for (int i=0; i<numToFill; i++) {
				double distToNextBrick= i*BRICK_WIDTH; //the next brick is placed BRICK_WIDTH pixels after the previous one
				putBrick(findStartingXPos(numToFill)+distToNextBrick,findStartingHeight(numToFill)); 
			}
	}
	
	/*
	 * Finds the starting x coordinate of the first brick in a row by dividing the difference 
	 * between the screen width and the overall length of the row of bricks by two 
	 */
	private double findStartingXPos(int numToFill) {
		double widthOfDisp = getWidth();
		double x = (widthOfDisp-(numToFill*BRICK_WIDTH))/2;
		return x;
	}
	
	/*
	 * Finds the starting Y-coordinate of the first brick in the row by
	 * multiplying the number of bricks in the row by the BRICK_HEIGHT constant
	 * since Y coordinate increases from top left hand, the first row is 14
	 * BRICK_HEIGHT down.
	 */
	private double findStartingHeight(int row) {
		double y = row*BRICK_HEIGHT;
		return y;
	}
	
	/*
	 * Adds a brick to the canvas given the x and y coordinate of a brick
	 * Pre-Condition: two double specifying the x and y coordinate of a brick
	 * Post-Condition:a brick of BRICK_HEIGHT and BRICK_WIDTH on the canvas at 
	 * 				  the specified x and y coordinate
	 */
	private void putBrick(double x, double y) {
		GRect brick = new GRect (x, y ,BRICK_WIDTH, BRICK_HEIGHT);
		add (brick);
	}
}