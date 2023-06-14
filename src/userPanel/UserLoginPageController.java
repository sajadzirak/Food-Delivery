package userPanel;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UserLoginPageController {

    private Alert alert;

    @FXML
    private AnchorPane background;

    @FXML
    private VBox container;

    @FXML
    private Hyperlink dontHaveAccountLink;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        String request = "Login User";
        boolean respond;
        
        UserClient.toServer.writeObject(request);
        UserClient.toServer.writeObject(usernameField.getText());
        UserClient.toServer.writeObject(passwordField.getText());
        respond = (Boolean) UserClient.fromServer.readObject();
        if(respond){
            // alert.setAlertType(AlertType.INFORMATION);
            // alert.setContentText("logged in successfully!");
            // alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("userMainPage.fxml"));
            UserClient.window.setScene(new Scene(root));
            // UserClient.toServer.writeObject("Get User");
            // UserClient.toServer.writeObject(usernameField.getText());
        }
        else{
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("User login");
            alert.setHeaderText(null);
            alert.setContentText("username or password is wrong!");
            alert.showAndWait();
        }
    }

    @FXML
    void dontHaveAccountClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userSignupPage.fxml"));
        UserClient.window.setScene(new Scene(root));
    }

}

