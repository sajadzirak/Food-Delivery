package adminPanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class adminClient extends Application {

    public static Stage window;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        window = primaryStage;
        window.setOnCloseRequest(
            e->{
                try {
                    Socket socket = new Socket("127.0.0.1", server.src.Server.PORT);
                    ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
                    toServer.writeObject("exit");
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        );
        Parent root = FXMLLoader.load(getClass().getResource("adminLoginPage.fxml"));
        window.setTitle("Admin Login");
        window.setScene(new Scene(root, 1280, 720));
        window.show();
    }
}
