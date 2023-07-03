package main.adminPanel.controllers;

import java.io.IOException;
import java.net.URL;
import main.adminPanel.AdminClient;
import main.classes.Restaurant;
import main.classes.methods;
import main.server.DataBase;
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

public class RestaurantFoodManagementPageController implements Initializable {

    public static Restaurant restaurant;
    private Stage addBox;

    @FXML
    private Button addFoodButton;

    @FXML
    private TilePane centerTilePane;
    public static TilePane centerTilePaneCopy;

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
        Parent root = FXMLLoader.load(getClass().getResource(DataBase.adminViewPath+"addFoodBox.fxml"));
        addBox.setScene(new Scene(root));
        addBox.setTitle("Add Food");
        addBox.initModality(Modality.APPLICATION_MODAL);
        addBox.showAndWait();
        methods.addFoodsToTilePane(centerTilePane, restaurant, 'A', AdminClient.toServer, AdminClient.fromServer);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            centerTilePaneCopy = centerTilePane;
            restaurant = (Restaurant)AdminClient.fromServer.readObject();
            methods.addFoodsToTilePane(centerTilePane, restaurant, 'A', AdminClient.toServer, AdminClient.fromServer);
        }catch(Exception e){
            e.printStackTrace();
        }
        titleLabel.setText(restaurant.getName());
    }
    
    public static void refresh() throws ClassNotFoundException, IOException {
        methods.addFoodsToTilePane(centerTilePaneCopy, restaurant, 'A', AdminClient.toServer, AdminClient.fromServer);
    }
}
