package userPanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import server.src.Food;
import server.src.Restaurant;
import general.methods;

public class RestaurantFoodsPageController implements Initializable{

    private Restaurant restaurant;
    
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
        
        try {
            restaurant = (Restaurant) UserClient.fromServer.readObject();
            methods.addFoodsToTilePane(centerTilePane, restaurant, 'U', UserClient.toServer, UserClient.fromServer);
        } catch (Exception e){
            e.printStackTrace();
        }
        titleLabel.setText(restaurant.getName());
    }
}
