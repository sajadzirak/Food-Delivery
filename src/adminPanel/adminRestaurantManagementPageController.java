package adminPanel;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class adminRestaurantManagementPageController {
    
    public static Stage addBox;
    public TilePane centerTilePane;
    public ImageTile imageTile;

    public void addRestaurantButtonClicked() throws IOException{
        addBox = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addRestaurantBox.fxml"));
        addBox.setScene(new Scene(root));
        addBox.setTitle("Add Restaurant");
        addBox.initModality(Modality.APPLICATION_MODAL);
        addBox.showAndWait();
        imageTile = new ImageTile();
        imageTile.setStyle("-fx-background-color: #000;");
        centerTilePane.getChildren().add(imageTile);
        System.out.println("clicked");
    }

    // public Stage getAddBox(){
    //     return addBox;
    // }
}
