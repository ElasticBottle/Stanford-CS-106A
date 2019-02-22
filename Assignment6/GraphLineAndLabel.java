/*
 * Name: GraphLineAndLabel.java
 * ----------------------------
 * Creates the line and labels for a given entry to be displayed on the screen
 */

import java.awt.Color;

import acm.graphics.*;

public class GraphLineAndLabel extends GCompound implements NameSurferConstants{
	
	/** The number of pixels reserved on the left off each label*/
	private static final double LABEL_MARGIN= 3;
	/** Class Variable which allows the class to keep track of which color of line to be graphed next*/
	private static int COLOR = 0;
	
	public GraphLineAndLabel (double width, double height, NameSurferEntry entry) {
		addLine(width, height, entry);
		addLabel( width, height, entry);
		if (COLOR == 3) {
			COLOR = 0;
		}
		else COLOR ++;
	}

	/**
	 * Adds the label for each of the points of the graph indicating the name as well as the position of 
	 * the particular name for that decade.
	 * The position of the name adjust relative to the position of the screen
	 * @param width provides the width of the screen to allow scaling of the x pos 
	 * @param height provides the height of the screen to allow scaling of the y pos
	 * @param entry provides the detials for the name being graphed as well as th rankings for each year
	 */
	private void addLabel(double width, double height, NameSurferEntry entry) {
		double spacing = width / NDECADES;
		double XPos = LABEL_MARGIN;
		double unit = (height - 2 * GRAPH_MARGIN_SIZE) / MAX_RANK;
		
		for (int i = 0; i < NDECADES; i++) {
			//Assigning the text to the label and adding "*" if the name is unranked
			GLabel label = new GLabel (entry.getName() + " " + entry.getRank(i));
			if (entry.getRank(i) == 0) {
				label.setFont(entry.getName() + " *");
			}
			
			//Assigning the Ypos of the label, moving it to the bottom if unranked
			double YPos = GRAPH_MARGIN_SIZE + unit * entry.getRank(i) - label.getAscent();
			if (entry.getRank(i) == 0) {
				YPos = GRAPH_MARGIN_SIZE + unit * MAX_RANK - label.getAscent();
			}
			add(label, XPos, YPos);
			
			XPos += spacing;
		}
	}

	/**
	 * Creates the graph displaying the data of the relative position of a given name's popularity 
	 * relative to the height and width of the window
	 * @param width provides the actual full width of the window
	 * @param height provides the overall height of the window
	 * @param entry provides the data points to graph the graph for.
	 */
	private void addLine(double width, double height, NameSurferEntry entry) {
		double spacing = width / NDECADES;
		double startXPos = 0;
		double finalXPos = spacing;
		double unit = (height - 2 * GRAPH_MARGIN_SIZE) / MAX_RANK;
		
		for (int i = 1; i < NDECADES; i++) {
			
			//Setting of the Y location for the lines
			double startYPos = GRAPH_MARGIN_SIZE + unit * entry.getRank(i-1);
			double finalYPos = GRAPH_MARGIN_SIZE + unit * entry.getRank(i);
			
			if (entry.getRank(i -1) == 0) {
				startYPos = GRAPH_MARGIN_SIZE + unit * MAX_RANK;
			}
			if (entry.getRank(i) == 0) {
				finalYPos = GRAPH_MARGIN_SIZE + unit * MAX_RANK;
			}
				
			// creating the actual line and assigning its color
			GLine line = new GLine (startXPos, startYPos, finalXPos, finalYPos);
			switch (COLOR) {
			case 0: 
				line.setColor(Color.BLACK);
				break;
			case 1: 
				line.setColor(Color.RED);
				break;
			case 2:
				line.setColor(Color.BLUE);
				break;
			case 3:
				line.setColor(Color.MAGENTA);
				break;
			}
			
			add(line);
			
			// Incrementing the x values to prepare for the drawing of the next line
			startXPos += spacing;
			finalXPos += spacing;
		}
	}

}
