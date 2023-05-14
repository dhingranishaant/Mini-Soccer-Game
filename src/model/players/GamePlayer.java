package model.players;

import model.SoccerBall;

import java.awt.*;

/**
 * This class contains all the actions and values a player has.
 */
public abstract class GamePlayer implements Comparable<GamePlayer> {

	/**
	 * The name of this player.
	 */
	protected final String playerName;

	/**
	 * The color of this player.
	 */
	protected final Color playerColor;

	/**
	 * The position of this player.
	 */
	protected Point playerPosition;

	/**
	 * The statistics of this player.
	 */
	protected final PlayerStatistics playerStatistics;

	/**
	 * This is the constructor that initializes the player and assigns it a name and color.
	 * @param name is the name of the player
	 * @param color is the color of the player
	 */
	public GamePlayer(String name, Color color) {
		playerName = name;
		playerColor = color;
		playerStatistics = new PlayerStatistics();
		setInitialPosition();
	}

	/**
	 * Checks if the player has the ball.
	 * @return true if the distance between the player and ball is less than 55, false if not.
	 */
	public boolean isPlayerHasBall() {
		Point playerPositionCenter = new Point(getPlayerPosition().x + 15, getPlayerPosition().y + 30);
		return playerPositionCenter.distance(SoccerBall.getSoccerBall().getPosition()) < 55;
	}

	/**
	 * Grabs the ball for the player
	 */
	public void grabsBall() {
		SoccerBall ball = SoccerBall.getSoccerBall();
		if (getPlayerPosition().x + 15 > ball.getPosition().x) {
			ball.setPosition(new Point(getPlayerPosition().x - 10, getPlayerPosition().y + 55));
		} else {
			ball.setPosition(new Point(getPlayerPosition().x + 20, getPlayerPosition().y + 55));
		}
	}

	/**
	 * abstract method for moving left.
	 */
	public abstract void moveLeft();

	/**
	 * abstract method for moving right.
	 */
	public abstract void moveRight();

	/**
	 * abstract method for moving up.
	 */
	public abstract void moveUp();

	/**
	 * abstract method for moving down.
	 */
	public abstract void moveDown();

	/**
	 * abstract method for shooting the ball.
	 */
	public abstract void shootBall();

	/**
	 * Gets the name of the player.
	 * @return String value of the player's name.
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Gets the color of the player.
	 * @return Color object of the player.
	 */
	public Color getPlayerColor() {
		return playerColor;
	}

	/**
	 * Gets the player's position.
	 * @return Point object containing the coordinates of the player.
	 */
	public Point getPlayerPosition() {
		return playerPosition;
	}

	/**
	 * abstract method for setting the initial position of the player.
	 */
	public abstract void setInitialPosition();

	/**
	 * Sets the players position.
	 * @param newPosition is the Point object containing the coordinates for the new position.
	 */
	public void setPlayerPosition(Point newPosition) {
		playerPosition = newPosition;
		if (isPlayerHasBall()) {
			grabsBall();
		}
	}

	/**
	 * Gets the player's statistics.
	 * @return PlayerStatistics object of the player.
	 */
	public Integer getPlayerStatistics() {
		return playerStatistics.getStatistics();
	}

	/**
	 * Sets the player's statistics.
	 * @param newStatistics Integer object containing the new goal value
	 */
	public void setPlayerStatistics(Integer newStatistics) {
		playerStatistics.setStatistics(newStatistics);
	}

	/**
	 * Compares the player to another player
	 * @param otherPlayer the other GamePlayer.
	 */
	@Override
	public int compareTo(GamePlayer otherPlayer) {
		return otherPlayer.getPlayerStatistics().compareTo(this.getPlayerStatistics());
	}

	/**
	 * Converts the player to a String object.
	 * @return converted abstract String object of the player.
	 */
	@Override
	public abstract String toString();
}
