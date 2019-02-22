import acm.graphics.*;

public class BoxDiagram extends GCompound{
	public BoxDiagram (String text) {
		GRect box = new GRect (BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel (text);
		label.setFont("Courier-20");
		
		add(box, 0, 0);
		add(label, (BOX_WIDTH - label.getWidth()) / 2, (BOX_HEIGHT + label.getAscent()) / 2);
	}
	
	private static final double BOX_WIDTH = 120; 
	private static final double BOX_HEIGHT = 50;
}
