package JUnitTests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.awt.*;
import java.util.*;

import org.junit.Test;

import model.*;
import model.players.*;

/**
 * This class tests all the methods in the model, including the model.players.
 */
public class MiniSoccerAppTest {
    
    
    /**
     * This method tests the return value of the getStatistics method by creating
       a new PlayerStatistics instance and assigning it a new Integer value. It then checks the return value.
     */
    @Test
    public void getStatisticsTest() {
        PlayerStatistics stats = new PlayerStatistics();
        Integer newStat = 64;
        stats.setStatistics(newStat);
        assertEquals(Integer.valueOf(64), stats.getStatistics());
    }

    /**
     * This method tests the setPlayerStatistics method by creating two GamePlayers and assigning each their own PlayerStatistics.
       It then sets each player's a uniqe value and checks that they were set correctly via the getPlayerStatistics method.
     */
    @Test
    public void setStatisticsTest() {
        GamePlayer p1 = new Goalkeeper("Goalkeeper", Color.red);
        GamePlayer p2 = new Striker("Striker", Color.blue);
        PlayerStatistics stats1 = new PlayerStatistics();
        PlayerStatistics stats2 = new PlayerStatistics();
        
        stats1.setStatistics(Integer.valueOf(62));
        stats2.setStatistics(Integer.valueOf(99));
        p1.setPlayerStatistics(stats1.getStatistics());
        p2.setPlayerStatistics(stats2.getStatistics());

        assertEquals(Integer.valueOf(62), p1.getPlayerStatistics());
        assertEquals(Integer.valueOf(99), p2.getPlayerStatistics());
    }

    /**
     * This method tests the conversion of the a player's statistics to a string value by creating an instance of PlayerStatistics
       and assigning it a unique value, then checking if that value was converted to a string value correctly.
     */
    @Test
    public void playerStatisticsToStringTest() {
        PlayerStatistics stats = new PlayerStatistics();
        assertEquals("0", stats.toString());

        stats.setStatistics(Integer.valueOf(123));
        assertEquals("123", stats.toString());
    }

    
    /**
     * This method tests the return value of the getPlayer method by creating an instance of PlayerFactory and checking if
       the correct values were returned by using two known valid returns and one known null return.
     */
    @Test
    public void getPlayerTest() {
        PlayerFactory pF = new PlayerFactory();
        GamePlayer p1 = pF.getPlayer("Goalkeeper");
        GamePlayer p2 = pF.getPlayer("Defender");
        GamePlayer p3 = pF.getPlayer("Striker");
        
        assertEquals("Goalkeeper", p1.getPlayerName());
        assertEquals(null, p2);
        assertEquals("Striker", p3.getPlayerName());
    }

    /**
     * This method tests the adding of GamePlayers to the PlayerCollection instance by checking its size before and after insertion.
     */
    @Test
    public void addTest() {
        PlayerCollection pC = new PlayerCollection();
        assertEquals(0, pC.getGamePlayers().size());

        GamePlayer p1 = new Goalkeeper("Goalkeeper", Color.blue);
        GamePlayer p2 = new Striker("Striker", Color.red);
        pC.add(p1);
        pC.add(p2);
        assertEquals(2, pC.getGamePlayers().size());
    }
   
    /**
     * This method tests the removal of GamePlayers in the PlayerCollection instance by checking its size after each removal.
     */
    @Test
    public void removeTest() {
        PlayerCollection pC = new PlayerCollection();
        GamePlayer p1 = new Goalkeeper("Goalkeeper", Color.blue);
        GamePlayer p2 = new Striker("Striker", Color.red);
        pC.add(p1);
        pC.add(p2);
        assertEquals(2, pC.getGamePlayers().size());

        pC.remove("Goalkeeper");
        assertEquals(1, pC.getGamePlayers().size());

        pC.remove("Striker");
        assertEquals(0, pC.getGamePlayers().size());
    }

    /**
     * This method tests the return values of the getGamePlayers method by checking the size of the return 
       before and after adding a GamePlayer to the PlayerCollection instance.
     */
    @Test
    public void getGamePlayersTest() {
        PlayerCollection pC = new PlayerCollection();
        ArrayList<GamePlayer> playerList = new ArrayList<>();
        assertEquals(0, playerList.size());
        
        playerList = pC.getGamePlayers();
        assertEquals(0, pC.getGamePlayers().size());

        GamePlayer p = new Goalkeeper("Goalkeeper", Color.green);
        pC.add(p);
        assertEquals(1, pC.getGamePlayers().size());
    }

