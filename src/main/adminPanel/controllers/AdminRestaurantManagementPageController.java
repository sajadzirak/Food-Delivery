package main.adminPanel.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import main.adminPanel.AdminClient;
import main.classes.methods;
import main.server.DataBase;

public class AdminRestaurantManagementPageController implements Initializable {
    
    private Stage addBox;

    @FXML
    private Button addRestaurantButton;
    
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
    void addRestaurantButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        addBox = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(DataBase.adminViewPath+"addRestaurantBox.fxml"));
        addBox.setScene(new Scene(root));
        addBox.setTitle("Add Restaurant");
        addBox.initModality(Modality.APPLICATION_MODAL);
        addBox.setResizable(false);
        addBox.showAndWait();
        methods.addRestaurantsToTilePane(centerTilePane, 'A', AdminClient.toServer, AdminClient.fromServer);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            centerTilePaneCopy = centerTilePane;
            methods.addRestaurantsToTilePane(centerTilePane, 'A', AdminClient.toServer, AdminClient.fromServer);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void refresh() throws ClassNotFoundException, IOException{
        methods.addRestaurantsToTilePane(centerTilePaneCopy, 'A', AdminClient.toServer, AdminClient.fromServer);
    }

}
