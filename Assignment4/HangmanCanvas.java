/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	
	/* Constants for the simple version of the picture (in pixels) */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 800;
	
	private static final int WORD_BUFFER = 30;
	
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int ROPE_LENGTH = 18;
	private static final int BEAM_LENGTH = 144;
	private static final int BEAM_HEIGHT_IN_CANVAS = APPLICATION_HEIGHT / 2 - BODY_LENGTH - HEAD_RADIUS * 2 - ROPE_LENGTH;

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		drawScaffold();
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		remove(guessedWord);
		guessedWord.setLabel(word);
		guessedWord.setFont (style);
		add(guessedWord);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		addIncorrectGuessToScreen(letter);
		addNextBodyPart();
	}
	
	/**
	 * @param letter which is added to the string containing the wrong characters that have already been entered
	 * wrongGuessLabel is then updated to reflect the changes on the screen by removing the old one and adding the new one.
	 */
	private void addIncorrectGuessToScreen(char letter) {
		wrongGuesses += letter;
		remove(wrongGuessLabel);
		wrongGuessLabel.setLabel(wrongGuesses);
		wrongGuessLabel.setFont (style);
		add(wrongGuessLabel);
	}

	/**
	 * Tracks the number of wrong guesses using an instance var, numOfWrongGuess.
	 * Depending on the number, the various body parts gets added to the screen.
	 */
	private void addNextBodyPart () {
		numOfWrongGuess++;
		switch (numOfWrongGuess) {
			case 1:addHead();
					break;
			case 2:addBody();
					break;
			case 3: addArm("left");
					break;
			case 4: addArm("right");
					break;
			case 5: addLeg("left");
					break;
			case 6: addLeg("right");
					break;
			case 7: addFeet("left");
					break;
			case 8: addFeet("right");
					break;			
		}
	}
	
	/**
	 * Adds the head of the hangMan at the appropriate position
	 */
	private void addHead() {
		GOval head = new GOval (APPLICATION_WIDTH / 2 - HEAD_RADIUS, BEAM_HEIGHT_IN_CANVAS + ROPE_LENGTH, 
								HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		add(head);
	}
	
	/**
	 * Adds the body of the hangMan at the appropriate position
	 */
	private void addBody() {
		GLine body = new GLine (APPLICATION_WIDTH / 2 , BEAM_HEIGHT_IN_CANVAS + ROPE_LENGTH + HEAD_RADIUS * 2, 
								APPLICATION_WIDTH / 2 , BEAM_HEIGHT_IN_CANVAS + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH);
		add(body);
	}
	
	/**
	 * Takes in the initial starting and final position relative to the crotch of Mr HangMan
	 * @param initX relative starting X position of the limb to the center of the graphic window
	 * @param initY relative starting Y position of the limb to the crotch of Mr. HangMan
	 * @param finalX relative final X position of the limb to the center of the graphic window
	 * @param finalY relative final Y position of the limb to the crotch of Mr. HangMan
	 */
	private void addLimb (int initX, int initY, int finalX, int finalY) {
		GLine limb = new GLine (APPLICATION_WIDTH / 2 + initX, 
								BEAM_HEIGHT_IN_CANVAS + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + initY,
								APPLICATION_WIDTH / 2 + finalX , 
								BEAM_HEIGHT_IN_CANVAS + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + finalY);
		add(limb);
	}
	
	/**
	 * Adds the left and right arm of the hagnMan
	 * @param lr allows the method to determine whether to add the right or left arm
	 */
	private void addArm(String lr) {
		if (lr.equals("left")) {
			addLimb (0, - LEG_LENGTH , - UPPER_ARM_LENGTH , - LEG_LENGTH); //adds left upper arm
			addLimb (- UPPER_ARM_LENGTH , - LEG_LENGTH, - UPPER_ARM_LENGTH , LOWER_ARM_LENGTH - LEG_LENGTH  ); //adds left lower arm
		}
		
		else {
			addLimb ( 0 , - LEG_LENGTH , UPPER_ARM_LENGTH , - LEG_LENGTH); //adds right upper arm
			addLimb (UPPER_ARM_LENGTH , - LEG_LENGTH, UPPER_ARM_LENGTH , LOWER_ARM_LENGTH - LEG_LENGTH ); //adds right lower arm
		}
	}
	
	/**
	 * Adds the left and right leg of the hagnMan
	 * @param lr allows the method to determine whether to add the right or left leg
	 */	
	private void addLeg(String lr)  {
		if (lr.equals("left")) {
			addLimb ( 0 , 0 , - HIP_WIDTH , 0 ); //adds left upper leg			
			addLimb (- HIP_WIDTH , 0 , - HIP_WIDTH , LEG_LENGTH); // adds left lower leg
		}
		
		else {
			addLimb ( 0 , 0 , HIP_WIDTH , 0 ); //adds upper right leg
			addLimb ( HIP_WIDTH , 0 , HEAD_RADIUS, LEG_LENGTH ); // adds lower right leg
		}
	}

	/**
	 * Adds the left and right foot of the hagnMan
	 * @param lr allows the method to determine whether to add the right or left foot
	 */
	private void addFeet (String lr) {
		if (lr.equals("left")) {
			addLimb ( - HIP_WIDTH , LEG_LENGTH , - ( HIP_WIDTH + FOOT_LENGTH ) , LEG_LENGTH); //adds left foot
		}
		
		else {
			addLimb ( HIP_WIDTH , LEG_LENGTH , HIP_WIDTH + FOOT_LENGTH , LEG_LENGTH); //adds right foot
		}
	}
	
	
	/**
	 * Draws the initial scaffold with beam and rope onto the graphic canvas
	 */
	private void drawScaffold() {
		GLine rope = new GLine(APPLICATION_WIDTH / 2, BEAM_HEIGHT_IN_CANVAS + ROPE_LENGTH , 
							   APPLICATION_WIDTH / 2, BEAM_HEIGHT_IN_CANVAS);
		add(rope);
		
		GLine beam = new GLine (APPLICATION_WIDTH / 2, BEAM_HEIGHT_IN_CANVAS,
								APPLICATION_WIDTH / 2 - BEAM_LENGTH , BEAM_HEIGHT_IN_CANVAS);
		
		add(beam);
		
		GLine scaffold = new GLine (APPLICATION_WIDTH / 2 - BEAM_LENGTH , BEAM_HEIGHT_IN_CANVAS, 
									APPLICATION_WIDTH / 2 - BEAM_LENGTH , BEAM_HEIGHT_IN_CANVAS + SCAFFOLD_HEIGHT);
		
		add(scaffold);
	}
	
/* Private instance variables for noting the incorrect guesses */
	private String wrongGuesses = "";
	private String style = "courier-32";
	
	private GLabel guessedWord = new GLabel ("", 
			APPLICATION_WIDTH / 4 , 
			APPLICATION_HEIGHT * 3 / 4);

	private GLabel wrongGuessLabel = new GLabel ("", 
			APPLICATION_WIDTH / 4, 
			APPLICATION_HEIGHT * 3 / 4 + guessedWord.getAscent() + WORD_BUFFER);
	
	int numOfWrongGuess = 0;
	
}
