/*
 * File Name: wordCount.java
 * =========================
 * Reads a file and reports how many lines, words, and characters appear in it. 
 */

import java.io.*;
import java.util.*;

import acm.program.*;
import acm.util.*;

public class wordCount extends ConsoleProgram{
	public void run () {
		 setFont("courier-24");
		 BufferedReader br = openFile ("File: ");
		 ArrayList<String> words = new ArrayList<String>();
		 readFileIntoAL(br, words);
		 int lines = countLines(words);
		 int numWords = countWords(words);
		 int characters = countChar(words);
		 println("Lines = " + lines + "\nWords = " + numWords + "\nChars = " + characters);
	}

	/**
	 * @param prompt which is posed to the user when asking them for what to open
	 * @return BufferedReader opened to a file
	 */
	private BufferedReader openFile(String prompt) {
		BufferedReader br = null;
		while (br == null) {
			try {
				String fileName = readLine(prompt);
				br = new BufferedReader (new FileReader (fileName));		
			} catch (IOException ex) {
				println ("Invalid file name");
			}
		}
		return br;
	}
	
	/**
	 * @param br is the BUfferedReader containing the file being read from
	 * @param words is the ArrayList from which the contents of the file will be read and stored into
	 */
	private void readFileIntoAL(BufferedReader br, ArrayList<String> words) {
		try {
			while(true) {
				words.add(br.readLine());
				if (words.get(words.size()-1) == null) {
					words.remove(words.size()-1);
					break;
				}
			}
			br.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		
	}

	/**
	 * @param words provides the ArrayList from which the number of lines is 
	 * determined through asking for the size of the ArrayList
	 * @return the size of the Arraylist which corresponds to the number of lines read from BufferedReader
	 */
	private int countLines(ArrayList<String> words) {
		return words.size();
	}

	/**
	 * @param words takes in an ArrayList containing the words that wants to be counted
	 * @return the number of words whereby anything separated by either a " " or a "'" will
	 * 		   be counted as a word through a StringTokenizer
	 */
	private int countWords(ArrayList<String> words) {
		int numWords = 0;
		for (int i = 0; i < words.size(); i++) {
			StringTokenizer tokenizer = new StringTokenizer(words.get(i), " '");
			numWords += tokenizer.countTokens();
		}
		return numWords;
	}

	/**
	 * @param words provides the list of words to be analyzed for the number of character.
	 * @return the counted number of characters, including spaces and punctuation, 
	 * 		   excluding the last characters at the end of each line.
	 */
	private int countChar(ArrayList<String> words) {
		int numChar = 0;
		for (int i=0; i < words.size(); i++) {
			for (int j = 0; j < words.get(i).length() - 1; j++) {
				numChar++;
			}
		}
		return numChar;
	}
}
