/*
 * File Name ; naeCountsPreHash.java
 * ===============================
 *  a program that asks the user for a list of names 
 *  (one per line) until the user enters a blank line 
 *  (i.e., just hits return when asked for a name).  
 *  At that point the program should print out how many 
 *  times each name in the list was entered.
 */

import java.util.*;
import acm.program.*;

public class nameCountsPreHash extends ConsoleProgram{
	public void run() {
		setFont("Default-20");
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> nameCount = new ArrayList<Integer>();
		
		while (true) {
			String name = readLine("Enter name: ");
			if (name.equals("")) break;
			else if (names.indexOf(name) == -1) {
				names.add(name);
				nameCount.add(1);
			}
			else {
				increaseNameCount(name, names, nameCount);
			}
			//println(names.indexOf(name));
		}
		printNameCount(names, nameCount);
	}

	private void increaseNameCount(String name, ArrayList<String> names, ArrayList<Integer> nameCount) {
		nameCount.set(names.indexOf(name), nameCount.get(names.indexOf(name)) + 1);
	}

	private void printNameCount(ArrayList<String> names, ArrayList<Integer> nameCount) {
		for (int i = 0; i < names.size(); i ++) {
			println ("Entry [" + names.get(i) + "] has count " + nameCount.get(i));
		}
		
	}
}
