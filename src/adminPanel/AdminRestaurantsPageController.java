package adminPanel;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;

public class AdminRestaurantsPageController {
    
    // public TilePane tilePane;
    // public ListItem listItem;
    public void logoLabelClicked() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("adminWelcomePage.fxml"));
        adminClient.window.setScene(new Scene(root));
    }

    public void addButton(){
        // listItem = new ListItem();
        // tilePane.getChildren().add(listItem);
        // System.out.println("clicked");
    }
}
