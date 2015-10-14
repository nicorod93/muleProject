package sample;

/**
 * Created by nico on 9/30/15.
 */
public class Tile {
    private int xPosition;
    private int yPosition;
    private String name;
    private Mule tileMule;

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

    public Item calcProduction() {
        if (getName().equals("River")) {
            if (tileMule != null) {
                if (tileMule.getName().equals("Energy Mule")) {
                    return new Item("Energy", 2, 25);
                } else if (tileMule.getName().equals("Food Mule")) {
                    return new Item("Food", 4, 25);
                }
            }
        } else if (getName().equals("Plain")) {
            if (tileMule != null) {
                if (tileMule.getName().equals("Energy Mule")) {
                    return new Item("Energy", 3, 25);
                } else if (tileMule.getName().equals("Food Mule")) {
                    return new Item("Food", 2, 25);
                }
            }
        } else if (getName().equals("Mountain1")) {
            if (tileMule != null) {
                if (tileMule.getName().equals("Energy Mule")) {
                    return new Item("Energy", 1, 25);
                } else if (tileMule.getName().equals("Food Mule")) {
                    return new Item("Food", 1, 25);
                }
            }
        } else if (getName().equals("Mountain2")) {
            if (tileMule != null) {
                if (tileMule.getName().equals("Energy Mule")) {
                    return new Item("Energy", 1, 25);
                } else if (tileMule.getName().equals("Food Mule")) {
                    return new Item("Food", 1, 25);
                }
            }
        } else if (getName().equals("Mountain3")) {
            if (tileMule != null) {
                if (tileMule.getName().equals("Energy Mule")) {
                    return new Item("Energy", 1, 25);
                } else if (tileMule.getName().equals("Food Mule")) {
                    return new Item("Food", 1, 25);
                }
            }
        }
        return null;
    }

    public void doProduction() {
        for (Player p : Main.playerArray) {
            for (Tile t : p.getTiles()) {
                Item calculatedItem = t.calcProduction();
                if (calculatedItem != null) {
                    p.addItem(calculatedItem);
                }
            }
        }
    }
}
