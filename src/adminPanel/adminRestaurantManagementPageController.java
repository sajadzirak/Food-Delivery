package adminPanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.src.Restaurant;

public class adminRestaurantManagementPageController implements Initializable {
    
    public static Stage addBox;
    public TilePane centerTilePane;
    // private Socket socket;
    // private ObjectOutputStream toServer;
    // private ObjectInputStream fromServer;

    // {
    //     try{
    //         socket = new Socket(Server.IP, Server.PORT);
    //         toServer = new ObjectOutputStream(socket.getOutputStream());
    //         fromServer = new ObjectInputStream(socket.getInputStream());
    //     }catch(Exception e){
    //         e.printStackTrace();
    //     }
    // }

    public void addRestaurantButtonClicked() throws IOException, ClassNotFoundException{
        addBox = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addRestaurantBox.fxml"));
        addBox.setScene(new Scene(root));
        addBox.setTitle("Add Restaurant");
        addBox.initModality(Modality.APPLICATION_MODAL);
        addBox.showAndWait();
        addRestaurantsToTilePane(centerTilePane, adminClient.toServer, adminClient.fromServer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // tiles = FXCollections.observableArrayList(restaurantsList);
            System.out.println("init");
            addRestaurantsToTilePane(centerTilePane, adminClient.toServer, adminClient.fromServer);
            // socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addRestaurantsToTilePane(TilePane pane, ObjectOutputStream toServer, ObjectInputStream fromServer) throws IOException, ClassNotFoundException{
        // ArrayList<Restaurant> restaurantsList = new ArrayList<>();
        int size;
        String request = "Get Restaurants";
        toServer.writeObject(request);
        pane.getChildren().clear();
        size = (Integer)fromServer.readObject();
        for(int i = 0; i < size; i++){
            pane.getChildren().add(new RestaurantTile((Restaurant)fromServer.readObject()));
            // list.add((Restaurant)fromServer.readObject());
        }
        // readRestaurants(restaurantsList, fromServer, toServer);
        // for(Restaurant r : restaurantsList){
        //     pane.getChildren().add(new RestaurantTile(r));
        // }
    }

    private void readRestaurants(ArrayList<Restaurant> list, ObjectInputStream fromServer, ObjectOutputStream toClient) throws ClassNotFoundException, IOException{
        int size;
        size = (Integer)fromServer.readObject();
        for(int i = 0; i < size; i++){
            list.add((Restaurant)fromServer.readObject());
        }
    }

}
