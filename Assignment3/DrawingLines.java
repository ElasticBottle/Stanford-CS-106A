/*
 * File Name: DrawingLiens.java
 * ============================
 *  Pressing the mouse button sets the starting point for the line.  
 *  Dragging the mouse moves the other endpoint around as the drag 
 *  proceeds. Releasing the mouse fixes the line in its current position 
 *  and gets ready to start a new line. 
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;

public class DrawingLines extends GraphicsProgram{
	
	/*
	 * (non-Javadoc)
	 * @see acm.program.GraphicsProgram#init()
	 * initializes the program by enabling the mouse listener
	 */
	public void init () {
		addMouseListeners();
	}
	
	/*
	 * Activate on mouse press to draw a line at given location.
	 */
	public void mousePressed(MouseEvent e) {
		line = new GLine (e.getX(), e.getY(), e.getX(), e.getY());
		add(line);
	}
	
	/*
	 * (non-Javadoc)
	 * @see acm.program.Program#mouseDragged(java.awt.event.MouseEvent)
	 */
	public void mouseDragged (MouseEvent e) {
		line.setEndPoint(e.getX(), e.getY());
	}
	
	private GLine line;
}
