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

    public Tile(String name, int xPosition, int yPosition) {
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
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
        Main.getCurrentPlayer().setMoney(Main.currentPlayer.getMoney() - 300);
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
}