    /**
     * This method tests the return value of the get method in the PlayerCollection instance by checking if the
       return value of the GamePlayer the same as the ones that were instanced in this method.
     */
    @Test
    public void getTest() {
        PlayerCollection pC = new PlayerCollection();
        GamePlayer p1 = new Goalkeeper("Goalkeeper", Color.blue);
        GamePlayer p2 = new Striker("Striker", Color.red);
        pC.add(p1);
        pC.add(p2);

        GamePlayer get1 = pC.get("Striker");
        GamePlayer get2 = pC.get("Goalkeeper");
        assertEquals(p2, get1);
        assertEquals(p1, get2);
    }

    /**
     * This method tests the sort method of the PlayerCollection instance by inserting three players and reversing
       the insertion order. It then checks if the order was correctly reversed and then sorts it again and checks if
       the values were correctly sorted into the original values by their colors.
     */
    @Test
    public void sortTest() {
        PlayerCollection pC = new PlayerCollection();
        pC.add(new Goalkeeper("GoalkeeperOne", Color.blue));
        pC.add(new Striker("StrikerOne", Color.red));
        pC.add(new Striker("StrikerTwo", Color.green));
    
        GamePlayer[] players = new GamePlayer[pC.getGamePlayers().size()];
        System.arraycopy(pC.getGamePlayers().toArray(), 0, players, 0, players.length);
        Collections.reverse(Arrays.asList(players));

        assertEquals(Color.green, players[0].getPlayerColor());
        assertEquals(Color.red, players[1].getPlayerColor());
        assertEquals(Color.blue, players[2].getPlayerColor());

        pC.sort();
        players = new GamePlayer[pC.getGamePlayers().size()];
        System.arraycopy(pC.getGamePlayers().toArray(), 0, players, 0, players.length);

        assertEquals(Color.blue, players[0].getPlayerColor());
        assertEquals(Color.red, players[1].getPlayerColor());
        assertEquals(Color.green, players[2].getPlayerColor());
    }

    /**
     * This method tests the return of the iterator method in the PlayerCollection instance by
       inserting three GamePlayers and then looping their names and inserting them into a new array
       and checking if the array has the correct names in the inserted order.
     */
    @Test
    public void iteratorTest() {
        PlayerCollection pC = new PlayerCollection();
        pC.add(new Goalkeeper("GoalkeeperOne", Color.blue));
        pC.add(new Goalkeeper("StrikerOne", Color.red));
        pC.add(new Goalkeeper("StrikerTwo", Color.green));

        String[] names = new String[pC.getGamePlayers().size()];
        Iterator<GamePlayer> itr = pC.iterator();
        int i = 0;

        while (itr.hasNext()) {
            names[i++] = itr.next().getPlayerName();
        }

        String[] expected = {"GoalkeeperOne", "StrikerOne", "StrikerTwo"};
        assertArrayEquals(expected, names);
    }

    /**
     * This method tests the movement of the Striker player by checking its initial position and after 
       moving once in each direction.
     */
    @Test
    public void strikerMoveTest() {
        GamePlayer player = new Striker("Striker", Color.blue);

        player.setPlayerPosition(new java.awt.Point(300, 300));

        assertEquals(300, (int) player.getPlayerPosition().getX());
        assertEquals(300, (int) player.getPlayerPosition().getY());
        
        player.moveRight();
        assertEquals(305, (int) player.getPlayerPosition().getX());
        assertEquals(300, (int) player.getPlayerPosition().getY());

        player.moveDown();
        assertEquals(305, (int) player.getPlayerPosition().getX());
        assertEquals(305, (int) player.getPlayerPosition().getY());

        player.moveLeft();
        assertEquals(300, (int) player.getPlayerPosition().getX());
        assertEquals(305, (int) player.getPlayerPosition().getY());

        player.moveUp();
        assertEquals(300, (int) player.getPlayerPosition().getX());
        assertEquals(300, (int) player.getPlayerPosition().getY());
    }

    /**
     * This method tests the correct string return of the Striker by assigning a unique Integer value to its
       PlayerStatistics and checking if the return value has been correctly converted.
     */
    @Test
    public void strikerToStringTest() {
        GamePlayer player = new Striker("StrikerOne", Color.blue);
        player.setPlayerStatistics(Integer.valueOf(7));
        assertEquals("StrikerOne scored 7 goals", player.toString());
    }

    /**
     * This method checks the movement of the Goalkeeper by checking its initial position and after each move 
       in every direction and random direction.
     */
    @Test
    public void goalkeeperMoveTest() {
        Goalkeeper player = new Goalkeeper("Goalkeeper", Color.yellow);

        player.setPlayerPosition(new java.awt.Point(300, 300));

        assertEquals(300, (int) player.getPlayerPosition().getX());
        assertEquals(300, (int) player.getPlayerPosition().getY());
        
        player.moveRight();
        assertEquals(310, (int) player.getPlayerPosition().getX());
        assertEquals(300, (int) player.getPlayerPosition().getY());

        player.moveDown();
        assertEquals(310, (int) player.getPlayerPosition().getX());
        assertEquals(300, (int) player.getPlayerPosition().getY());

        player.moveLeft();
        assertEquals(300, (int) player.getPlayerPosition().getX());
        assertEquals(300, (int) player.getPlayerPosition().getY());

        player.moveUp();
        assertEquals(300, (int) player.getPlayerPosition().getX());
        assertEquals(295, (int) player.getPlayerPosition().getY());

        player.moveRandomly();
        assertNotEquals(300, player.getPlayerPosition().getX());
    }

