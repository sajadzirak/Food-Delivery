package adminPanel;

import java.io.IOException;
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
        Parent root = FXMLLoader.load(getClass().getResource("adminLoginPage.fxml"));
        window.setTitle("Admin Login");
        window.setScene(new Scene(root, 1280, 720));
        window.show();
    }
}
