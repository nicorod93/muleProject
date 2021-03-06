package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by RichardWang on 10/14/15.
 */
public class PubController {


    @FXML
    private void gamble(MouseEvent event) {
        int round = Main.round;

        int roundBonus = 0;
        if (round < 4) {
            roundBonus = 50;
        } else if(round < 8) {
            roundBonus = 100;
        } else if (round < 12) {
            roundBonus = 150;
        } else if (round == 12) {
            roundBonus = 200;
        }

        int timeBonus = 0;
        int timeLeft = Main.timeRemain;
        if (timeLeft >= 37) {
            timeBonus = 200;
        } else if (timeLeft >= 25) {
            timeBonus = 150;
        } else if (timeLeft >= 12) {
            timeBonus = 100;
        } else {
            timeBonus = 50;
        }

        int total = roundBonus + timeBonus;

        if (total > 250) {
            total = 250;
        }
        System.out.println("You Won $" + total + " at the Pub! Congrats!");
        int oldMoney = Main.getCurrentPlayer().getMoney();
        Main.getCurrentPlayer().setMoney(oldMoney + total);
        Main.timeRemain = 0;
        Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
        t.setScene(Main.map);
        t.setTitle("Player Configuration");
        t.show();
    }


    @FXML
    private void back(MouseEvent event) {
        try {
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(Main.map);
            t.setTitle("Player Configuration");
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }
}
