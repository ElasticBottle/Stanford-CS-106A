/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println ("Enter values to compute Pythagorean Theorem");
		int a = readInt("a: ");
		int b = readInt("b: ");
		double c = pythagoras (a, b);
		println("c = " + c);
	}
	
	private double pythagoras (int a , int b) {
		double c = Math.sqrt(a*a+b*b);
		return c;
	}
}
