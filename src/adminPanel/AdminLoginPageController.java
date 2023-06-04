package adminPanel;

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

public class AdminLoginPageController {

    public PasswordField passwordField;
    public Label reportLabel;
    
    public void loginButtonClicked() throws UnknownHostException, IOException, ClassNotFoundException{
        String password = passwordField.getText();
        String request = "Login Admin";
        String respond;
        Alert alert;

        if(password.length() != 0){
            Socket socket = new Socket("127.0.0.1", 8000);
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
            toServer.writeObject(request);
            toServer.flush();
            toServer.writeObject(password);
            respond = (String) fromServer.readObject();
            if(respond.equals("true")){
                adminClient.window.close();
                Parent root = FXMLLoader.load(getClass().getResource("adminMainPage.fxml"));
                adminClient.window.setTitle("Admin Panel");
                adminClient.window.setScene(new Scene(root, 1280, 720));
                adminClient.window.show();
            }
            else{
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong password!");
                alert.showAndWait();
            }
            socket.close();
        }    
    }
}
