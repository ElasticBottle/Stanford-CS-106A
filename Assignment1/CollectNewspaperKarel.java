/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	
	public void run() {    
		walkTillWallInFront();
		turnRight();
		walkTillNoWallOnLeft();
		turnLeft();
		move();
		pickBeeper();
		turnAround();
		move();
		turnRight();
		walkTillWallInFront();
		turnLeft();
		walkTillWallInFront();
	}
	
	private void walkTillWallInFront() {
		while(frontIsClear()==true) {
			move();
		}
	}
	
	private void walkTillNoWallOnLeft() {
		while (leftIsBlocked()==true) {
			move();
		}
	}
}
