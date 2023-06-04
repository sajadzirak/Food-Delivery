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
    
    public static Stage addBox;
    public TilePane centerTilePane;
    private Socket socket;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
    private ObservableList tiles;

    {
        try{
            socket = new Socket(Server.IP, Server.PORT);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addRestaurantButtonClicked() throws IOException, ClassNotFoundException{
        addBox = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addRestaurantBox.fxml"));
        addBox.setScene(new Scene(root));
        addBox.setTitle("Add Restaurant");
        addBox.initModality(Modality.APPLICATION_MODAL);
        addBox.showAndWait();
        System.out.println("before get");
        addRestaurantsToTilePane(centerTilePane, toServer, fromServer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // tiles = FXCollections.observableArrayList(restaurantsList);
            addRestaurantsToTilePane(centerTilePane, toServer, fromServer);
            // socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addRestaurantsToTilePane(TilePane pane, ObjectOutputStream toServer, ObjectInputStream fromServer) throws IOException, ClassNotFoundException{
        ArrayList<Restaurant> restaurantsList;
        String request = "Get Restaurants";
        System.out.println("before sending get command");
        toServer.writeObject(request);
        System.out.println("after sending request");
        restaurantsList = (ArrayList<Restaurant>)fromServer.readObject();
        System.out.println("after recieving list");
        pane.getChildren().clear();
        for(Restaurant r : restaurantsList){
            pane.getChildren().add(new ImageTile(r));
        }
    }

}
