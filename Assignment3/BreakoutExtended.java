/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class BreakoutExtended extends GraphicsProgram {

/** Width and height of application window in pixels.  IMPORTANT NOTE:
  * ON SOME PLATFORMS THESE CONSTANTS MAY **NOT** ACTUALLY BE THE DIMENSIONS
  * OF THE GRAPHICS CANVAS.  Use getWidth() and getHeight() to get the 
  * dimensions of the graphics canvas. */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board.  IMPORTANT NOTE: ON SOME PLATFORMS THESE 
  * CONSTANTS MAY **NOT** ACTUALLY BE THE DIMENSIONS OF THE GRAPHICS
  * CANVAS.  Use getWidth() and getHeight() to get the dimensions of
  * the graphics canvas. */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;
	
/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	
/**Diameter of the ball in pixels*/
	private static final int BALL_DIAMETER = 2*BALL_RADIUS;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Pause between each update of the game */
	private static final int DELAY = 10;
	
/** innitialise the audioCLip bounce.au to COunceClip tp be played when the ball hits an object*/
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setup();
		setBallSpeed();
		waitForClick();
		while(!gameOver()) {
			moveBall();
			checkForCollision();
			GObject collider = getCollidingObject();
			if (collider == paddle) {
				bounceBall();
			}
			else if (collider != null){
				remove(collider);
				bounceBall();
			}
			pause(DELAY);
		}
	}
	
	/**
	 * Draws in the elements of the BreakOut game
	 * Includes: Bricks, Paddle, Ball
	 */
	private void setup() {
		drawBricks();
		drawPaddle();
		drawBall();
	}
	
	/**
	 * Draws in the breaks in the BreakOut Game
	 * Color of rows is determined by row number.
	 * Any additional row after row 10 will be set to CYAN.
	 */
	private void drawBricks() {
		for (int i=0; i<NBRICK_ROWS; i++) {
			if (i==0 || i==1) {
				layBricks(Color.RED, i);
			}
			else if (i ==2|| i==3) {
				layBricks(Color.ORANGE, i);
			}
			else if (i==4 || i==5) {
				layBricks(Color.YELLOW, i);
			}
			
			else if (i==6|| i==7) {
				layBricks (Color.GREEN, i);
			}
			else layBricks(Color.CYAN, i);
		}
	}
	
	/**
	 * Draws a row of bricks
	 * Pre-Condition: Takes in a color for the row and the row number to determine Y Pos.
	 * Post Conditions: Lays a row of NBRICKS_PER_ROW of Color color 
	 */
	private void layBricks(Color color, int row) {
		for (int i=0; i<NBRICKS_PER_ROW; i++) {
			GRect brick = new GRect ((getWidth()-NBRICKS_PER_ROW*BRICK_WIDTH- (NBRICKS_PER_ROW- 1)*BRICK_SEP)/2 + i*(BRICK_WIDTH+BRICK_SEP), 
									BRICK_Y_OFFSET+row*(BRICK_HEIGHT+BRICK_SEP), 
									BRICK_WIDTH, 
									BRICK_HEIGHT);
			brick.setFilled(true);
			brick.setColor(color);
			add(brick);
		}
	}
	
	/**
	 * Draws a paddle of width PADDLE_WIDTH and height PADDLE_HEIGHT
	 * adds a mouse listener to allow users to interact with the paddle
	 */
	private void drawPaddle() {
			paddle = new GRect ((getWidth()-PADDLE_WIDTH)/2, getHeight()-PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
			paddle.setFilled(true);
			paddle.setColor(Color.black);
			add(paddle);
			addMouseListeners();
			addKeyListeners();
	}
	
	
	/**
	 * triggers upon MouseClicked and checks to see if there is any object being clicked on at that location 
	 */
	public void mouseClicked(MouseEvent e) {
		clicked = new GPoint(e.getPoint());
		Gobj = getElementAt(clicked);
		//println(Gobj);
	}
	
	/**
	 * If there is an object (paddle) that was clicked on earlier, the code then moves it based on how much the mouse is moved
	 * To additional checks are put in place to ensure that the move will not bring the paddle outside the window boundaries.
	 */
	public void mouseDragged(MouseEvent e) {
		//println (Gobj != null);
		if (Gobj ==paddle) {
			
			//checks to see if the paddle is moved pass the left wall,
			// if it is, move the paddle only until the left wall
			if (Gobj.getX()+(e.getX()-clicked.getX())<0) {
				Gobj.move( -Gobj.getX(), 0);
			}
			
			//checks to see if the paddle is moved pass the right wall,
			//if it is, move the paddle only until the right wall
			else if (Gobj.getX() + (e.getX()-clicked.getX()) > getWidth()-PADDLE_WIDTH) {
				Gobj.move(getWidth()-PADDLE_WIDTH-Gobj.getX(), 0);
			}
			else {
				Gobj.move(e.getX()-clicked.getX(), 0);
			}
			clicked = new GPoint (e.getPoint());
		}
	}
	
	/**
	 * creates ball of radius BALL_RADIUS, fill it with BLACK color and adds it to the scene
	 */
	private void drawBall() {
		ball = new GOval (getWidth()/2-BALL_RADIUS, getHeight()/2-BALL_RADIUS, BALL_DIAMETER, BALL_DIAMETER);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		add(ball);
	}
	
	/**Initializes the x and y speed component of the ball in the instance variables vx and vy respectively.*/
	private void setBallSpeed() {
		vx = rgen.nextDouble(1.0, 3.0); 
		if (rgen.nextBoolean(0.5)) vx = -vx; 
		vy = 2.0;
	}
	
	 /** Update and move ball */ 
	private void moveBall() {
			ball.move(vx, vy);
	}
	
	
	/** Determine if collision with walls, update velocities   
	 * * and location as appropriate. */  
	private void checkForCollision(){
		double diff = 0;
		
		//checks to see if the ball hits the upper wall
		if (ball.getY()<0) {
			vy=-vy;
			// assume bounce will move ball an amount equal to the amount
			//it would have moved pass the floor.    
			diff = 0 - ball.getY();
			ball.move(0, 2 * diff); 
		}
		
		//checks to see if the ball hits the left wall
		else if (ball.getX()<0) {
			vx=-vx;
			diff=ball.getX();
			ball.move(-2 * diff ,0);
		}
		
		//checks to see if the ball hits the right wall
		else if (ball.getX()>getWidth()-BALL_DIAMETER) {
			vx=-vx;
			diff=ball.getX()-(getWidth()-BALL_DIAMETER);
			ball.move(-2 * diff, 0);
		}
		
	}
	
	/**
	 * Checks the four corners of the rectangle that is bounding the ball to see if there is any objects in that area.
	 */
	private GObject getCollidingObject() {
		//checks to see if the ball is in contact with any object at the top of the ball
		if (getElementAt(ball.getX()+BALL_RADIUS, ball.getY()-1)!=null) {
			return getElementAt(ball.getX()+BALL_RADIUS, ball.getY()-1);
		}
		
		//checks to see if the ball is in contact with any object at the right side of the ball
		else if (getElementAt(ball.getX()+BALL_DIAMETER+1, ball.getY()+BALL_RADIUS)!=null) {
			return getElementAt(ball.getX()+BALL_DIAMETER+1, ball.getY()+BALL_RADIUS);
		}
		
		//checks to see if the ball is in contact with any object at the bottom of the ball
		else if (getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+BALL_DIAMETER+1)!=null) {
			return getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+BALL_DIAMETER+1);
		}
		
		//checks to see if the ball is in contact with any object at the left side of the ball
		else if (getElementAt(ball.getX()-1, ball.getY()+BALL_RADIUS)!=null) {
			return getElementAt(ball.getX()-1, ball.getY()+BALL_RADIUS);
		}
		
		//Returns null since the ball's bounding box collided with nothing
		else {
			return null;
		}
	}
	
	/** transforms vy into -vy */
	private void bounceBall() {
		vy=-vy;
		bounceClip.play();
	}
	
	/** returns true when the ball's bounding box touches the lower wall, ending the game */
	private boolean gameOver() {
		if (ball.getY()>=getHeight()-BALL_DIAMETER) {
			add(finished()); //Adds in a "GAME OVER" label.
			return true;		
		}
		else {
			return false;
		}
	}
	
	
	/** Extension methods that extends the functionality of the original game assignment*/
	
	/** creates a "GAME OVER" label which is set to the  middle of the screen*/
	private GLabel finished() {
		GLabel finish = new GLabel ("GAME OVER");
		finish.setFont("Helvatica-50");
		finish.setLocation((getWidth()-finish.getWidth())/2, (getHeight()-finish.getAscent())/2);
		return finish;
	}
	
	/** Allows the user to use the left and right arrow key to move the paddle
	 * 'q' and 'w' are used to further subtract and add speed to the paddle respectively*/
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_Q: if (paddleSpeed > 0) {
				paddleSpeed -= 20.0;
				break;
			}
			case KeyEvent.VK_W: paddleSpeed += 20.0;
								break;
			case KeyEvent.VK_RIGHT: paddle.move(paddleSpeed, 0);
									break;
			case KeyEvent.VK_LEFT: paddle.move(-paddleSpeed, 0);
									break;
		}
	}
	
	/** Instance variable for the paddle to allow checking if the ball hits it outside of the drawPaddle method*/
	GRect paddle;
	private double paddleSpeed = 5.0;
	
	/** Instance variable to track the speed of the ball in the game */
	GOval ball;
	private double vx, vy;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	/** Instance Variables for the mouseEvents needed to move the paddle*/
	private GObject Gobj;
	private GPoint clicked;
}
