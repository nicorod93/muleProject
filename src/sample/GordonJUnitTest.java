package sample;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Gordon on 11/11/2015.
 */
public class GordonJUnitTest {

    private static final int TIMEOUT = 200;

//    @Before
//    public void setUp() {
//        Player player1 = new Player("Gordon", "Blue", "Human");
//        Player player2 = new Player("Nico", "Red", "Flapper");
//        Player player3 = new Player("Dick", "Purple", "Other");
//        Player player4 = new Player("Advaith", "Orange", "Human");
//        Main.playerArray.add(player1);
//        Main.playerArray.add(player2);
//        Main.playerArray.add(player3);
//        Main.playerArray.add(player4);
//        Main.difficulty = "Beginner";
//        Main.setPlayersItems();
//    }
//    @Test (timeout = TIMEOUT)
//    public void testPlayer1Food() {
//        long food = 8;
//        Player player = Main.playerArray.get(0);
//        assertEquals(food, player.getSpecificItem(0).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer2Food() {
//        long food = 8;
//        Player player = Main.playerArray.get(1);
//        assertEquals(food, player.getItems().get(0).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer3Food() {
//        long food = 8;
//        Player player = Main.playerArray.get(2);
//        assertEquals(food, player.getItems().get(0).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer4Food() {
//        long food = 8;
//        Player player = Main.playerArray.get(3);
//        assertEquals(food, player.getItems().get(0).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer1Energy() {
//        long energy = 4;
//        Player player = Main.playerArray.get(0);
//        assertEquals(energy, player.getItems().get(1).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer2Energy() {
//        long energy = 4;
//        Player player = Main.playerArray.get(1);
//        assertEquals(energy, player.getItems().get(1).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer3Energy() {
//        long energy = 4;
//        Player player = Main.playerArray.get(2);
//        assertEquals(energy, player.getItems().get(1).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer4Energy() {
//        long energy = 4;
//        Player player = Main.playerArray.get(3);
//        assertEquals(energy, player.getItems().get(1).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer1Smithore() {
//        long smithore = 0;
//        Player player = Main.playerArray.get(0);
//        assertEquals(smithore, player.getItems().get(2).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer2Smithore() {
//        long smithore = 0;
//        Player player = Main.playerArray.get(1);
//        assertEquals(smithore, player.getItems().get(2).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer3Smithore() {
//        long smithore = 0;
//        Player player = Main.playerArray.get(2);
//        assertEquals(smithore, player.getItems().get(2).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer4Smithore() {
//        long smithore = 0;
//        Player player = Main.playerArray.get(3);
//        assertEquals(smithore, player.getItems().get(2).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer1Crystite() {
//        long crystite = 0;
//        Player player = Main.playerArray.get(0);
//        assertEquals(crystite, player.getItems().get(3).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer2Crystite() {
//        long crystite = 0;
//        Player player = Main.playerArray.get(1);
//        assertEquals(crystite, player.getItems().get(3).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer3Crystite() {
//        long crystite = 0;
//        Player player = Main.playerArray.get(2);
//        assertEquals(crystite, player.getItems().get(3).getAmount());
//    }
//
//    @Test (timeout = TIMEOUT)
//    public void testPlayer4Crystite() {
//        long crystite = 0;
//        Player player = Main.playerArray.get(3);
//        assertEquals(crystite, player.getItems().get(3).getAmount());
//    }
//


    @Test (timeout = TIMEOUT)
    public void testMainInitialize1() {
        Main.difficulty = "Beginner";
        Main.playerArray.add(new Player("Bob", "Red", "Human"));
        Main.setPlayersItems();
        assertEquals(1, Main.playerArray.size());
    }

    @Test (timeout = TIMEOUT)
    public void testFood() {
        assertEquals(8, Main.playerArray.get(0).getSpecificItem(0).getAmount());
    }

    @Test (timeout = TIMEOUT)
    public void testEnergy() {
        assertEquals(4, Main.playerArray.get(0).getSpecificItem(1).getAmount());
    }

    @Test (timeout = TIMEOUT)
    public void testOre() {
        assertEquals(0, Main.playerArray.get(0).getSpecificItem(2).getAmount());
    }

    @Test (timeout = TIMEOUT)
    public void testCrystite() {
        assertEquals(0, Main.playerArray.get(0).getSpecificItem(3).getAmount());
    }
}
