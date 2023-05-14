package controller;

import model.SoccerGame;
import view.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MenubarListener class manages the actions that are performed after the user has
   clicked on the menu options or pressed their respective keybinds. 
   The available options are new game, exit game, pause, and resume.
 */

public class MenubarListener implements ActionListener {

	/**
	 * The private final GamePanel object
	 */
	private final GamePanel gamePanel;

	/**
	 * The MenubarListener constructor
	 * @param panel is the required GamePanel class object
	 */
	public MenubarListener(GamePanel panel) {
		gamePanel = panel;
	}

	/**
	 * The actionPerformed method acts upon the users input on the menu options.
	   The New option sets up the game for a new game.
	   The Exit option closes the application.
	   The Pause option pauses the game.
	   The Resume option resumes the game.
	 *  
	 * @throws RuntimeException if the user gives an invalid input
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		SoccerGame soccerGame = gamePanel.getGame();
		switch (e.getActionCommand()) {
			case "NEW":
				gamePanel.setupSoccerGame();
				break;
			case "EXIT":
				System.exit(0);
				break;
			case "PAUSE":
				if (!soccerGame.isPaused() && !soccerGame.isOver()) {
					soccerGame.setPaused(true);
				} else if (soccerGame.isPaused()) {
					System.out.println("game is already on pause!");
				} else if (soccerGame.isOver()) {
					System.out.println("game is over, please start a new game.");
				}
				break;
			case "RESUME":
				if (soccerGame.isPaused() && !soccerGame.isOver()) {
					soccerGame.setPaused(false);
				} else if (!soccerGame.isPaused()) {
					System.out.println("game is already running!");
				} else if (soccerGame.isOver()) {
					System.out.println("game is over, please start a new game.");
				}
				break;
			default:
				throw new RuntimeException("Invalid action command " + e.getActionCommand());
		}
	}
}
