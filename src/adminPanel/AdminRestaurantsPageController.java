package adminPanel;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class AdminRestaurantsPageController {
    
    // public TilePane tilePane;
    // public ListItem listItem;
    public void logoLabelClicked() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("adminWelcomePage.fxml"));
        adminClient.window.setScene(new Scene(root));
    }

    public void addButton() throws IOException{
        Stage addBox = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addRestaurantBox.fxml"));
        addBox.setScene(new Scene(root));
        addBox.setTitle("Add Restaurant");
        addBox.show();
        // listItem = new ListItem();
        // tilePane.getChildren().add(listItem);
        // System.out.println("clicked");
    }
}
