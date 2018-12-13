/*
 *  File:defendDemocracyKarel.java
 *  ---------------------------------
 *   the voter’s intent is indicated by the status of the square in the middle of the rectangle, 
 *   which is where the stylus makes contact with the card.  
 *   If there is a beeper in that position, Karel must assume that the voter did not
 *   intend to cast a vote in that column and move on to the next.  
 *   If there is no beeper in the center square, 
 *   Karel must check the other two squares in the ballot and remove any and all beepers 
 *   so that the ballot can be counted correctly.s
 */
import stanford.karel.SuperKarel;

public class defendDemocracyKarel extends SuperKarel {

	public void run() {
		move();
		while(frontIsClear()) {
			checkBallotValidity();
			moveToNextBallot();
		}
	}
	
	/*
	 * Checks to see if the Ballot is to be counted or not by determining if there is a beeper 
	 * in the middle of the rectangle. If the vote is to be counted, the beepers on the left 
	 * and right of Karel is cleared if they aren't already.
	 */
	private void checkBallotValidity() {
		if (noBeepersPresent()) {
			clearLeftSquare();
			clearRightSquare();
		}
	}
	
	/*
	 * Pre-condition: Karel in the middle of the ballot rectangle.
	 * Post-condition:Karel back at the starting position, any beeper initially to the left of 
	 * Karel is now removed
	 */
	private void clearLeftSquare() {
		turnLeft();
		move();
		while(beepersPresent()) {
			pickBeeper();
		}
		turnAround();
		move();
	}
	
	/*
	 * Pre-condition: Karel in the middle of the ballot rectangle.
	 * Post-condition:Karel back at the starting position, any beeper initially to the right of 
	 * Karel is now removed
	 */	
	private void clearRightSquare() {
		move();
		while(beepersPresent()) {
			pickBeeper();
		}
		turnAround();
		move();
		turnRight();
	}
	
	
	/*
	 * Pre-condition: Karel in the middle of the ballot rectangle.
	 * Post-condition: Karel in the middle of the ballot rectangle in front of it. If there isn't anymore
	 * ballot, Krel stops, facing the wall at the end of the street.
	 */
	private void moveToNextBallot() {
		move();
		if (frontIsClear()) {
			move();
		}
	}
}