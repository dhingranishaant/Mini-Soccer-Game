package model;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class manages the movement of the ball, position, and color.
 */
public class SoccerBall {

	/**
	 * A private static final instance of this class
	 */
	private static final SoccerBall soccerBall = new SoccerBall();

	/**
	 * A Point object which contains the position of the ball.
	 */
	private Point position;

	/**
	 * A double value used for the velocity of the ball.
	 */
	private double velocity;

	/**
	 * A Color object used to depict the color of the ball.
	 */
	private final Color color;

	/**
	 * This is the constructor which initializes the color of the ball and resets its position.
	 */
	private SoccerBall() {
		color = Color.white;
		resetSoccerBall();
	}

	/**
	 * This method returns the instance of this class.
	 * @return an instance of the SoccerBall class.
	 */
	public static SoccerBall getSoccerBall() {
		return soccerBall;
	}

	/**
	 * This method manages the movement of the ball when it is shot by either the Goalkeeper or Striker.
	 * @param initialDistance is the initial distance of the ball.
	 * @param initialVelocity is the initial velocity of the ball.
	 * @param acceleration is the acceleration of the ball.
	 */
	public void moveBall(int initialDistance, double initialVelocity, double acceleration) {
		moveBallY(initialDistance);
		setVelocity(initialVelocity);
		Timer timer = new Timer();
		TimerTask repaintTask = new TimerTask() {
			@Override
			public void run() {
				if (Math.abs(velocity) < 2) {
					velocity = 0.0;
					timer.cancel();
				} else {
					velocity = velocity - acceleration;
				}
				moveBallY((int) velocity);
			}
		};
		timer.schedule(repaintTask, 0, 10);
	}

	/**
	 * This method moves the Y position of the ball.
	 * @param distance is how far upwards the ball will move.
	 */
	public void moveBallY(int distance) {
		if (getPosition().y + distance < 510 && getPosition().y - distance > 20) {
			setPosition(new Point(getPosition().x, getPosition().y - distance));
		} else {
			setVelocity(0.0);
		}
	}

	/**
	 * This method resets the ball to its initial positon of (480, 500).
	 */
	public void resetSoccerBall() {
		setVelocity(0.0);
		setPosition(new Point(480, 500));
	}

	/**
	 * This method checks if the ball is past the Goalkeeper line
	 * @return true if is past the line, false if not.
	 */
	public boolean onGoalkeeperSide() {
		return getPosition().y < 200;
	}

	/**
	 * This method checks if the ball is inside the goal.
	 * @return true if the ball is inside the goal, false if not.
	 */
	public boolean inGate() {
		return getPosition().x > 180 && getPosition().x < 400 && getPosition().y > 10 && getPosition().y < 60;
	}

	/**
	 * This method sets the velocity of the ball.
	 * @param velocity the given velocity of the ball.
	 */
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	/**
	 * This method gets the position of the ball.
	 * @return the Point object which contains the x and y position values of the ball.
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * This method sets the position of the ball.
	 * @param position the given position that the ball will be set to.
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * This method gets the color of the ball.
	 * @return the Color object of the ball.
	 */
	public Color getColor() {
		return color;
	}
}
