package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by RichardWang on 10/15/15.
 */
public class buyController implements Initializable {

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
    private void buyFood() {
        if (Main.food == 0 || Main.getCurrentPlayer().getMoney() < 30) {
            if (Main.food == 0) {
                System.out.println("No more food!");
            } else {
                System.out.println("No more money!");
            }
        } else {
            Main.getCurrentPlayer().getSpecificItem(0).increaseAmount(1);
            Main.food--;
            Main.getCurrentPlayer().setMoney(Main.getCurrentPlayer().getMoney() - 30);
            playerMoneyLabel.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
            playerFoodAmm.textProperty().set("Food: " + Integer.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
            playerFood.textProperty().set("x" + Integer.toString(Main.food));
        }
    }

    @FXML
    private void buyEnergy() {
        if (Main.energy == 0 || Main.getCurrentPlayer().getMoney() < 25) {
            if (Main.energy == 0) {
                System.out.println("No more energy!");
            } else {
                System.out.println("No more money!");
            }
        } else {
            Main.getCurrentPlayer().getSpecificItem(1).increaseAmount(1);
            Main.energy--;
            Main.getCurrentPlayer().setMoney(Main.getCurrentPlayer().getMoney() - 25);
            playerMoneyLabel.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
            playerEnergyAmm.textProperty().set("Energy: " + Integer.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
            playerEnergy.textProperty().set("x" + Integer.toString(Main.energy));
        }
    }

    @FXML
    private void buyMule(MouseEvent event) {

            if (Main.getCurrentPlayer().getMoney() < 100) {
                System.out.println("Not Enough Money");
            }
            else {
                try {
                    Main.getCurrentPlayer().setMoney(Main.getCurrentPlayer().getMoney() - 100);
                    playerMoneyLabel.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
                    if (Main.getCurrentPlayer().getMoney() < 25) {
                        return;
                    }
                    Scene town = new Scene(FXMLLoader.load(getClass().getResource("buyMuleScreen.fxml")));
                    Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    t.setScene(town);
                    t.setTitle("Sell Screen");
                    t.show();
                }
                catch (Exception e) {
                    throw new IllegalArgumentException("No file");
                }
            }

    }
}
