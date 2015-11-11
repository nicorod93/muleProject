package sample;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by user on 11/10/2015.
 */
public class PlayerTest {

    @Test
    public void testMainInitialize1() throws Exception {
        Main.difficulty = "Beginner";
        Main.playerArray.add(new Player("Bob", "Red", "Human"));
        Main.setPlayersItems();
        Player player = new Player("Bob", "Red", "Human");
        assertEquals("Food", Main.playerArray.get(0).getSpecificItem(0).getName());
        assertEquals(8, Main.playerArray.get(0).getSpecificItem(0).getAmount());
    }

    @Test
    public void testMainInitialize2() throws Exception {
        assertEquals("Energy", Main.playerArray.get(0).getSpecificItem(1).getName());
        assertEquals(4, Main.playerArray.get(0).getSpecificItem(1).getAmount());
    }

    @Test
    public void testMainInitialize3() throws Exception {
        assertEquals("Smithore", Main.playerArray.get(0).getSpecificItem(2).getName());
        assertEquals(0, Main.playerArray.get(0).getSpecificItem(2).getAmount());
    }

    @Test
    public void testMainInitialize4() throws Exception {
        assertEquals("Crystite", Main.playerArray.get(0).getSpecificItem(3).getName());
        assertEquals(0, Main.playerArray.get(0).getSpecificItem(3).getAmount());
    }

    @Test
    public void testCalculateScore1() throws Exception { // Tests starting score with default food and energy
        Main.playerArray.get(0).calculateScore();
        assertEquals(12,Main.playerArray.get(0).getScore());
    }

    @Test
    public void testCalculateScore2() throws Exception { // Tests score with certain amount of money
        Main.playerArray.get(0).setMoney(100);
        Main.playerArray.get(0).calculateScore();
        assertEquals(112, Main.playerArray.get(0).getScore());
    }

    @Test
    public void testCalculateScore3() throws Exception {  // Tests score with 1 tile and accumulated money score
        Main.playerArray.get(0).addProperty(new Tile("Mountain", 0, 0));
        Main.playerArray.get(0).calculateScore();
        assertEquals(612, Main.playerArray.get(0).getScore());
    }

    @Test
    public void testCalculateScore4() throws Exception { // Tests score with 50 additional tiles, accumulating from previous
        for (int i = 0; i < 50; i++) {
            Main.playerArray.get(0).addProperty(new Tile("Mountain " + 1, i, i+1));
        }
        Main.playerArray.get(0).calculateScore();
        assertEquals(25612, Main.playerArray.get(0).getScore());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testCalculateScore5() throws Exception { //Player's should not have default food/energy/ores before initialize.
        //Initialize depends on the difficulty selected.
        Main.playerArray.add(new Player("George", "Blue", "Other"));
        Main.playerArray.get(1).calculateScore();
        assertEquals(0, Main.playerArray.get(1).getScore());
    }

    @Test
    public void testCalculateScore6() throws Exception { //Player's score does not depend on Mules
        Main.playerArray.get(1).addEMule();
        Main.playerArray.get(1).addFMule();
        Main.playerArray.get(1).addOMule();
        Main.setPlayersItems();
        Main.playerArray.get(1).calculateScore();
        assertEquals(12, Main.playerArray.get(1).getScore());
    }

    @Test
    public void testCalculateScore7() throws Exception { //Tests the accuracy of adding default value of money
        //depending on type of player and calculating score based on that
        Main.setPlayersMoney();
        Main.playerArray.get(1).calculateScore();
        assertEquals(1012, Main.playerArray.get(1).getScore());
    }

    @Test
    public void testCalculateScore8() throws Exception { //Calculation should be based on only energy, food, and ore items.
        Main.playerArray.get(1).addItem(new Item("Pizza", 100, 100));
        Main.playerArray.get(1).calculateScore();
        assertEquals(1012, Main.playerArray.get(1).getScore());
    }

    @Test
    public void testCalculateScore9() throws Exception { //Changing value of food item should affect score
        Main.playerArray.get(1).getSpecificItem(0).increaseAmount(100);
        Main.playerArray.get(1).calculateScore();
        assertEquals(1112, Main.playerArray.get(1).getScore());
    }




}