package adminPanel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import server.src.Restaurant;

public class RestaurantFoodManagementPageController implements Initializable {

    public static Restaurant restaurant;
    private Stage addBox;

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
    void addFoodButtonClicked(ActionEvent event) throws IOException {
        addBox = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addFoodBox.fxml"));
        addBox.setScene(new Scene(root));
        addBox.setTitle("Add Food");
        addBox.initModality(Modality.APPLICATION_MODAL);
        addBox.showAndWait();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            restaurant = (Restaurant)adminClient.fromServer.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        titleLabel.setText(restaurant.getName());
    }
    

}
