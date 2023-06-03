package adminPanel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class adminMainPageController implements Initializable{

    public Label homeIcon, restaurantsIcon, exitIcon;
    public BorderPane mainPane;

    public void homeLabelClicked() throws IOException{
        mainPane.setCenter(new FxmlLoader().getPage("adminWelcomePage.fxml"));
    }

    public void restaurantsLabelClicked(){
        // mainPane.getCenter().setVisible(false);
        try {
            mainPane.setCenter(new FxmlLoader().getPage("adminRestaurantManagementPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitLabelClicked(){
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mainPane.setCenter(new FxmlLoader().getPage("adminWelcomePage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
