/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.io.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		NameSurferGrid grid = new  NameSurferGrid(getWidth(), getHeight());
		add(grid);
	}
	
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		displayedOnScreen.clear();
		update();
	}
	
	public void removeEntry(NameSurferEntry entry) {
		displayedOnScreen.remove(entry.getName());
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		displayedOnScreen.put(entry.getName(), entry);
		update();
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		NameSurferGrid grid = new  NameSurferGrid(getWidth(), getHeight());
		for( String name : displayedOnScreen.keySet()) {
			GraphLineAndLabel graphedStats = new GraphLineAndLabel(getWidth() , getHeight(), displayedOnScreen.get(name));
			add(graphedStats);
		}
		
		/* alternative method of implementing iterators
		Iterator<String> it = displayedOnScreen.keySet().iterator();
		while (it.hasNext()) {
			String nameToDraw = it.next();
			GraphLineAndLabel graphedStats = new GraphLineAndLabel(getWidth() , getHeight(), displayedOnScreen.get(nameToDraw));
			add(graphedStats);
		}*/
		
		add(grid);
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private Map <String, NameSurferEntry> displayedOnScreen = new HashMap <String, NameSurferEntry>();
}
