package main;
import controller.GameListener;
import controller.MenubarListener;
import view.GameMenuBar;
import view.GamePanel;

import javax.swing.*;

/**
 * This class initializes the swing GUI, user input, and starts the game.
 */
public class MiniSoccerApp {

	/**
	 * The main method creates all the required swing GUI instances and initializes the application.
	 * @param args the arguments used when calling this application via CLI.
	 */
	public static void main(String[] args) {
		JFrame gameFrame = new JFrame("Mini Soccer");
		GamePanel gamePanel = new GamePanel();
		GameListener gameListener = new GameListener(gamePanel);
		MenubarListener menubarListener = new MenubarListener(gamePanel);
		GameMenuBar gameMenuBar = new GameMenuBar(menubarListener);
		gameFrame.add(gamePanel);
		gameFrame.addKeyListener(gameListener);
		gameFrame.setJMenuBar(gameMenuBar);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(600, 600);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
	}
}
