/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int SENTINEL=0;
	public void run() {
		println("This program finds the largest adn smallest numbers.");
		int largest=0, smallest=0;
		while (true) {
			int value = readInt("? ");
			if (value == SENTINEL) {
				break;
			}
			else if (largest == 0 && smallest ==0) {
				largest = value;
				smallest = value;
			}
			else if (value >largest) {
				largest = value;
			}
			else if (value < smallest) {
				smallest = value;
			}
		}
		output(checkForInput(largest, smallest), largest, smallest);
	}
	
	/*
	 * checks if any input has been given before the SENTINEL value was inputed
	 * returns true if input has been given before SENTINEL value
	 * false otherwise, and "No input" is printed
	 */
	private boolean checkForInput(int largest, int smallest) {
		if (largest == 0 && smallest == 0) {
			println("No input");
			return false;
		}
		else return true;
	}
	
	/*
	 * prints the required statement in two separate lines
	 */
	private void output(boolean b, int largest, int smallest) {
		if (b==true) {
			println ("smallest: " + smallest);
			println ("largest: " + largest);
		}
	}
}

