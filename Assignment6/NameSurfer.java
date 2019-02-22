/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {
	    drawGUI();
	    nameBase = new NameSurferDataBase (NAMES_DATA_FILE);
	    graph = new NameSurferGraph(); 
	    add(graph);
	}

	/* Method: drawGUI() */
	 /** Draws the GUI in the SOUTHERN region of the window which allows the user to interact with the program
	 */
	private void drawGUI() {
		add(new JLabel ("Name:"), SOUTH);
		
		nameField = new JTextField(NAME_LENGTH);
		nameField.addActionListener(this);
		nameField.setActionCommand("Graph");
		
		JButton graph = new JButton("Graph");
		JButton remove =  new JButton ("Remove");
		JButton clear = new JButton("Clear");
		
		add(nameField, SOUTH);
		add(graph, SOUTH);
		add(remove, SOUTH);
		add(clear, SOUTH);
		addActionListeners();
	}
	
	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals("Graph")) {
			String name = nameField.getText();
			NameSurferEntry entry= nameBase.findEntry(name);
			if (entry != null) {
				graph.addEntry(entry);
			}
		}
		
		else if (cmd.equals("Remove")) {
			String name = nameField.getText();
			NameSurferEntry entry= nameBase.findEntry(name);
			if (entry != null) {
				graph.removeEntry(entry);
			}
		}
		
		else if (cmd.equals("Clear")) {
			graph.clear();
		} 
	}
	
	/*Instance Variables*/
	private JTextField nameField;
	private NameSurferDataBase nameBase;
	private NameSurferGraph graph;
}
