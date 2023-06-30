package userPanel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class cartPageController implements Initializable{

    @FXML
    private HBox buttonBox;

    @FXML
    private TilePane centerTilePane;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label titleLabel;

    @FXML
    private AnchorPane topMenu;

    @FXML
    void cahrgeButtonClicked(ActionEvent event) {

    }

    @FXML
    void confirmButtonClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addOrdersToTilePane(centerTilePane);
    }

    private void addOrdersToTilePane(TilePane pane) {
        pane.getChildren().clear();
        for(int i = 0; i < UserClient.currentUser.getCart().getOrders().size(); i++) {
            pane.getChildren().add(new OrderTile(UserClient.currentUser.getCart().getOrders().get(i)));
        }
    }

}
