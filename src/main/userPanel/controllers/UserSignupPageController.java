package main.userPanel.controllers;

import main.classes.User;
import main.classes.methods;
import main.server.DataBase;
import main.userPanel.UserClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UserSignupPageController implements Initializable{

    private Alert alert;

    @FXML
    private TextField addressField;

    @FXML
    private AnchorPane background;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private VBox container;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button signupButton;

    @FXML
    private Label signupLabel;

    @FXML
    private TextField usernameField;

    @FXML
    void signupButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {

        String request = "New User";
        boolean respond, checkEmptyFields;
        User user;
        
        checkEmptyFields = methods.checkForEmptyTextField(usernameField, phoneNumberField, emailField,
        addressField, passwordField, confirmPasswordField);

        if(!checkEmptyFields){
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Please fill the empty fields!");
            alert.showAndWait();
        }
        else if(!checkEmailField()){
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Invalid email address!");
            alert.showAndWait();
        }
        else if(!checkPhoneNumberField()){
            alert.setContentText("Invalid phone number!");
            alert.showAndWait();
        }
        else if(!checkPasswordField()){
            alert.setContentText("Password must include at least 4 characters!");
            alert.showAndWait();
        }
        else if(!checkPasswordConfirm()){
            alert.setContentText("password confirmation is incorrect!");
            alert.showAndWait();
        }
        else{
            user = new User(usernameField.getText(), phoneNumberField.getText(), emailField.getText(),
            passwordField.getText(), addressField.getText());
            UserClient.toServer.writeObject(request);
            UserClient.toServer.writeObject(user);
            respond = (Boolean) UserClient.fromServer.readObject();
            if(respond){
                alert.setAlertType(AlertType.INFORMATION);
                alert.setContentText("Signed uped successfully!");
                alert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("userLoginPage.fxml"));
                UserClient.window.setScene(new Scene(root));
            }
            else{
                alert.setContentText("Something went wrong! \nmaybe this user has signed up before");
                alert.showAndWait();
            }
        }

    }

    @FXML
    void backToLoginClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(DataBase.userViewPath+"userLoginPage.fxml"));
        UserClient.window.setScene(new Scene(root));
    }

    private boolean checkEmailField(){
        return Pattern.matches(".+@[ge]mail.com", emailField.getText());
    }

    private boolean checkPhoneNumberField(){
        return Pattern.matches("[0-9]{5}", phoneNumberField.getText());
    }

    private boolean checkPasswordField(){
        return passwordField.getText().length()>3?true:false;
    }

    private boolean checkPasswordConfirm(){
        return passwordField.getText().equals(confirmPasswordField.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
    }

}
