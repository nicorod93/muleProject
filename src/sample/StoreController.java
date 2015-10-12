package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StoreController {
    @FXML
    private Label energyLeft;

    @FXML
    private Label foodLeft;

    @FXML
    private Button foodBut;

    @FXML
    private Button energyBut;

    @FXML
    private void buyFood() {
        if (Main.food == 0) {
            System.out.println("No food left!");
            foodBut.setDisable(true);
        } else {
            foodBut.setDisable(false);
            int currentMoney = Main.getCurrentPlayer().getMoney();
            Main.getCurrentPlayer().setMoney(currentMoney - 30);
            System.out.println(Main.getCurrentPlayer().getMoney());
            Main.food--;
            foodLeft.textProperty().set(Integer.toString(Main.food));
        }
    }

    @FXML
    private void buyEnergy() {
        if (Main.energy == 0) {
            System.out.println("No energy left!");
            energyBut.setDisable(true);
        } else {
            energyBut.setDisable(false);
            int currentMoney = Main.getCurrentPlayer().getMoney();
            Main.getCurrentPlayer().setMoney(currentMoney - 25);
            System.out.println(Main.getCurrentPlayer().getMoney());
            Main.energy--;
            energyLeft.textProperty().set(Integer.toString(Main.energy));
        }
    }

    @FXML
    private void back(MouseEvent event) {
        try {
            Scene town = new Scene(FXMLLoader.load(getClass().getResource("map.fxml")));
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(town);
            t.setTitle("Player Configuration");
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }
}
