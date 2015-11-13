package sample;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Gordon on 11/11/2015.
 */
public class GordonJUnitTest {

    private static final int TIMEOUT = 200;

    @Test (timeout = TIMEOUT)
    public void testMainInitialize1() {
        Main.difficulty = "Beginner";
        Main.playerArray.add(new Player("Gordon", "Blue", "Human"));
        Main.playerArray.add(new Player("Nico", "Red", "Flapper"));
        Main.playerArray.add(new Player("Dick", "Purple", "Other"));
        Main.setPlayersItems();
        assertEquals(3, Main.playerArray.size());
    }
// Human
    @Test (timeout = TIMEOUT)
    public void testFoodHuman() {
        assertEquals(8, Main.playerArray.get(0).getSpecificItem(0).getAmount());
    }

    @Test (timeout = TIMEOUT)
    public void testEnergyHuman() {
        assertEquals(4, Main.playerArray.get(0).getSpecificItem(1).getAmount());
    }

    @Test (timeout = TIMEOUT)
    public void testOreHuman() {
        assertEquals(0, Main.playerArray.get(0).getSpecificItem(2).getAmount());
    }

    @Test (timeout = TIMEOUT)
    public void testCrystiteHuman() {
        assertEquals(0, Main.playerArray.get(0).getSpecificItem(3).getAmount());
    }
//Flapper
    @Test (timeout = TIMEOUT)
    public void testFoodFlapper() {
        assertEquals(8, Main.playerArray.get(1).getSpecificItem(0).getAmount());
    }

    @Test (timeout = TIMEOUT)
    public void testEnergyFlapper() {
        assertEquals(4, Main.playerArray.get(1).getSpecificItem(1).getAmount());
    }

    @Test (timeout = TIMEOUT)
    public void testOreFlapper() {
        assertEquals(0, Main.playerArray.get(1).getSpecificItem(2).getAmount());
    }

//Other
    @Test (timeout = TIMEOUT)
    public void testFoodOther() {
        assertEquals(8, Main.playerArray.get(2).getSpecificItem(0).getAmount());
    }

    @Test (timeout = TIMEOUT)
    public void testEnergyOther() {
        assertEquals(4, Main.playerArray.get(2).getSpecificItem(1).getAmount());
    }

    @Test (timeout = TIMEOUT)
    public void testOreOther() {
        assertEquals(0, Main.playerArray.get(2).getSpecificItem(2).getAmount());
    }

    @Test (timeout = TIMEOUT)
    public void testCrystiteOther() {
        assertEquals(0, Main.playerArray.get(2).getSpecificItem(3).getAmount());
    }


}
