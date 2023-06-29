package userPanel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class cartPageController implements Initializable{

    @FXML
    private TilePane centerTilePane;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label titleLabel;

    @FXML
    private HBox topMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleLabel.setText("Your Cart");
        addOrdersToTilePane(centerTilePane);
    }

    private void addOrdersToTilePane(TilePane pane) {
        pane.getChildren().clear();
        for(int i = 0; i < UserClient.currentUser.getCart().getOrders().size(); i++) {
            System.out.println(UserClient.currentUser.getCart().getOrders());
            pane.getChildren().add(new OrderTile(UserClient.currentUser.getCart().getOrders().get(i)));
        }
    }

}
