/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run () {
		while (frontIsClear()==true) {
			fillColumn();
			nextColumn();
		}
		fillColumn();
	}

	private void fillColumn() {
		turnLeft();
		while(frontIsClear()==true) {
			if (noBeepersPresent()==true) {
				putBeeper();
			}
			move();
		}
		if (noBeepersPresent()==true) {
			putBeeper();
		}
		descend();
	}
	
	private void descend() {
		turnAround();
		while (frontIsClear()==true) {
			move();
		}
		turnLeft();
	}
	
	private void nextColumn() {
		for (int i=0; i<4; i++) {
			move();
		}
	}
}

