package main.adminPanel.controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import main.adminPanel.AdminClient;

public class AdminLoginPageController {

    public PasswordField passwordField;
    public Label reportLabel;
    
    public void loginButtonClicked() throws UnknownHostException, IOException, ClassNotFoundException{
        String password = passwordField.getText();
        String request = "Login Admin";
        String respond;
        Alert alert;

        if(password.length() != 0){
            // Socket socket = new Socket("127.0.0.1", 8000);
            // ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            // ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
            AdminClient.toServer.writeObject(request);
            AdminClient.toServer.flush();
            AdminClient.toServer.writeObject(password);
            AdminClient.toServer.flush();
            respond = (String) AdminClient.fromServer.readObject();
            if(respond.equals("true")){
                AdminClient.window.close();
                Parent root = FXMLLoader.load(getClass().getResource("adminMainPage.fxml"));
                AdminClient.window.setTitle("Admin Panel");
                AdminClient.window.setScene(new Scene(root, 1280, 720));
                AdminClient.window.show();
            }
            else{
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong password!");
                alert.showAndWait();
            }
            // socket.close();
        }
        else{
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("enter a password");
            alert.showAndWait(); 
        }
    }
}
