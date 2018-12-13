/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.*;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		initialiseScoreArr();
		playGame();
	}

	/**
	 * initializes the matrix needed to keep track of all the scores within the game
	 * @param nPlayers2
	 */
	private void initialiseScoreArr () {
		scoreArr = new int [TOTAL][nPlayers];
		for (int i = 0; i < TOTAL; i++) {
			for (int j=0; j < nPlayers; j++) {
				scoreArr[i][j] = -1;
			}
		}
		 scoreArr[TOTAL - 1][playersTurn - 1] = 0;
		
	}

	/**
	 * Plays the Yahtzee game.
	 */
	private void playGame() {
		
		//runs for the number of player multiplied to the 13 scoring categories available
		for (int i = 0; i < nPlayers * N_SCORING_CATEGORIES; i++) {
			//Displays instructions for the user and waits for the user to click roll
			display.printMessage(playerNames[playersTurn - 1] + "'s turn! Click the \"Roll Dice\" button to roll the Dice");
			display.waitForPlayerToClickRoll(playersTurn); 
			
			//Gets the dive values when the user clicks roll and displays it for the user,
			///allowing them to re-roll if they choose to do so
			getDiceValues();
			display.displayDice(diceValues);
			TwoReRolls();
			
			//Updates the score that the user choose to score their dice rolls in and moves on to the next players turn (if any)
			updateScoresInCategory();
			updatePlayersTurn();
		}
		calculateBonuses();
		int winner = getwinner();
		display.printMessage(playerNames[winner] + " is the winner with a score of: " + scoreArr[TOTAL - 1][winner]); 
	}
	
	/**
	 * Initializes the N_DICE dice's value using a RandomGenerator and storing the individual 
	 * values into an array.
	 */
	private void getDiceValues() {
		diceValues = new int [N_DICE];
		
		for (int i = 0; i < N_DICE-2; i++) {
			diceValues[i] = 6; //rgen.nextInt(MIN_DICE_VAL, MAX_DICE_VAL);
		}
		diceValues[3]=5;
		diceValues[4] = 5;
	}

	/**
	 * Let's the user choose the dices that they want to update and does so accordingly, 
	 * updating the screen to reflect the changes
	 */
	private void TwoReRolls() {
		for(int i = 0; i < N_REROLLS; i++ ) {
			display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\"");
			display.waitForPlayerToSelectDice();
			for ( int j = 0; j < N_DICE; j ++) {
				if (display.isDieSelected(j)) {
					updateDice(j);
				}
			}
			display.displayDice(diceValues);
		}
	}
	
	/**
	 * updates the value given for a particular dice using RandomGenerator of a value between 1 and 6.
	 * @param j indicates the particular dice to be updated from the top of the screen down, counting from 0
	 */
	private void updateDice(int j) {
		rgen.setSeed(1);
		diceValues[j] = rgen.nextInt(MIN_DICE_VAL, MAX_DICE_VAL);
	}

	/**
	 * Prompts user to select a category to assign dice values. 
	 * Selected category is then selected and checked if has already been filled. 
	 * If the selected category has already been filled, the user is asked to choose again.
	 * Else, the score in that category is updated and the display is updated to reflect the changes 
	 * made to the that category and the total score itself.
	 */
	private void updateScoresInCategory() {
		display.printMessage("Select a category for this roll");
		score = 0;
		while(true) {
			category = display.waitForPlayerToSelectCategory();
			if (categoryNotFilled()) {
				updateScore();
				break;
			}
			display.printMessage("Category already choosen! Choose another category!");
		}
		display.updateScorecard(category, playersTurn, score);
		display.updateScorecard(TOTAL, playersTurn, scoreArr[TOTAL - 1][playersTurn - 1]);
	}

	/**
	 * Checks to see if the particular category selected has not been filled with a score
	 * @return true if the particular category has not been filled, false otherwise.
	 */
	private boolean categoryNotFilled() {
		if (scoreArr[category - 1][playersTurn - 1] == -1) return true;
		else return false;
	}

	/**
	 * Updates the score of the user for the choose category.
	 * score remains at zero if the dice configuration doesn't 
	 * meet the requirements of that particular category.
	 */
	private void updateScore() {
		if (checkForUpperCategory()) {
			score += YahtzeeScoreChecker.updateUpperCategory(diceValues, category);
			updateScoreArr();
		}
		else if (category == CHANCE) {
			score += YahtzeeScoreChecker.updateForChance(diceValues);
			updateScoreArr();
		}
		else if (category >= THREE_OF_A_KIND && category <= FULL_HOUSE || category == YAHTZEE){
			score += YahtzeeScoreChecker.checkForRest(diceValues, category);
			//println(score);
			updateScoreArr();
		}
		else if (category >= SMALL_STRAIGHT && category <= LARGE_STRAIGHT) {
			score += YahtzeeScoreChecker.checkForStraights(diceValues); 
			//println(score);
			updateScoreArr();
		}
	}

	/**
	 * Checks to see if the category selected is one of first six
	 * @return true if the category selected is from ONES to SIXES, false otherwise.
	 */
	private boolean checkForUpperCategory() {
		return category <= SIXES;
	}

	
	/**
	 * Update the scoreArr with the appropriate values in the appropriate category for the 
	 * particular player as well as the total score for that player.
	 */
	private void updateScoreArr() {
		scoreArr[category-1][playersTurn-1] += score + 1;
		scoreArr[TOTAL - 1][playersTurn - 1] += score;
	}
	
	/**
	 * Called at the end of a players turn so that the next player takes a turn.
	 * If the player is the last one in the group, the first player to go takes his/her turn again.
	 */
	private void updatePlayersTurn() {
		if (playersTurn == nPlayers) {
			playersTurn = 1;
		}
		else playersTurn++;
		
	}
	
	/**
	 * Tabulate the various values for the upper and lower score as well as the bonus category 
	 * if any.
	 */
	private void calculateBonuses() {
		int upperScore = 0, lowerScore = 0;
		for (int i = 0; i < nPlayers; i++) {
			
			//updates the upper score
			for (int j = 0; j < SIXES; j++) {
				upperScore += scoreArr[j][i];
			}
			scoreArr[UPPER_SCORE - 1][i] = upperScore;
			
			// rewards the upper score bonus if any
			if (upperScore > 63) {
				scoreArr[UPPER_BONUS - 1][i] = 35;
			}
			else scoreArr[UPPER_BONUS - 1][i] = 0;
			
			//Updates the lower score
			for (int j = THREE_OF_A_KIND - 1; j < CHANCE; j++) {
				lowerScore += scoreArr[j][i];
			}
			scoreArr[LOWER_SCORE - 1][i] = lowerScore;
			
			display.updateScorecard(UPPER_SCORE, playersTurn, scoreArr[UPPER_SCORE - 1][i]);
			display.updateScorecard(UPPER_BONUS, playersTurn, scoreArr[UPPER_BONUS - 1][i]);
			display.updateScorecard(LOWER_SCORE, playersTurn, scoreArr[LOWER_SCORE - 1][i]);
		}
		
	}

	/**
	 * Gets the highest scoring player by comparing the total scores across all the players
	 * @return the int representing the winning column scores.
	 */
	private int getwinner() {
		int winner = 0;
		for (int i = 0; i < nPlayers; i++) {
			if (scoreArr[TOTAL - 1][i] > scoreArr[TOTAL - 1][winner]) {
				winner = i;
			}
		}
		return winner;
	}

	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	
	private int[] diceValues;
	private int playersTurn = 1;
	private int score;
	private int[][] scoreArr;
	private int category;
}
