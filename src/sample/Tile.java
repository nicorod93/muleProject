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

    public final int getXPosition() {
        return xPosition;
    }

    public final int getYPosition() {
        return yPosition;
    }

    public final int[] getPosition() {
        return new int[] {xPosition, yPosition};

    }

    public final String getName() {
        return name;
    }

    public final void addMule(Mule mule) {
        if (tileMule == null) {
            tileMule = mule;
        }
    }

    public final Mule getMule() {
        return tileMule;
    }

    public final void purchase() {
        Main.getCurrentPlayer().setMoney(Main.currentPlayer.getMoney() - CONSTANT);
        Main.getCurrentPlayer().addProperty(new Tile(getName(), getXPosition
                (), getYPosition()));
    }

    public final String toString() { return this.name + " " + xPosition + " " + yPosition; }

    public final void setTileOwner(Player p) {
        this.tileOwner = p;
    }

    public final Player getTileOwner() {
        return this.tileOwner;
    }

    @Override
    public final boolean equals(Object o) {
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
