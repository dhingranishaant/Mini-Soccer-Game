package model.players;

import model.SoccerBall;

import java.awt.*;

/**
 * This class extends the GamePlayer class but allows the player to shoot the ball and move in any direction.
 */
public class Striker extends GamePlayer {
	/**
	 * Initializes the Striker with a name and color.
	 * @param name the name of the Striker
	 * @param color the color of the Striker
	 */
	public Striker(String name, Color color) {
		super(name, color);
	}

	/**
	 * Move the player left
	 */
	@Override
	public void moveLeft() {
		if (getPlayerPosition().x - 10 > 0) {
			setPlayerPosition(new Point(getPlayerPosition().x - 5, getPlayerPosition().y));
		}
	}

	/**
	 * Move the player right
	 */
	@Override
	public void moveRight() {
		if (getPlayerPosition().x + 50 < 600) {
			setPlayerPosition(new Point(getPlayerPosition().x + 5, getPlayerPosition().y));
		}
	}

	/**
	 * Move the player up
	 */
	@Override
	public void moveUp() {
		if (getPlayerPosition().y - 5 > 200) {
			setPlayerPosition(new Point(getPlayerPosition().x, getPlayerPosition().y - 5));
		}
	}

	/**
	 * Move the player down
	 */
	@Override
	public void moveDown() {
		if (getPlayerPosition().y + 50 < 500) {
			setPlayerPosition(new Point(getPlayerPosition().x, getPlayerPosition().y + 5));
		}
	}

	/**
	 * Shoot the ball
	 */
	@Override
	public void shootBall() {
		SoccerBall.getSoccerBall().moveBall(60, 5.0, 0.05);
	}

	/**
	 * Set the initial position of the Striker to (500, 450).
	 */
	@Override
	public void setInitialPosition() {
		setPlayerPosition(new Point(500, 450));
	}

	/**
	 * Convert the Striker class to a String containing the player name and goals.
	 * @return String containing the name and score of the player.
	 */
	@Override
	public String toString() {
		return playerName + " scored " + playerStatistics.toString() + " goals";
	}
}
