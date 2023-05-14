package model.players;

/**
 * This class handles the tracking of goals made by the player and the methods to read and write values.
 */
public class PlayerStatistics {
    /**
     * Keeps track of how many goals/blocks the player has made
     */
    private int points;

    /**
     * Initializes the points to 0.
     */
    public PlayerStatistics() {
        points = 0;
    }

    /**
     * Gets the Integer value of the points.
     * @return Integer value of the points.
     */
    public Integer getStatistics() {
        return Integer.valueOf(points);
    }

    /**
     * Sets the Integer value of the points.
     * @param statistics the Integer containing the points.
     */
    public void setStatistics(Integer statistics) {
        points = statistics.intValue();
    }

    /**
     * Converts the Integer value of points to a String value.
     * @return the String value of the points
     */
    @Override
    public String toString() {
        return Integer.toString(points);
    }
}
