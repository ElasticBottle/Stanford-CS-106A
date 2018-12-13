/*
 * File Name: Histograms.java
 * ==========================
 * reads a list of exam scores from the file MidtermScores.txt (which contains
 * one score per line) and then displays a histogram of those numbers, divided 
 * into the ranges 0–9, 10–19, 20–29, and so forth, up to the range containing 
 * only the value 100.
 */

import java.util.*;
import java.io.*;
import acm.program.*;
import acm.util.*;

public class Histograms extends ConsoleProgram {
	
	private static final int SIZE = 11;
	
	public void run() {
		setFont("Times new roman-24");
		ArrayList<String> scores = new ArrayList<String>();
		String[] histogram = new String [SIZE];
		initialiseHistogram(histogram);
		
		readScores(scores);
		addStarsToHistogram (histogram, scores);
		printHistrogram(histogram);
	}
	
	/**
	 * Prints the String array containing the histogram
	 * @param histogram contains the histogram
	 */
	private void printHistrogram(String[] histogram) {
		for (int i = 0; i < histogram.length; i++) {
			println(histogram[i]);
		}
		
	}

	/**
	 * Adds a "*" for every occurrence of scores within a certain range
	 * @param histogram stores the "*" that is added
	 * @param scores provides the data for plotting the "*"
	 */
	private void addStarsToHistogram(String[] histogram, ArrayList<String> scores) {
		for (int i = 0; i < scores.size(); i++) {
			switch(scores.get(i).charAt(0)) {
				case '0':  histogram[0] += "*";
						   break;
				case '1':  if (scores.get(i).length() == 3) histogram[1] += "*"; //all the scores have an additional space after them
							else histogram[10] += "*"; // for the 100 case
							break;
				case '2':  histogram[2] += "*";
							break;
				case '3':  histogram[3] += "*";
							break;
				case '4':  histogram[4] += "*";
							break;
				case '5':  histogram[5] += "*";
							break;
				case '6':  histogram[6] += "*";
							break;
				case '7':  histogram[7] += "*";
							break;
				case '8':  histogram[8] += "*";
							break;
				case '9':  histogram[9] += "*";
							break;
			}
		}
	}

	/**
	 * Reads the list of score in MidtermScores.txt into the score ArrayList
	 * @param scores the ArrayList in which the scores are read into using BufferedReader
	 */
	private void readScores(ArrayList<String> scores) {
		try {
			BufferedReader br = new BufferedReader( new FileReader ("MidtermScores.txt"));
			while (true) {
				String temp = br.readLine();
				if (temp==null) break;
				scores.add(temp);
				//println(scores.get(scores.size()-1));
			}
			br.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	/**
	 * Initializes the initial params of the Histogram. I.E. the 01-09: ... 100:
	 * @param histogram which is modified directly within the method since changes made to object holds across methods.
	 */
	private void initialiseHistogram (String[] histogram) {
		for (int i = 0; i < histogram.length - 1; i++) {
			histogram[i] = i +"1-" + i +"9: ";
			//println(histogram[i]);
		}
		histogram[SIZE - 1] = "   100: ";
		//println(histogram[10]);
	}

}
