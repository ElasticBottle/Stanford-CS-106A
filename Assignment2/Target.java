/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	private static final double OUTER_CIRCLE_RADIUS = 72;
	private static final double WHITE_CIRCLE_RADIUS = 46.8; //0,62inches
	private static final double INNER_CIRCLE_RADIUS = 21.6; //0.3inches
	
	public void run() {
		drawOuterCircle();
		drawWhiteCircle();
		drawInnerCirlce();
	}
	
	
	/*
	 * Draws a white circle with radius equal WHITE_CIRCLE_RADIUS using the GOval class.
	 * Center of circle is in the center of the display.
	 */
	private void drawWhiteCircle() {
		double whiteCircleXPOS = (getWidth()/2)- WHITE_CIRCLE_RADIUS;
		double whiteCircleYPOS = (getHeight()/2) - WHITE_CIRCLE_RADIUS;
		GOval whiteCircle = new GOval (whiteCircleXPOS, whiteCircleYPOS,2*WHITE_CIRCLE_RADIUS, 2*WHITE_CIRCLE_RADIUS);
		whiteCircle.setFilled(true);
		whiteCircle.setColor(Color.WHITE);
		add (whiteCircle);
	}
	
	/*
	 * Draws a Red circle with radius equal OUTER_CIRCLE_RADIUS using the GOval class.
	 * Center of circle is in the center of the display.
	 */
	private void drawOuterCircle() {
		double outerCircleXPOS = (getWidth()/2)- OUTER_CIRCLE_RADIUS;
		double outerCircleYPOS = (getHeight()/2) - OUTER_CIRCLE_RADIUS;
		GOval outerCircle = new GOval (outerCircleXPOS, outerCircleYPOS,2*OUTER_CIRCLE_RADIUS, 2*OUTER_CIRCLE_RADIUS);
		outerCircle.setFilled(true);
		outerCircle.setColor(Color.RED);
		add (outerCircle);
	}
	
	/*
	 * Draws a Red circle with radius equal INNER_CIRCLE_RADIUS using the GOval class.
	 * Center of circle is in the center of the display.
	 */
	private void drawInnerCirlce() {
		double innerCircleXPOS = (getWidth()/2)- INNER_CIRCLE_RADIUS;
		double innerCircleYPOS = (getHeight()/2) - INNER_CIRCLE_RADIUS;
		GOval innerCircle = new GOval (innerCircleXPOS, innerCircleYPOS, 2*INNER_CIRCLE_RADIUS, 2*INNER_CIRCLE_RADIUS);
		innerCircle.setFilled(true);
		innerCircle.setColor(Color.RED);
		add (innerCircle);
	}
}
