package main.userPanel;

import main.classes.User;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import main.server.Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserClient extends Application{

    public static User currentUser;
    public static Stage window;
    public static Socket clientSocket;
    public static ObjectOutputStream toServer;
    public static ObjectInputStream fromServer;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        clientSocket = new Socket(Server.IP, Server.PORT);
        toServer = new ObjectOutputStream(clientSocket.getOutputStream());
        fromServer = new ObjectInputStream(clientSocket.getInputStream());
        window.setOnCloseRequest(
            e->{
                try {
                    toServer.writeObject("Update User");
                    toServer.writeObject(currentUser);
                    toServer.writeObject("exit");
                } catch (Exception e1){
                    e1.printStackTrace();
                }

            }
        );
        Parent root = FXMLLoader.load(getClass().getResource("userLoginPage.fxml"));
        window.setTitle("User Panel");
        window.setScene(new Scene(root, 1280, 720));
        window.show();
    }



}
