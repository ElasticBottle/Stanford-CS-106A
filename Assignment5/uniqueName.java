/*
 * File Name: uniqueNamee.java
 * =============================
 * asks the user for a list of names (one per line) until 
 * the user enters a blank line (i.e., just hits return 
 * when asked for a name).  At that point the program should 
 * print out the list of names entered, where each name is 
 * listed only once (i.e., uniquely) no matter how many times 
 * the user entered the name in the program.  
 */

import java.util.*;
import acm.program.*;

public class uniqueName extends ConsoleProgram {
	public void run() {
		setFont ("couurier-20");
		ArrayList<String> names = new ArrayList<String>();
		String name = null;
		String test = null;
		
		while (true) {
			name = readLine("Enter name: ");
			if (name.equals("")) break;
			if (names.indexOf(name) == -1) {
				names.add(name);
			}
		}
		
		printUniqueNames(names);
	}

	private void printUniqueNames(ArrayList<String> names) {
		println("Unique name list contains:");
		for (int i = 0; i < names.size(); i++) {
			println(names.get(i));
		}
	}

}
