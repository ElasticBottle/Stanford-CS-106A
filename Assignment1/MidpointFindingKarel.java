/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		fillCornersWithBeepers();
		karelInTheMiddleByFillingStreetOneWithBeepers();
		moveBackwards();
		putBeeper();
		clearStreetOneOfOneBeeper();
		moveToCenter();
	}
	
	
	/*
	 * Pre-condition: Karel at 1,1, no beeepers along street 1
	 * Post-condition: the corners of street one will be filled with beepers, Karel will end on 2,1
	 */
	private void fillCornersWithBeepers() {
		putBeeper();
		moveToWall();
		putBeeper();
		turnAround();
		move();
	}
	
	/*
	 * Fills the street with Beepers from both corners, gradually filling it till it reach the center,which
	 * is filled last.
	 * Pre-condition: Karel at 2,1, corners of street is filled with Beepers
	 * Post-condition: the whole street will be filled with beepers, Karel ends one square to the right 
	 * of the middle (for odd street length)
	 */
	private void karelInTheMiddleByFillingStreetOneWithBeepers() {
		while(noBeepersPresent()) {
			moveToSpaceBeforeBeeper();
			putBeeper();
			turnAround();
			move();
		}
		
	}
	
	/*
	 * Pre-condition: Karel at a point along the street with no Beeper, but with beepers ahead of it.
	 * Post-condition: Karel at a point one space before a beeper, facing it.
	 */
	private void moveToSpaceBeforeBeeper() {
		while (noBeepersPresent()) {
			move();
		}
		moveBackwards();
	}
	
	/*
	 * Pre-condition:Karel at some location on a street with an empty position behind it
	 * Post-condition: Karel in the empty position directly behind it, facing the same direction
	 */
	private void moveBackwards() {
		turnAround();
		move();
		turnAround();
	}

	/*
	 * Pre-condition: Karel on a street filled with Beepers
	 * Post-condition: Karel will clear one beeper of each point of the street, ending at a boundary wall, 
	 * facing away from it.
	 */
	private void clearStreetOneOfOneBeeper() {
		moveToWall();
		turnAround();
		while(frontIsClear()) {
			pickBeeper();
			move();
		}
		pickBeeper();
		turnAround();
	}
	
	/*
	 * Pre-condition:Karel at some location on a street
	 * Post-condition:Karel next to wall, facing it
	 */
	private void moveToWall() {
		while(frontIsClear()) {
			move();
		}
	}
	
	/*
	 * Moves Karel to the Center of a street using the fact that there is a beeper at the center of the street
	 * Pre-condition: a beeper is located at the middle of the street that Karel is suppose to move to the center off.
	 * Post-condition: Karel in the middle of the street
	 */
	private void moveToCenter() {
		while(noBeepersPresent()) {
			move();
		}
	}
}
