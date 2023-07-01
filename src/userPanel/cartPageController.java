package userPanel;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.src.Order;
import server.src.User;

public class cartPageController implements Initializable{

    private Stage paymentStage;
    private Alert alert;

    @FXML
    private HBox buttonBox;

    @FXML
    private TilePane centerTilePane;
    private static TilePane centerTilePaneCopy;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label titleLabel;

    @FXML
    private Label totalCostLabel;
    private static Label totalLabelCopy;

    @FXML
    private Label balanceLabel;

    @FXML
    void cahrgeButtonClicked(ActionEvent event) throws IOException {
        paymentStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("paymentPage.fxml")); 
        paymentStage.setScene(new Scene(root));
        paymentStage.setTitle("payment page");
        paymentStage.initModality(Modality.APPLICATION_MODAL);
        paymentStage.showAndWait();
        balanceLabel.setText("Your Balance: $"+UserClient.currentUser.getBalance());
    }

    @FXML
    void confirmButtonClicked(ActionEvent event) throws IOException {
        if(UserClient.currentUser.getBalance() < findTotalCost()) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Your balance is not enough to confirm the orders!!\nPlease charge your wallet first.");
            alert.showAndWait();
        }
        else {
            alert.setAlertType(AlertType.CONFIRMATION);
            alert.setContentText("Are you sure do you want to confirm ?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.OK)) {
                UserClient.toServer.writeObject("Buy");
                int size = UserClient.currentUser.getCart().getOrders().size();
                UserClient.toServer.writeObject(size);
                for(int i = 0; i < size; i++) {
                    UserClient.toServer.writeObject(UserClient.currentUser.getCart().getOrders().get(i).getRestaurantName());
                    UserClient.toServer.writeObject(UserClient.currentUser.getCart().getOrders().get(i).getFood());
                    UserClient.toServer.writeObject(UserClient.currentUser.getCart().getOrders().get(i).getQuantity());
                }
                UserClient.currentUser.setBalance(UserClient.currentUser.getBalance()-findTotalCost());
                UserClient.currentUser.getCart().getOrders().clear();
            }            
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        centerTilePaneCopy = centerTilePane;
        totalLabelCopy = totalCostLabel;
        addOrdersToTilePane(centerTilePane);
        totalCostLabel.setText("Total: $"+findTotalCost());
        balanceLabel.setText("Your Balance: $"+UserClient.currentUser.getBalance());
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText(null);
    }

    private static void addOrdersToTilePane(TilePane pane) {
        pane.getChildren().clear();
        for(int i = 0; i < UserClient.currentUser.getCart().getOrders().size(); i++) {
            pane.getChildren().add(new OrderTile(UserClient.currentUser.getCart().getOrders().get(i)));
        }
    }

    public static void refresh() {
        addOrdersToTilePane(centerTilePaneCopy);
        totalLabelCopy.setText("Total: $"+findTotalCost());
    }

    private static double findTotalCost() {
        double total = 0;
        for(Node tile : centerTilePaneCopy.getChildren()) {
            total += ((OrderTile)tile).getOrder().getCost();
        }
        return total;
    }

}
