package adminPanel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class adminClient extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("adminLoginPage.fxml"));
        primaryStage.setTitle("Admin Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
