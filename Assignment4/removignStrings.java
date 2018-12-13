/*
 * File Name: removignStrings.java
 * ============================== 
 * removes all occurrences of the character ch from the string str.
 */

import acm.program.*;

public class removignStrings extends ConsoleProgram {
	
	public void run() {
		while (true) {       
			String str = readLine("Enter a string: ");       
			String remove = "";
			
			// A loop and a half to guard against invalid character input since remove is
			//actually a String here and not a char.
			while (true) {
				remove = readLine ("Enter a character to remove from the string above: ");
				if (remove.length() == 1) break;
				println("Input was not a character!");
			}
			
			if (str.length() == 0) break;
			println(removeAllOccurrences(str, remove.charAt(0)));    
		} 
		exit();
	}
	
	/** @param str, remove A String str, and a char remove containing the char that we want to remove from str
	 * @return str without any instance of remove*/
	private String removeAllOccurrences(String str, char remove) {
		String result="";
		for (int i=0; i<str.length(); i++) {
			char temp = str.charAt(i);
			if (temp == remove) {
				continue;
			}
			else {
				result+=temp;
			}
		}
		
		return result;
	}
}
