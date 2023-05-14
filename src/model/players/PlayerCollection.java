package model.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * This class contains the GamePlayers and the methods to add, remove, sort, and iterate.
 */
public class PlayerCollection implements Iterable<GamePlayer> {
    
    /**
     * TreeMap that holds the player's name as the keys and GamePlayer object as the values.
     */
    public TreeMap<String, GamePlayer> playerMap;

    /**
     * Initializes the TreeMap
     */
    public PlayerCollection() {
        playerMap = new TreeMap<String, GamePlayer>();
    }

    /**
     * Adds the player to the TreeMap
     * @param player the GamePlayer object to add to the TreeMap
     */
    public void add(GamePlayer player) {
        playerMap.put(player.getPlayerName(), player);
    }

    /**
     * Removes the player from the TreeMap
     * @param playerName the name of the player to remove from the TreeMap
     */
    public void remove(String playerName) {
        playerMap.remove(playerName);
    }

    /**
     * Gets the ArrayList of GamePlayers in the TreeMap
     * @return the ArrayList of type GamePlayer.
     */
    public ArrayList<GamePlayer> getGamePlayers() {
        ArrayList<GamePlayer> players = new ArrayList<GamePlayer>(playerMap.values());
        return players;
    }

    /**
     * Gets a player from the TreeMap given their name.
     * @param name the name of the Player to get.
     * @return GamePlayer object.
     */
    public GamePlayer get(String name) {
        return playerMap.get(name);
    }

    /**
     * Sorts the collection of players.
     */
    public void sort() {
        Collections.sort(getGamePlayers());
    }

    /**
     * Gets the iterator for the GamePlayers collection using the PlayerCollectionIterator class.
     * @return the iterator for the GamePlayers collection
     */
    @Override
    public Iterator<GamePlayer> iterator() {
        return new PlayerCollectionIterator<>(getGamePlayers());
    }

}
