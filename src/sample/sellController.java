package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by RichardWang on 10/15/15.
 */
public class sellController implements Initializable{

    @FXML
    private Label playerFoodAmm;

    @FXML
    private Label playerEnergyAmm;

    @FXML
    private Label playerFood;

    @FXML
    private Label playerEnergy;

    @FXML
    private Label playerOre;

    @FXML
    private Label playerNameID;

    @FXML
    private Label playerMoneyLabel;
//
//    @FXML
//    private Button foodBut;
//
//    @FXML
//    private Button energyBut;
//
//    @FXML
//    private Button muleBut;
//
//    @FXML
//    private Button oreBut;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        playerEnergy.textProperty().set("x" + Integer.toString(Main.energy));
        playerFood.textProperty().set("x" + Integer.toString(Main.food));
        playerNameID.textProperty().set(Main.getCurrentPlayer().getName());
        playerMoneyLabel.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
        playerEnergyAmm.textProperty().set("Energy: " + Integer.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
        playerFoodAmm.textProperty().set("Food: " + Integer.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
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

    @FXML
    private void sellFood() {
        if (Main.getCurrentPlayer().getSpecificItem(0).getAmount() == 0) {
            System.out.println("No more food!");
        } else {
            Main.food++;
            Main.getCurrentPlayer().getSpecificItem(0).decreaseAmount(1);
            Main.getCurrentPlayer().setMoney(Main.getCurrentPlayer().getMoney() + 30);
            playerMoneyLabel.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
            playerFood.textProperty().set("x" + Integer.toString(Main.food));
            playerEnergyAmm.textProperty().set("Energy: " + Integer.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
            playerFoodAmm.textProperty().set("Food: " + Integer.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
        }
    }

    @FXML
    private void sellEnergy() {
        if (Main.getCurrentPlayer().getSpecificItem(1).getAmount() == 0) {
            System.out.println("No more energy!");
        } else {
            Main.getCurrentPlayer().getSpecificItem(1).decreaseAmount(1);
            Main.energy++;
            Main.getCurrentPlayer().setMoney(Main.getCurrentPlayer().getMoney() + 25);
            playerMoneyLabel.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
            playerEnergy.textProperty().set("x" + Integer.toString(Main.energy));
            playerEnergyAmm.textProperty().set("Energy: " + Integer.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
            playerFoodAmm.textProperty().set("Food: " + Integer.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
        }
    }
}
