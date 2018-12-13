/*
 * File:Fib.java
 * =============
 * program displays the terms in the Fibonacci sequence, starting 
 * with Fib(0) and continuing as long as the terms are less than 10,000.
 */

import acm.program.ConsoleProgram;

public class Fib extends ConsoleProgram {
	
	private static final int MAX_TERM_VALUE = 10000;
	
	public void run () {
		println("This porgram lists the Fibonacci sequence");
		int i=0;
		while (fib(i)<MAX_TERM_VALUE) {
			println(fib(i));
			i++;
		}
	}
	
	/*
	 * Pre-Condition: Takes in an int to determine which sequence in the Fibonacci sequence is wanted
	 * Post-Condition: Returns that particular Fibonacci sequence
	 */
	private int fib (int i) {
		int[] arr = new int [100];
		
		//base case One
		if (i==0) {
			arr[0]=0;
			return arr[i];
		}
		
		//Base Case Two
		else if (i==1) {
			arr[i]=1;
			return arr[i];
		}
		
		//Recursive call
		else {
			arr[i] = fib(i-1) +fib(i-1);
			return arr[i];
		}
	}
}
