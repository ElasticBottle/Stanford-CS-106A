/*
 * File Name: commasToNumbers.java
 * ========================================
 * Takes a string of numbers and returns the string formed 
 * by inserting commas at every third position, starting on the right. 
 */

import acm.program.*;

public class commasToNumbers extends ConsoleProgram {

	public void run() {    
		while (true) {       
			String digits = readLine("Enter a numeric string: ");       
			if (digits.length() == 0) break;
			println(addCommasToNumericString(digits));    
			} 
		exit();
	}
	
	
	/**@param digits A String digits is passed from which , a comma is 
	 * inserted after every third number from the right if there are numbers to the left of it
	 * @return result, containing digits with a comma after every third character from the right
	 * as long as it doesn't result in the string starting with a ","*/
	private String addCommasToNumericString (String digits) {
		String result="";
		for (int i=1; i<=digits.length(); i++) {
			char temp = digits.charAt(digits.length()-i);
			if (i != 1 && i % 3 == 1) {
				result = temp + "," + result;
			}
			else {
				result = temp + result;
			}
		}
		return result;
	}
}
