/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	
	public void run() {
		putBeeper();
		placeCheckerPattern();
		while (leftIsClear()) {
			nextRowFaceWest();
			putBeeperHereOrNot();
			placeCheckerPattern();
			if(rightIsClear()) {
				nextRowFaceEast();
				putBeeperHereOrNot();
				placeCheckerPattern();
			}
			else {
				turnAround();
			}
		}
	}
	
	/*
	 * Places a checker pattern in a given street
	 * Pre-condition: A street without an obstruction in front of Karel 
	 * Post-condition: Karel at the end of the street with a trail of beeper placed in a checker pattern
	 */
	private void placeCheckerPattern() {
		while(frontIsClear()) {
			move();
			doWePutBeeper();
		}
	}
	
	/*
	 * Moves to the next street above and face east
	 */
	private void nextRowFaceEast() {
		turnRight();
		move();
		turnRight();
	}	

	/*
	 * Moves to the next street above and face west
	 */
	private void nextRowFaceWest() {
		turnLeft();
		move();
		turnLeft();
	}
	
	/*
	 * looks back one space to determine if there was a beeper. If there is, move on. Else, place a beeper
	 * Pre-condition: a space behind Karel either with or without a beeper
	 * Post-condition: Karel remains where it is. A beeper will be in its place if there isn't a beeper in 
	 * the space behind it.
	 */
	private void doWePutBeeper() {
		moveBackwards();
		if(beepersPresent()) {
			move();
		}
		else {
			move();
			putBeeper();
		}
	}
	
	/*
	 * Pre-condition:karel has a space behind him
	 * post-condition karel will occupy the space behind him, facing the same direction it was facing.
	 */
	private void moveBackwards() {
		turnAround();
		move();
		turnAround();
	}
	
	/*
	 * Checks to see if there is a beeper to the left or the right of karel depending on orientation. If there is,
	 * do not put a beeper where Karel is standing. Else, a beeper is placed.
	 */
	private void putBeeperHereOrNot() {
		if (facingWest()) {
			checkBeeperOnLeft();
		}
		else if (facingEast()) {
			checkBeeperOnRight();
		}
	}
	
	/*
	 * checks to see if there is a beeper to the left of Karel. If there is, do not place a beeper where Karel is.
	 * Else, a beeper is placed.
	 */
	private void checkBeeperOnLeft() {
		turnLeft();
		move();
		if(beepersPresent()) {
			turnAround();
			move();
			turnLeft();
		}
		else {
			turnAround();
			move();
			turnLeft();
			putBeeper();
		}
	}
	
	/*
	 * checks to see if there is a beeper to the right of Karel. If there is, do not place a beeper where Karel is.
	 * Else, a beeper is placed.
	 */
	private void checkBeeperOnRight() {
		turnRight();
		move();
		if(beepersPresent()) {
			turnAround();
			move();
			turnRight();
		}
		else {
			turnAround();
			move();
			turnRight();
			putBeeper();
		}
	}
}
