/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	private static final double BOX_WIDTH = 150;
	private static final double BOX_HEIGHT = 30;
	private static final double BOX_HOR_SPACING= 15;
	private static final double BOX_VERT_SPACING= 50;
	private static final double BOTTOM_BUFFER = 20;
	
	public void run() {
		DrawGPBox();
		DrawCPBox();
		DrawDPBox();
		DrawPBox();
	}
	
	/*
	 * Draws the GraphiProgram Box and add in the text and line extending 
	 * from it to program bow
	 */
	private void DrawGPBox() {
		double XPos = (getWidth()/2)- (3*BOX_WIDTH/2)- BOX_HOR_SPACING ;
		double YPos = getHeight() - BOX_HEIGHT- BOTTOM_BUFFER;
		GRect Box = new GRect (XPos, YPos, BOX_WIDTH, BOX_HEIGHT);
		add(Box);
		drawGPLabelAndLine(XPos, YPos);
	}
	
	/*
	 * Draws the ConsoleProgram Box and add in the text and line extending 
	 * from it to program bow
	 */
	private void DrawCPBox() {
		double XPos = (getWidth()/2)- (BOX_WIDTH/2);
		double YPos = getHeight() - BOX_HEIGHT- BOTTOM_BUFFER;
		GRect Box = new GRect (XPos, YPos, BOX_WIDTH, BOX_HEIGHT);
		add(Box);
		drawCPLabelAndLine(XPos, YPos);
	}
	
	/*
	 * Draws the DialougeProgram Box and add in the text and line extending 
	 * from it to program box
	 */
	private void DrawDPBox() {
		double XPos = (getWidth()/2) + (BOX_WIDTH/2) + BOX_HOR_SPACING ;
		double YPos = getHeight() - BOX_HEIGHT- BOTTOM_BUFFER;
		GRect Box = new GRect (XPos, YPos, BOX_WIDTH, BOX_HEIGHT);
		add(Box);
		drawDPLabelAndLine(XPos, YPos);
	}
	
	/*
	 * Draws the Program Box and add in the text
	 */
	private void DrawPBox() {
		double XPos = (getWidth()/2)- (BOX_WIDTH/2);
		double YPos = getHeight() - 2*BOX_HEIGHT- BOTTOM_BUFFER-BOX_VERT_SPACING;
		GRect Box = new GRect (XPos, YPos, BOX_WIDTH, BOX_HEIGHT);
		add(Box);
		drawPLabel(XPos, YPos);
	}
	
	
	private void drawGPLabelAndLine(double XPos, double YPos) {
		GLabel GP = new GLabel ("GraphicsProgram");
		double labelYPos = getHeight()-BOTTOM_BUFFER-BOX_HEIGHT/2+GP.getAscent()/2;
		double labelXPos = getWidth()/2-BOX_HOR_SPACING-BOX_WIDTH-GP.getWidth()/2;
		GP.setLocation(labelXPos, labelYPos);
		add(GP);
		GLine GPLine = new GLine (XPos+(BOX_WIDTH/2), YPos, getWidth()/2, YPos-BOX_VERT_SPACING);
		add(GPLine);
	}
	
	private void drawCPLabelAndLine(double XPos, double YPos) {
		GLabel GP = new GLabel ("ConsoleProgram");
		double labelYPos = getHeight()-BOTTOM_BUFFER-BOX_HEIGHT/2+GP.getAscent()/2;
		double labelXPos = getWidth()/2-GP.getWidth()/2;
		GP.setLocation(labelXPos, labelYPos);
		add(GP);
		GLine GPLine = new GLine (XPos+(BOX_WIDTH/2), YPos, getWidth()/2, YPos-BOX_VERT_SPACING);
		add(GPLine);
	}
	
	private void drawDPLabelAndLine(double XPos, double YPos) {
		GLabel GP = new GLabel ("DialougeProgram");
		double labelYPos = getHeight()-BOTTOM_BUFFER-BOX_HEIGHT/2+GP.getAscent()/2;
		double labelXPos = getWidth()/2+BOX_WIDTH+BOX_HOR_SPACING-GP.getWidth()/2;
		GP.setLocation(labelXPos, labelYPos);
		add(GP);
		GLine GPLine = new GLine (XPos+(BOX_WIDTH/2), YPos, getWidth()/2, YPos-BOX_VERT_SPACING);
		add(GPLine);
	}
	
	private void drawPLabel(double XPos, double YPos) {
		GLabel GP = new GLabel ("Program");
		double labelYPos = getHeight()-BOTTOM_BUFFER-BOX_VERT_SPACING-(3*BOX_HEIGHT/2)+GP.getAscent()/2;
		double labelXPos = getWidth()/2-GP.getWidth()/2;
		GP.setLocation(labelXPos, labelYPos);
		add(GP);
	}
	
}

