package sample;

/**
 * Created by nico on 9/30/15.
 */
public class Tile {
    private int xPosition;
    private int yPosition;
    private String name;

    public Tile(String name, int xPosition, int yPosition) {
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int[] getPosition() {
        int[] pos = {xPosition, yPosition};
        return pos;
    }

    public String getName() {
        return name;
    }

    public String toString() { return this.name + " " + xPosition + " " + yPosition; }
}
