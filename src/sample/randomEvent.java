package sample;

import java.util.Random;

/**
 * Created by RichardWang on 10/19/15.
 */
public class randomEvent {
    private Player player;
    private int round;

    public randomEvent(Player p, int r) {
        this.player = p;
        this.round = r;
    }
}
