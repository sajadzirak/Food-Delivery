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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import main.adminPanel.AdminClient;
import main.server.DataBase;

public class AdminLoginPageController implements Initializable{

    private Alert alert;

    @FXML
    private AnchorPane background;

    @FXML
    private VBox container;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        String password = passwordField.getText();
        String request = "Login Admin";
        boolean respond;

        if(password.length() != 0){
            AdminClient.toServer.writeObject(request);
            AdminClient.toServer.writeObject(password);
            respond = (Boolean) AdminClient.fromServer.readObject();
            if(respond){
                AdminClient.window.close();
                Parent root = FXMLLoader.load(getClass().getResource(DataBase.adminViewPath+"adminMainPage.fxml"));
                AdminClient.window.setTitle("Admin Panel");
                AdminClient.window.setScene(new Scene(root, 1280, 720));
                AdminClient.window.show();
            }
            else{
                alert.setContentText("Wrong password!");
                alert.showAndWait();
            }
        }
        else{
            alert.setContentText("enter a password");
            alert.showAndWait(); 
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error message");
        alert.setHeaderText(null);
    }
}
