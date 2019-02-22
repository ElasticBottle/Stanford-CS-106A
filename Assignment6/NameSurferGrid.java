import acm.graphics.*;

public class NameSurferGrid extends GCompound implements NameSurferConstants{
	
	/** The initial Y location on the grid for the Vertical lines*/
	private static final double INITY = 0;
	
	/** The initial X location on the grid for the Horizontal lines*/
	private static final double INITX = 0;
	
	/** The number of pixels reserved on the left off each label*/
	private static final double LABEL_MARGIN= 5;
	
	public NameSurferGrid (double width, double height) {
		drawVertLines(width, height);
		drawHorLines(width, height);
		addLabels(width, height);
	}

	/**
	 * Adds the year at the bottom of the screen, below the bottom horizontal line 
	 * @param width provides the width of the screen to allow the label to be positioned evenly along the x axis
	 * @param height provides the height of the screen to allow the label to be positioned evenly along the y axis
	 */
	private void addLabels(double width, double height) {
		double spacing = width / NDECADES;
		double XPos = LABEL_MARGIN;
		for ( int i = 0; i < NDECADES; i++) {
			int decade = START_DECADE + 10 * i;
			String labelText = "" + decade;
			GLabel decadeLabel = new GLabel (labelText);
			add(decadeLabel, XPos, height - decadeLabel.getDescent());
			XPos += spacing;
		}
	}
	
	/**
	 * Draws the top and bottom horizontal lines of the grid 
	 * @param width provides the x location in which the line is drawn too/from
	 * @param height provides the y location in which the bottom line is offset from via GRAPH_MARGIN_SIZE.
	 */
	private void drawHorLines(double width, double height) {
		GLine horLineTop = new GLine (INITX, GRAPH_MARGIN_SIZE, width, GRAPH_MARGIN_SIZE);
		GLine horLIneBot = new GLine (INITX, height - GRAPH_MARGIN_SIZE, width, height - GRAPH_MARGIN_SIZE);
		add(horLineTop);
		add(horLIneBot);
	}

	/**
	 * Draws the vertical lines of the grid by taking the width of the application screen and 
	 * dividing it by NDECADES to get the spacing between each of the lines.
	 * @param width provides the total width of the screen from which spacing between the vert lines are determined
	 * @param height provides the y location in which the line is drawn too/ from.
	 */
	private void drawVertLines(double width, double height) {
		double spacing = width / NDECADES;
		double XPos = spacing;
		for ( int i = 1; i < NDECADES; i++) {
			GLine vertLine = new GLine (XPos, INITY, XPos, height);
			add(vertLine);
			XPos += spacing;
		}
	}
}
