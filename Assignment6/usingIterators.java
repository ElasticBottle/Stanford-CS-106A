/*
 * File Name: usingIterators.java
 * ===================================
 *  Allows using to add Rectangular boxes with 
 *  labels and move them around or remove them according to will.
 */

import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.applet.*;
import javax.swing.*;
import acm.program.*;
import acm.graphics.*;

public class usingIterators extends GraphicsProgram {
	public void init() {
		initialiseTextField();
		InitialiseButtons();
		addActionListeners();
		addMouseListeners();
	}
	
	private void initialiseTextField() {
		nameField = new JTextField (30);
		nameField.addActionListener(this);
		nameField.setActionCommand("Add");
		
		add(new  JLabel ("Name"), SOUTH);
		add(nameField, SOUTH);
	}

	private void InitialiseButtons() {
		addBox = new JButton ("Add");
		remove = new JButton ("Remove");
		clear = new JButton ("Clear");
		
		add (addBox, SOUTH);
		add (remove, SOUTH);
		add (clear, SOUTH);
	}
		
	public void actionPerformed (ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Add")) {
			println(nameField.getText() + " is printed");
			addNewTextBox(nameField.getText());
		}
		else if (cmd.equals("Remove")) {
			remove(nameField.getText());
		}
		else {
			removeAllBox();
		}
	}
	
	/**
	 * Adds a new box diagram to GCanvas by creating a new box of type GCompound with the text centered in the middle
	 * New boxes are added to the middle of GCanvas
	 * @param text is the label that is added within the box.
	 */
	private void addNewTextBox(String text) {
		BoxDiagram box = new BoxDiagram (text);
		add(box, (getWidth() - box.getWidth()) / 2, (getHeight() - box.getHeight()) / 2);
		diagrams.put(text, box);
	}
	
	/**
	 * Removes the box with a specified String text
	 * @param text specifies the box to be removed
	 */
	private void remove(String text) {
		Iterator <String> it = diagrams.keySet().iterator();
		while (it.hasNext()) {
			if (it.next().equals(text)) {
				remove(diagrams.get(text));
				diagrams.remove(text);
				break;
			} 
		}
	}
	
	private void removeAllBox() {
		for( String boxNames : diagrams.keySet()) {
			println(boxNames);
			remove(diagrams.get(boxNames));
		}
		diagrams.clear();
	}
	
	public void mousePressed (MouseEvent e) {
		location = new GPoint (e.getPoint());
		obj = getElementAt(location);
	}
	
	public void mouseDragged(MouseEvent e) {
		if (obj != null) {
			println("test");
			obj.move(e.getX() - location.getX(), e.getY() - location.getY());
			location = new GPoint(e.getPoint());
		}
	}
	
	public void mouseClicked (MouseEvent e) {
		if (obj != null) {
			obj.sendToFront();
		}
	}

	/*Instance Variables*/
	private JButton addBox;
	private JButton remove;
	private JButton clear;
	private JTextField nameField;
	
	private Map<String, BoxDiagram> diagrams = new HashMap<String, BoxDiagram>();
	
	private GObject obj;
	private GPoint location;
}