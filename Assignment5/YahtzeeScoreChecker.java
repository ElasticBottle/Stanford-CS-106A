/*
 * File Name: YahtzeeScoreChecker
 * =================================
 * Enables you to check the validiity of the category given the dice category 
 * in the Yahtzee game.
 */

import acm.program.*;

public class YahtzeeScoreChecker extends ConsoleProgram implements YahtzeeConstants {
	public YahtzeeScoreChecker () {
	}
	
	/**
	 * Updates the score given the selected upper category.
	 */
	public static int updateUpperCategory(int [] diceValues, int category) {
		int score = 0;
		for (int i = 0; i < N_DICE; i++) {
			if (diceValues[i] == category) {
				score += category;
			}
		}
		return score;
	}
	
	/**
	 * Updates the score for the CHANCE category.
	 */
	public static int updateForChance(int [] diceValues) {
		int score = 0;
		for (int i = 0; i < N_DICE; i++) {
			score += diceValues[i];
		}
		return score;
	 }
	
	/**
	 * Checks the dice roll for small and large straight
	 */
	public static int checkForStraights(int [] diceValues) {
		int[] diceValueCount = new int[MAX_DICE_VAL];
		int straightCounter= 0;
		for (int i = 0; i < N_DICE; i++) {
			diceValueCount[diceValues[i]-1] += 1;
		}
		for (int i = 0; i < MAX_DICE_VAL; i++) {
			if (diceValueCount[i] == 1) {
				straightCounter++;
			}
		}
		if (straightCounter == 5) {
			return 40;
		}
		else if (straightCounter == 4 
				&& (diceValueCount[MIN_DICE_VAL - 1] == 0 
				|| diceValueCount[MAX_DICE_VAL - 1] == 0)) {
			return 30;
		}
		return 0;
	}

	/**
	 * Checks if the selected category for Yahtzee, four of a kind, three of a kind, and full house are valid
	 * Scores are assigned accordingly.
	 */
	public static int checkForRest(int [] diceValues, int category) {
		int[] diceValueCount = new int[MAX_DICE_VAL];
		int score = 0;
		for (int i = 0; i < N_DICE; i++) {
			diceValueCount[diceValues[i]-1] += 1;
		}
		for (int i = 0; i < MAX_DICE_VAL; i++) {
			//Checking for Yahtzee
			if (category == YAHTZEE && diceValueCount[i] == 5) {
				return 50;
			}
			
			//Checking for Three of a kind and full house
			else if ((category == THREE_OF_A_KIND 
					|| category == FULL_HOUSE )
					&& diceValueCount[i] >= 3) {
				score += (i + 1) * diceValueCount[i];
				
				//To check if the dice roll is actually FULL_HOUSE.
				for (int j = 0; j < MAX_DICE_VAL; j++) {
					if (diceValueCount[j] == 2) {
						return 25;
					}
				}
				return score;
			}
			
			//Checking for four of a kind
			else if (category == FOUR_OF_A_KIND && diceValueCount[i] >=4) {
				return (i + 1) * diceValueCount[i];
			}
			
		}
		return 0;
	}
}