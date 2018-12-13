/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.event.*;
import java.awt.*;

public class Hangman extends ConsoleProgram {

    private static final int NUM_OF_GUESSESS = 8;
    public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 800;
	
    public void init() {    
    	canvas = new HangmanCanvas();    
    	add(canvas); 
    } 
    
    
    public void run() {
    	canvas.reset();
    	startGame();
	}
    
    private void startGame() {
    	setFont("Times_New_Roman-24");
    	println("Welcome to Hangman!");
    	int seed = rgen.nextInt(0, word.getWordCount());
		String wordToGuess = word.getWord(seed);
		String answer= initialiseAnswer(wordToGuess.length());
    	int guessLeft = NUM_OF_GUESSESS;
    	canvas.displayWord(answer);
    
    	while(true) {
       		println("The word now looks like this: " +answer);
    		printGuessLeftLine(guessLeft);
    		getUserGuess();
    		
    		if (isGuessCorrect(guess.charAt(0), wordToGuess)) {
    			println("That guess is correct");
    			answer = updateAnswer(guess.charAt(0), answer, wordToGuess);
    			canvas.displayWord(answer);
    		}
    		
    		else {
    			println("There are no " + guess.toUpperCase() + "\'s in the word");
    			canvas.noteIncorrectGuess(guess.charAt(0));
    			guessLeft--;
    		}
  
    		if (guessLeft == 0 ||
    				answer.equalsIgnoreCase(wordToGuess)) break;
    	}

    	if (guessLeft == 0) {
    		println("You're completely hung. \nThe word was: " + wordToGuess + "\nYou lose.");
    	}
    	else {
    		println("You guess the word: " + answer + "\nYou win.");
    	}
    }
    
    /** 
     * @param length is used to determine the number of dashes that is present in the initial string 
     * for which users only knows the number of letters in the word as indicated by the dashes.
     * @return a String with the number of dashes equivalent to length
     */
    private String initialiseAnswer(int length) { 
    	String Dashes="";
    	for (int i=0; i<length; i++) {
    		Dashes+="-";
    	}
    	return Dashes;
    }
    
    
     /** @param guessLeft representing the number of guesses left
     * @return nothing, but outputs a grammatically correct string indicating to user
     * how many guesses they are left with
     */
    private void printGuessLeftLine (int guessLeft) {
    	if (guessLeft != 1) {
			println("You have " + guessLeft +" guesses left.");
		}
		else println("You only have one guess left.");
    }
    
    /**
     * A loop and a half to read the user's input and prompts them for another input if they
     * did not enter a character
     */
    private void getUserGuess() {
    	while(true) {
    		guess = readLine ("Your guess: ");
    		if (guess.length() == 1 && 
    				(guess.charAt(0) >= 65 && guess.charAt(0) <=90) || 
    				(guess.charAt(0) >= 96 && guess.charAt(0) <=122)) break;
    		else {
    			println ("please enter a valid alphabet character.	");
    		}
    	}
    }   
    
    /**
     * @param guess compares char guess to the string wordToGuess character by character
     * @param wordToGuess is the string containing the various char in which char guess is matched against
     * @return true if char guess matches a character in wordToGuess, false otherwise
     */
    private boolean isGuessCorrect(char guess, String wordToGuess) {
    	for (int i=0; i<wordToGuess.length(); i++) {
    		char temp = wordToGuess.charAt(i);
    		if (temp==Character.toUpperCase(guess)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * @param guess is the current guess of the user that is correct and will be added to be shown in the answer at 
     * all appropriate places
     * @param oldAnswer is the state of the user's answer before their new guess that is correct
     * @param wordToGuess is the actual word that they are guessing and is used to figure out the position that 
     * the guess should appear in along the line of dashes
     * @return the oldAnswer of the user, with the dashes of guess swapped for the actual character of guess.
     */
    private String updateAnswer (char guess, String oldAnswer, String wordToGuess) {
    	String updatedAnswer = "";
    	for (int i =0; i<wordToGuess.length(); i++) {
    		char temp = wordToGuess.charAt(i);
    		if (temp!=Character.toUpperCase(guess)) {
    			updatedAnswer += oldAnswer.charAt(i);
    		}
    		else {
    			updatedAnswer += temp;
    		}
    	}
    	return updatedAnswer;
    }

    /*private instance variables*/
    private HangmanLexicon word = new HangmanLexicon();
    private RandomGenerator rgen = RandomGenerator.getInstance(); 
	private String guess = "";
	private HangmanCanvas canvas = new HangmanCanvas(); 
    
}

