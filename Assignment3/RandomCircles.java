/*
 * File Name: RandomCircles.java
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.*;
import acm.util.*;

public class RandomCircles extends GraphicsProgram{
	
	/* Minimum pixels for the radius of a random circle*/
	private static final int MIN_RADIUS = 5;
	
	/* Maximum pixels for the radius of a random circle*/
	private static final int MAX_RADIUS = 60;
	
	/* number of circles generated*/
	private static final int NO_CIRCLES= 100;
	
	public void run () {
		for (int i=0; i<NO_CIRCLES; i++) {
			double tempRadius = randomRadius();
			GOval circle = randomCircle(randomXPos(tempRadius), randomYPos(tempRadius), tempRadius, randomColor());
			add(circle);
		}
		while (true) {
			circle.move(10,10);
		}
	}
	
	/* 
	 * Creates a circle given the x, y location, the radius, and a color	.
	 */
	private GOval randomCircle(double cx, double cy, double radius, Color color) {
		GOval circle = new GOval (cx, cy, 2*radius, 2*radius);
		circle.setFilled(true);
		circle.setColor(color);
		return circle;
	}
	
	/*
	 * Returns a random Xpos for the circle within the boundary of the screen.
	 */
	private double randomXPos (double radius) {
		return rgen.nextDouble(0, getWidth()-2*radius);
	}

	/*
	 * Returns a random Ypos for the circle within the boundary of the screen.
	 */
	private double randomYPos (double radius) {
		return rgen.nextDouble(0, getHeight() - 2*radius);
	}
	
	/*
	 * Returns a random radius for the circle within 5 - 50 pixels.
	 */
	private double randomRadius () {
		return rgen.nextDouble(MIN_RADIUS, MAX_RADIUS);
	}
	
	/*
	 * Returns a random color
	 */
	private Color randomColor () {
		return rgen.nextColor();
	}
	
	/* Private Instance Variable */
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
