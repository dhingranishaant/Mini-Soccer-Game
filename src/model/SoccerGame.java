package model;

import java.util.Timer;
import java.util.TimerTask;

import model.players.*;

/**
 * This class intializes and starts the game. It also manages the game and automation of the goalkeer
 */
public class SoccerGame {

	/**
	 * A private Integer for the time that is remaining in the game.
	 */
	private Integer timeRemaining;

	/**
	 * A private Integer to keep track of the amount of goals.
	 */
	private Integer goal;

	/**
	 * A private Boolean which keeps track if the game is paused or not.
	 */
	private Boolean isPaused;

	/**
	 * A private Boolean which keeps track if the game is over or not.
	 */
	private Boolean isOver;

	/**
	 * A private final PlayerCollection instance.
	 */
	private final PlayerCollection gamePlayers;

	/**
	 * This is the constructor which initializes the game and starts it.
	 */
	public SoccerGame() {
		timeRemaining = 60;
		goal = 0;
		isPaused = false;
		isOver = false;
		SoccerBall.getSoccerBall().resetSoccerBall();
		PlayerFactory playerFactory = new PlayerFactory();
		gamePlayers = new PlayerCollection();
		gamePlayers.add(playerFactory.getPlayer("Striker"));
		gamePlayers.add(playerFactory.getPlayer("Goalkeeper"));
		startGame();
	}

	/**
	 * Starts the game and keeps track of the time left and if the player scored.
	 */
	private void startGame() {
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				if (!isPaused()) {
					if (getTimeRemaining() <= 0) {
						setOver(true);
						timer.cancel();
					} else {
						setTimeRemaining(getTimeRemaining() - 1);
					}
					if (isScored()) {
						setPaused(true);
						setGoal(getGoal() + 1);
						getActivePlayer().setPlayerStatistics(getActivePlayer().getPlayerStatistics() + 1);
						getGamePlayers().get("Striker").setInitialPosition();
						SoccerBall.getSoccerBall().resetSoccerBall();
					} else {
						automateGoalkeeper();
					}
				}
			}
		};
		timer.schedule(timerTask, 1000, 1000);
	}

	/**
	 * Gets the time that is remaining.
	 * @return Integer that contains the amount of seconds remaining.
	 */
	public Integer getTimeRemaining() {
		return timeRemaining;
	}

	/**
	 * Sets the time for how long the game will last.
	 * @param timeRemaining is how long the game will last in seconds.
	 */
	public void setTimeRemaining(Integer timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	/**
	 * Gets the amount of goals made.
	 * @return Integer that contains the amount of goals made.
	 */
	public Integer getGoal() {
		return goal;
	}

	/**
	 * Sets the amount of goals.
	 * @param newGoal Integer that has the new goal value.
	 */
	public void setGoal(Integer newGoal) {
		goal = newGoal;
	}

	/**
	 * Gets the status of the game pause.
	 * @return true if the game is paused, false if not.
	 */
	public Boolean isPaused() {
		return isPaused;
	}

	/**
	 * Sets the isPaused status.
	 * @param paused true if the game is to be paused, false if not.
	 */
	public void setPaused(Boolean paused) {
		isPaused = paused;
	}

	/**
	 * Gets the status of if the game is over.
	 * @return true if the game is over, false if not.
	 */
	public Boolean isOver() {
		return isOver;
	}

	/**
	 * Sets the isOver status
	 * @param over true if the game is over, false if not.
	 */
	public void setOver(Boolean over) {
		isOver = over;
	}

	/**
	 * Gets the PlayerCollection of GamePlayers
	 * @return PlayerCollection containing all the GamePlayers.
	 */
	public PlayerCollection getGamePlayers() {
		return gamePlayers;
	}

	/**
	 * Checks if the SoccerBall is past the Goalkeeper line and shoots it back, otherwise it moves to a random position.
	 */
	public void automateGoalkeeper() {
		SoccerBall soccerBall = SoccerBall.getSoccerBall();
		Goalkeeper goalkeeper = (Goalkeeper) gamePlayers.get("Goalkeeper");
		if (soccerBall.onGoalkeeperSide()) {
			goalkeeper.grabsBall();
			goalkeeper.shootBall();
			goalkeeper.setPlayerStatistics(goalkeeper.getPlayerStatistics() + 1);
		} else {
			goalkeeper.moveRandomly();
		}
	}

	/**
	 * Gets the status of the ball if it is inside the goal.
	 * @return true if it is in the goal, false if not.
	 */
	public boolean isScored() {
		return SoccerBall.getSoccerBall().inGate();
	}

	/**
	 * Gets the Striker GamePlayer object from the PlayerCollection instance.
	 * @return GamePlayer Striker object.
	 */
	public GamePlayer getActivePlayer() {
		return gamePlayers.get("Striker");
	}
}
