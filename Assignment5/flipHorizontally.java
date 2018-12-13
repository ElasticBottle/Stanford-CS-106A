/*
 * File Name: flipHorizontally.java
 * ================================
 *  reverses the picture in the horizontal dimension
 */

import acm.graphics.*;
import acm.program.*;

public class flipHorizontally extends GraphicsProgram {
	public void run () {
		GImage toFlip = new GImage ("The Milkmaid.JPG");
		GImage flipped = flipHor (toFlip);
		
		toFlip.scale(0.5);
		flipped.scale(0.5);
		
		add(toFlip, 10 , 10);
		add(flipped, toFlip.getWidth() + 50 , 10);
	}

	private GImage flipHor(GImage toFlip) {
		int [][] pixelArray = toFlip.getPixelArray();
		
		for (int i = 0; i < pixelArray.length; i++) {
			for (int j = 0; j < pixelArray[0].length/2; j++) {
				int temp = pixelArray[i][j];

				pixelArray[i][j] = pixelArray[i][pixelArray[0].length - j - 1];
				
				pixelArray[i][pixelArray[0].length - j - 1] = temp;
			}
		}
		
		return (new GImage (pixelArray));
	}
}
