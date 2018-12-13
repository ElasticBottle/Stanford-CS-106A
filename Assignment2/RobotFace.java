/*
 * File Name: RobotFace.java
 * =========================
 * Face consists of four parts—a head, two eyes, and a mouth
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class RobotFace extends GraphicsProgram {

	private static final int HEAD_HEIGHT = 500;
	private static final int HEAD_WIDTH = 300;
	private static final int EYE_RADIUS= 30;
	private static final int MOUTH_WIDTH = 150;
	private static final int MOUTH_HEIGHT = 50;
	
	public void run() {
		add(setupFace());
		add(setupLeftEye());
		add(setupRightEye());
		add(setupMouth());
	}
	
	private GRect setupFace() {
		GRect face = new GRect ((getWidth()-HEAD_WIDTH)/2, (getHeight()-HEAD_HEIGHT)/2, HEAD_WIDTH, HEAD_HEIGHT);
		face.setFilled(true);
		face.setFillColor(Color.gray);
		return face;
	}
	
	private GOval setupLeftEye() {
		GOval Leye = new GOval ((getWidth()-HEAD_WIDTH)/2 + (HEAD_WIDTH-4*EYE_RADIUS)/4, 
								(getHeight()-HEAD_HEIGHT/2 -2*EYE_RADIUS)/2 ,
								EYE_RADIUS*2, EYE_RADIUS*2);
		Leye.setFilled(true);
		Leye.setColor(Color.YELLOW);
		return Leye;
	}
	
	private GOval setupRightEye() {
		GOval Reye = new GOval ((getWidth()-HEAD_WIDTH)/2 + (HEAD_WIDTH-4*EYE_RADIUS)/4*3+EYE_RADIUS*2, 
								(getHeight()-HEAD_HEIGHT/2 -2*EYE_RADIUS)/2 ,
								EYE_RADIUS*2, EYE_RADIUS*2);
		Reye.setFilled(true);
		Reye.setColor(Color.YELLOW);
		return Reye;
	}
	
	private GRect setupMouth() {
		GRect mouth = new GRect ((getWidth()-MOUTH_WIDTH)/2, (getHeight()+ HEAD_HEIGHT/2-MOUTH_HEIGHT)/2, 
								  MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setFilled(true);
		mouth.setColor(Color.white);
		return mouth;
	}
}

