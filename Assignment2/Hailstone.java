/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 * reads in a number from the user and then displays the Hailstone
 *  sequence for that number, just as in Hofstadter’s book, 
 *  followed by a line showing the number of steps taken to reach 1. 
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	private static final int FINAL_VAL = 1;
	
	public void run() {
		int n = readInt("Enter a nuumber: ");
		int steps = 0;
		while (n!=1) {
			if (n==FINAL_VAL) {
				break;
			}
			else if (isOdd(n)) {
				n = hofstadterOdd(n);
				steps++;
			}
			else if (isEven(n)) {
				n = hofstadterEven(n);
				steps++;
			}
		}
		println("The process took " +steps +" to reach 1");
	}
	
	/*
	 * Takes in an integer and returns true if the number gives a remainder of 1 when divided by 2
	 */
	private boolean isOdd(int n) {
		if (n%2==1) {
			return true;
		}
		
		else return false;
	}
	
	/*
	 * Takes in an integer and returns true if the number is divisible by 2
	 */
	private boolean isEven(int n) {
		if (n%2==0) {
			return true;
		}
		
		else return false;
	}

	/*
	 * Pre-Condition: Takes n, an odd number and does the operation 3*n +1
	 * Post-Conditons: outputs " is odd, so I make 3n+1: ", and returns a new n after doing the above operation
	 */
	private int hofstadterOdd(int n) {
		int newN = 3*n+1;
		println(n + " is odd, so I make 3n+1: " + (3*n+1));
		return newN;
	}

	/*
	 * Pre-Condition: Takes n, an even number and does the operation n/2
	 * Post-Conditons: outputs " is even, so I take half: ", and returns a new n after doing the above operation
	 */
	private int hofstadterEven(int n) {
		int newN = n/2;
		println(n + " is even, so I take half: " + n/2);
		return newN;
	}
}

