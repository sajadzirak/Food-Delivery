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
    
    private Stage addBox;
    public static Stage addBoxCopy;
    public TilePane centerTilePane;
    public static TilePane centerTilePaneCopy;
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
        addBoxCopy = addBox;
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
            centerTilePaneCopy = centerTilePane;
            addRestaurantsToTilePane(centerTilePane, adminClient.toServer, adminClient.fromServer);
            // socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void addRestaurantsToTilePane(TilePane pane, ObjectOutputStream toServer, ObjectInputStream fromServer) throws IOException, ClassNotFoundException{
        int size;
        String request = "Get Restaurants";
        toServer.writeObject(request);
        pane.getChildren().clear();
        size = (Integer)fromServer.readObject();
        for(int i = 0; i < size; i++){
            pane.getChildren().add(new RestaurantTile((Restaurant)fromServer.readObject()));
        }
    }

    public static void refresh() throws ClassNotFoundException, IOException{
        addRestaurantsToTilePane(centerTilePaneCopy, adminClient.toServer, adminClient.fromServer);
    }

}
