package adminPanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.src.Restaurant;
import server.src.Server;

public class adminRestaurantManagementPageController implements Initializable {
    
    public static ArrayList<Restaurant> restaurantsList;
    public static Stage addBox;
    public TilePane centerTilePane;
    private ObservableList tiles;

    public void addRestaurantButtonClicked() throws IOException{
        addBox = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addRestaurantBox.fxml"));
        addBox.setScene(new Scene(root));
        addBox.setTitle("Add Restaurant");
        addBox.initModality(Modality.APPLICATION_MODAL);
        addBox.showAndWait();
        // imageTile = new ImageTile();
        // imageTile.setStyle("-fx-background-color: #000;");
        // centerTilePane.getChildren().add(imageTile);
        // System.out.println("clicked");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String request;
        try {
            Socket socket = new Socket(Server.IP, Server.PORT);
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
            // restaurantsList = (ObservableList<Restaurant>)fromServer.readObject();
            request = "Get Restaurants";
            toServer.writeObject(request);
            restaurantsList = (ArrayList<Restaurant>)fromServer.readObject();
            tiles = FXCollections.observableArrayList(restaurantsList);
            System.out.println("list recieved");
            // for(Restaurant r : restaurantsList){
            //     centerTilePane.getChildren().add(new ImageTile(r));
            // }
            centerTilePane.getChildren().addAll(tiles);
            System.out.println("after for");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){

        }
    }

    // public Stage getAddBox(){
    //     return addBox;
    // }
}
