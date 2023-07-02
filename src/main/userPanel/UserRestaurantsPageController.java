package main.userPanel;

import main.classes.Restaurant;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.fxml.Initializable;

public class UserRestaurantsPageController implements Initializable{

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
        try{
            addRestaurantsToTilePane(centerTilePane, UserClient.toServer, UserClient.fromServer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void addRestaurantsToTilePane(TilePane pane, ObjectOutputStream toServer, ObjectInputStream fromServer) throws IOException, ClassNotFoundException{
        int size;
        String request = "Get Restaurants";
        toServer.writeObject(request);
        pane.getChildren().clear();
        size = (Integer)fromServer.readObject();
        for(int i = 0; i < size; i++){
            pane.getChildren().add(new RestaurantTile((Restaurant)fromServer.readObject()));
        }
    }
    
}
