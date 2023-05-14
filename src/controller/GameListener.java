package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.SoccerGame;
import view.GamePanel;

/**
 * The GameListener class manages the movement and action inputs the user gives
 */
public class GameListener implements KeyListener {
	/**
	 * A global variable of the GamePanel class
	 */
	private final GamePanel gamePanel;

	/**
	 * The GameListener constructor
	 * @param panel The GamePanel object
	 */
	public GameListener(GamePanel panel) {
		gamePanel = panel;
	}

	/**
	 * The keyTyped event method
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * The keyPressed event method which calls the movement of the active player, which is moving up, down, left, right. It also calls the action of shooting the ball. 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		SoccerGame soccerGame = gamePanel.getGame();
		if (!soccerGame.isPaused() && !soccerGame.isOver()) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					soccerGame.getActivePlayer().moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					soccerGame.getActivePlayer().moveRight();
					break;
				case KeyEvent.VK_UP:
					soccerGame.getActivePlayer().moveUp();
					break;
				case KeyEvent.VK_DOWN:
					soccerGame.getActivePlayer().moveDown();
					break;
				case KeyEvent.VK_SPACE:
					if (soccerGame.getActivePlayer().isPlayerHasBall()) {
						soccerGame.getActivePlayer().shootBall();
					}
					break;
			}
		}
	}

	/**
	 * The keyReleased event method
	 */
	@Override
	public void keyReleased(KeyEvent e) {

	}
}
