package model;

import java.awt.Color;
import java.util.ArrayList;

import model.players.GamePlayer;
import model.players.Goalkeeper;
import model.players.Striker;

/**
 * This class creates the GamePlayer instances and adds them to a private ArrayList.
   It allows the retreival of GamePlayer items from the list.
 */
public class PlayerFactory {

    /**
     * A private ArrayList used to keep track of each GamePlayer.
     */
    private ArrayList<GamePlayer> players;

    /**
     * The PlayerFactory constructor which initializes the ArrayList and the two GamePlayers.
     */
    public PlayerFactory() {
        players = new ArrayList<>();
        players.add(new Goalkeeper("Goalkeeper", Color.yellow));
        players.add(new Striker("Striker", Color.blue));
    }

    /**
     * Searches for and returns the correct GamePlayer given the GamePlayer's name.
     * @param name the name of the player.
     * @return GamePlayer object if found, otherwise null.
     */
    public GamePlayer getPlayer(String name) {
        GamePlayer player = null;
        for (GamePlayer p : players) {
            if (p.getPlayerName().equals(name)) {
                player = p;
                break;
            }
        }
        return player;
    }
}
