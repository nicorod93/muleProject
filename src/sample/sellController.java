package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by RichardWang on 10/15/15.
 */
public class sellController implements Initializable{
    private static org.apache.log4j.Logger log = LogManager.getRootLogger();
    public static final int FOOD_PRICE = 30;
    public static final int ENERGY_PRICE = 25;

    @FXML
    private Label playerFoodAmm;

    @FXML
    private Label playerEnergyAmm;

    @FXML
    private Label playerFood;

    @FXML
    private Label playerEnergy;

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
    public final void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        playerEnergy.textProperty().set("x" + Long.toString(Main.energy));
        playerFood.textProperty().set("x" + Long.toString(Main.food));
        playerNameID.textProperty().set(Main.getCurrentPlayer().getName());
        playerMoneyLabel.textProperty().set("$" + Long.toString(Main.getCurrentPlayer().getMoney()));
        playerEnergyAmm.textProperty().set("Energy: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
        playerFoodAmm.textProperty().set("Food: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
    }

    @FXML
    private void back(MouseEvent event) {
        try {
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(Main.map);
            t.setTitle("Player Configuration");
            t.show();
        } catch (Exception e) {
            log.error("File Not Found", e);        }
    }

    @FXML
    private void sellFood() {
        if (Main.getCurrentPlayer().getSpecificItem(0).getAmount() == 0) {
            return;
        } else {
            Main.food++;
            Main.getCurrentPlayer().getSpecificItem(0).decreaseAmount(1);
            Main.getCurrentPlayer().setMoney(Main.getCurrentPlayer().getMoney() + FOOD_PRICE);
            playerMoneyLabel.textProperty().set("$" + Long.toString(Main.getCurrentPlayer().getMoney()));
            playerFood.textProperty().set("x" + Long.toString(Main.food));
            playerEnergyAmm.textProperty().set("Energy: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
            playerFoodAmm.textProperty().set("Food: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
        }
    }

    @FXML
    private void sellEnergy() {
        if (Main.getCurrentPlayer().getSpecificItem(1).getAmount() == 0) {
            return;
        } else {
            Main.getCurrentPlayer().getSpecificItem(1).decreaseAmount(1);
            Main.energy++;
            Main.getCurrentPlayer().setMoney(Main.getCurrentPlayer().getMoney() + ENERGY_PRICE);
            playerMoneyLabel.textProperty().set("$" + Long.toString(Main.getCurrentPlayer().getMoney()));
            playerEnergy.textProperty().set("x" + Long.toString(Main.energy));
            playerEnergyAmm.textProperty().set("Energy: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
            playerFoodAmm.textProperty().set("Food: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
        }
    }
}
