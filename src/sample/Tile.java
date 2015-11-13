package sample;

/**
 * Created by nico on 9/30/15.
 */
public class Tile {
    private int xPosition;
    private int yPosition;
    private String name;
    private Mule tileMule;
    private Player tileOwner;
    private static final int CONSTANT = 300;

    public Tile(String name1, int xPosition1, int yPosition1) {
        this.name = name1;
        this.xPosition = xPosition1;
        this.yPosition = yPosition1;
        this. tileOwner = null;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int[] getPosition() {
        return new int[] {xPosition, yPosition};

    }

    public String getName() {
        return name;
    }

    public void addMule(Mule mule) {
        if (tileMule == null) {
            tileMule = mule;
        }
    }

    public Mule getMule() {
        return tileMule;
    }

    public void purchase() {
        Main.getCurrentPlayer().setMoney(Main.currentPlayer.getMoney() - CONSTANT);
        Main.getCurrentPlayer().addProperty(new Tile(getName(), getXPosition
                (), getYPosition()));
    }

    public String toString() { return this.name + " " + xPosition + " " + yPosition; }

    public void setTileOwner(Player p) {
        this.tileOwner = p;
    }

    public Player getTileOwner() {
        return this.tileOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Tile that = (Tile) o;
        return this.getXPosition() == that.getXPosition() && this.getYPosition() == that.getYPosition();
    }

    @Override
    public abstract int hashCode();
}
