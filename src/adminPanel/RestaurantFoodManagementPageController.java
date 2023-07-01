package adminPanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import general.methods;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.src.Food;
import server.src.Restaurant;
import userPanel.UserClient;

public class RestaurantFoodManagementPageController implements Initializable {

    public static Restaurant restaurant;
    private Stage addBox;
    public static Stage addBoxCopy;

    @FXML
    private Button addFoodButton;

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

    @FXML
    void addFoodButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        addBox = new Stage();
        addBoxCopy = addBox;
        Parent root = FXMLLoader.load(getClass().getResource("addFoodBox.fxml"));
        addBox.setScene(new Scene(root));
        addBox.setTitle("Add Food");
        addBox.initModality(Modality.APPLICATION_MODAL);
        addBox.showAndWait();
        methods.addFoodsToTilePane(centerTilePane, restaurant, 'A', adminClient.toServer, adminClient.fromServer);
        // addFoodsToTilePane(centerTilePane, adminClient.toServer, adminClient.fromServer);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            restaurant = (Restaurant)adminClient.fromServer.readObject();
            // addFoodsToTilePane(centerTilePane, adminClient.toServer, adminClient.fromServer);
            methods.addFoodsToTilePane(centerTilePane, restaurant, 'A', adminClient.toServer, adminClient.fromServer);
        }catch(Exception e){
            e.printStackTrace();
        }
        titleLabel.setText(restaurant.getName());
    }

    private void addFoodsToTilePane(TilePane pane, ObjectOutputStream toServer, ObjectInputStream fromServer) throws IOException, ClassNotFoundException{
        int size;
        String request = "Get Foods";
        toServer.writeObject(request);
        toServer.writeObject(restaurant.getName());
        pane.getChildren().clear();
        size = (Integer)fromServer.readObject();
        for(int i = 0; i < size; i++){
            pane.getChildren().add(new FoodTile((Food) adminClient.fromServer.readObject()));
        }
    }
    

}
