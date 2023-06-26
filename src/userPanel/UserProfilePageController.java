package userPanel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class UserProfilePageController implements Initializable{


    @FXML
    private VBox addressContainer;

    @FXML
    private TextFlow addressTextFlow;

    @FXML
    private HBox balanceContainer;

    @FXML
    private Label balanceLabel;

    @FXML
    private Button editButton;

    @FXML
    private Label emailLabel;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private VBox mainInfoContainer;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    void editButtonClicked(ActionEvent event) {
        
    }

    @FXML
    void paymentButtonClicked(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setText(UserClient.currentUser.getUsername());
        phoneNumberLabel.setText(UserClient.currentUser.getPhoneNumber());
        emailLabel.setText("email: "+UserClient.currentUser.getEmail());
        addressTextFlow.getChildren().add(new Text("Address: "+UserClient.currentUser.getAddress()));
        balanceLabel.setText("$ "+UserClient.currentUser.getBalance());
    }
    
}