    /**
     * This method tests the correct return of the Goalkeeper by assigning a unique Integer value
       to its PlayerStatistics and checking if the return value was correctly converted.
     */
    @Test
    public void goalkeeperToStringTest() {
        GamePlayer player = new Goalkeeper("GoalkeeperZero", Color.blue);
        player.setPlayerStatistics(Integer.valueOf(10));
        assertEquals("GoalkeeperZero caught 10 balls", player.toString());
    }

   /**
    * This method tests all the methods in the SoccerGame instance.
    */
    @Test
    public void soccerGameTest() {
        SoccerGame game = new SoccerGame();

        assertEquals(Integer.valueOf(60), game.getTimeRemaining());
        game.setTimeRemaining(Integer.valueOf(30));
        assertEquals(Integer.valueOf(30), game.getTimeRemaining());

        assertEquals(Integer.valueOf(0), game.getGoal());
        game.setGoal(Integer.valueOf(5));
        assertEquals(Integer.valueOf(5), game.getGoal());

        assertFalse("isPaused should return false", game.isPaused());
        game.setPaused(true);
        assertTrue("isPaused should return true", game.isPaused());

        assertFalse("isOver should return false", game.isOver());
        game.setOver(true);
        assertTrue("isOver should return true", game.isOver());

        assertEquals("PlayerCollection", game.getGamePlayers().getClass().getSimpleName());

        assertFalse("isScored should return false", game.isScored());
        
        assertEquals("Striker", game.getActivePlayer().getClass().getSimpleName());

        SoccerBall.getSoccerBall().setPosition(new Point(480, 180));
        game.automateGoalkeeper();
        assertEquals(280, (int) game.getGamePlayers().get("Goalkeeper").getPlayerPosition().getX());
        SoccerBall.getSoccerBall().setPosition(new Point(480, 300));
        game.automateGoalkeeper();
        assertNotEquals(280, (int) game.getGamePlayers().get("Goalkeeper").getPlayerPosition().getX());
    }

    /**
     * This method tests all the methods in the SoccerBall class.
     */
    @Test
    public void soccerBallTest() {
        SoccerBall.getSoccerBall().resetSoccerBall();
        assertEquals(480, (int) SoccerBall.getSoccerBall().getPosition().getX());
        assertEquals(500, (int) SoccerBall.getSoccerBall().getPosition().getY());
        
        SoccerBall.getSoccerBall().setPosition(new Point(45, 90));
        assertEquals(45, (int) SoccerBall.getSoccerBall().getPosition().getX());
        assertEquals(90, (int) SoccerBall.getSoccerBall().getPosition().getY());
        
        SoccerBall.getSoccerBall().resetSoccerBall();
        assertEquals(480, (int) SoccerBall.getSoccerBall().getPosition().getX());
        assertEquals(500, (int) SoccerBall.getSoccerBall().getPosition().getY());
        
        SoccerBall.getSoccerBall().moveBallY(100);
        assertNotEquals(480, (int) SoccerBall.getSoccerBall().getPosition().getY());

        assertFalse("onGoalkeeperSide should return false", SoccerBall.getSoccerBall().onGoalkeeperSide());
        SoccerBall.getSoccerBall().setPosition(new Point(45, 90));
        assertTrue("onGoalkeeperSide should return true", SoccerBall.getSoccerBall().onGoalkeeperSide());

        assertFalse("inGate should return false", SoccerBall.getSoccerBall().inGate());
        SoccerBall.getSoccerBall().setPosition(new Point(500, 50));
        assertFalse("inGate should return false", SoccerBall.getSoccerBall().inGate());
        SoccerBall.getSoccerBall().setPosition(new Point(200, 5));
        assertFalse("inGate should return false", SoccerBall.getSoccerBall().inGate());
        SoccerBall.getSoccerBall().setPosition(new Point(200, 100));
        assertFalse("inGate should return false", SoccerBall.getSoccerBall().inGate());
        SoccerBall.getSoccerBall().setPosition(new Point(300, 30));
        assertTrue("inGate should return true", SoccerBall.getSoccerBall().inGate());
        
        assertEquals(Color.white, SoccerBall.getSoccerBall().getColor());

        SoccerBall.getSoccerBall().moveBall(0, 10, 3);
    }
}
