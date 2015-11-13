package sample;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertTrue;

/**
 * Created by nico on 11/10/15.
 */
public class NicoJUnit {


    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        Player player1 = new Player("Nico", "Blue", "Human");
        Player player2 = new Player("Mateo", "Red", "Flapper");
        Player player3 = new Player("Erica", "Purple", "Other");
        Player player4 = new Player("PJ", "Orange", "Human");
        player1.addItem(new Item("Food", 8, 30));
        player2.addItem(new Item("Food", 2, 30));
        player3.addItem(new Item("Food", 0, 30));
        Main.playerArray.add(player1);
        Main.playerArray.add(player2);
        Main.playerArray.add(player3);
        Main.playerArray.add(player4);
        Main.round = 0;
        Main.playerTurn = 0;
    }

    @Test (timeout = TIMEOUT)
    public void testPlayer1TurnTime() {
        long turnTime = 50;
        long calculatedTurnTime = Main.calculateTurnTime();
        assertTrue("Calculated time was " + calculatedTurnTime + " when it " +
                "should have been " + turnTime, calculatedTurnTime
                == turnTime);
    }

    @Test (timeout = TIMEOUT)
    public void testPlayer2TurnTime() {
        Main.playerTurn = 1;
        long turnTime = 30;
        long calculatedTurnTime = Main.calculateTurnTime();
        assertTrue("Calculated time was " + calculatedTurnTime + " when it " +
                "should have been " + turnTime, calculatedTurnTime
                == turnTime);
    }

    @Test (timeout = TIMEOUT)
    public void testPlayer3TurnTime() {
        Main.playerTurn = 2;
        long turnTime = 5;
        long calculatedTurnTime = Main.calculateTurnTime();
        assertTrue("Calculated time was " + calculatedTurnTime + " when it " +
                "should have been " + turnTime, calculatedTurnTime
                == turnTime);
    }

    @Test (timeout = TIMEOUT)
    public void testPlayer4TurnTime() {
        Main.playerTurn = 3;
        long turnTime = 5;
        long calculatedTurnTime = Main.calculateTurnTime();
        assertTrue("Calculated time was " + calculatedTurnTime + " when it " +
                "should have been " + turnTime, calculatedTurnTime
                == turnTime);
    }

    @Test (timeout = TIMEOUT)
    public void testPlayer1TurnTimeRound8() {
        Main.round = 8;
        long turnTime = 50;
        long calculatedTurnTime = Main.calculateTurnTime();
        assertTrue("Calculated time was " + calculatedTurnTime + " when it " +
                "should have been " + turnTime, calculatedTurnTime
                == turnTime);
    }

    @Test (timeout = TIMEOUT)
    public void testPlayer2TurnTimeRound8() {
        Main.round = 8;
        Main.playerTurn = 1;
        long turnTime = 30;
        long calculatedTurnTime = Main.calculateTurnTime();
        assertTrue("Calculated time was " + calculatedTurnTime + " when it " +
                "should have been " + turnTime, calculatedTurnTime
                == turnTime);
    }

    @Test (timeout = TIMEOUT)
    public void testPlayer3TurnTimeRound8() {
        Main.round = 8;
        Main.playerTurn = 2;
        long turnTime = 5;
        long calculatedTurnTime = Main.calculateTurnTime();
        assertTrue("Calculated time was " + calculatedTurnTime + " when it " +
                "should have been " + turnTime, calculatedTurnTime
                == turnTime);
    }

    @Test (timeout = TIMEOUT)
    public void testPlayer4TurnTimeRound8() {
        Main.round = 8;
        Main.playerTurn = 3;
        long turnTime = 5;
        long calculatedTurnTime = Main.calculateTurnTime();
        assertTrue("Calculated time was " + calculatedTurnTime + " when it " +
                "should have been " + turnTime, calculatedTurnTime
                == turnTime);
    }

}
