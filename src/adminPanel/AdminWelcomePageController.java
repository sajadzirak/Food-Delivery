package adminPanel;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AdminWelcomePageController {

    public void restaurantsLabelClicked() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("adminRestaurantsPage.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        adminClient.window.setScene(scene);
    }
}
