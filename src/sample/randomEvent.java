package sample;


import java.util.List;

/**
 * Created by RichardWang on 10/19/15.
 */
public class RandomEvent {
    private Player player;
    private static long round;
    private static long roundBonus;

    public RandomEvent(Player p, long r, List<Player> array) {
        this.player = p;
        this.round = r;
        long low = p.getScore();
        for (Player a: array) {
            if (low > a.getScore()) {
                low = a.getScore();
            }
        }
        Player b = null;
        for (Player a: array) {
            if (low == a.getScore()) {
                b = a;
            }
        }
        int rNum = (int) Math.ceil(Math.random()*100);
        if (rNum < 27) {
            int rNum1 = (int) Math.ceil(Math.random() * 6);
            doRandom(rNum1);
        }
        if (round < 4 || this.player.equals(b)) {
            this.roundBonus = 25;
        } else if (round < 8) {
            this.roundBonus = 50;
        } else if (round < 12) {
            this.roundBonus = 75;
        } else {
            this.roundBonus = 100;
        }
    }

    public final void doRandom(int rNum1) {
        if (rNum1 == 0) {
//            System.out.println("YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.");
            long newFood = this.player.getSpecificItem(0).getAmount() + 3;
            long newEnergy = this.player.getSpecificItem(1).getAmount() + 2;
            this.player.getSpecificItem(0).setAmount(newFood);
            this.player.getSpecificItem(1).setAmount(newEnergy);
        }
        if (rNum1 == 1) {
//            System.out.println("A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.");
            long newOre = this.player.getSpecificItem(2).getAmount() + 2;
            this.player.getSpecificItem(2).setAmount(newOre);
        }
        if (rNum1 == 2) {
            long a = this.roundBonus * 8;
            long newMoney = this.player.getMoney() + a;
//            System.out.println("THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $ " + a);
            this.player.setMoney(newMoney);
        }
        if (rNum1 == 3) {
            long b = this.roundBonus * 2;
            long newMoney = this.player.getMoney() + b;
//            System.out.println("YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $ " + b);
            this.player.setMoney(newMoney);
        }
        if (rNum1 == 4) {
            long c = this.roundBonus * 4;
            long newMoney = this.player.getMoney() - c;
            if (newMoney < 0 ) {
                this.player.setMoney(0);
            } else {
                this.player.setMoney(newMoney);
            }
//            System.out.println("FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $ " + c);
        }
        if (rNum1 == 5) {
            long newMoney = this.player.getMoney() / 2;
//            System.out.println("MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.");
            this.player.setMoney(newMoney);
        }
        if (rNum1 == 6) {
            long d = this.roundBonus * 6;
            long newMoney = this.player.getMoney() - d;
            if (newMoney < 0) {
                this.player.setMoney(0);
            } else {
                this.player.setMoney(newMoney);
            }
//            System.out.println("YOUR SPACE GYPSY IN LAWS MADE A MESS OF THE TOWN. IT COST YOU $ " + d + " TO CLEAN IT UP.");

        }

    }
}
