package main.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import main.adminPanel.others.AdminFoodTile;
import main.adminPanel.others.AdminRestaurantTile;
import main.userPanel.others.UserFoodTile;
import main.userPanel.others.UserRestaurantTile;

public class methods {
    
    public static void addFoodsToTilePane(TilePane pane, Restaurant restaurant, char flag, ObjectOutputStream toServer, ObjectInputStream fromServer) throws IOException, ClassNotFoundException{
        int size;
        String request = "Get Foods";
        toServer.writeObject(request);
        toServer.writeObject(restaurant.getName());
        pane.getChildren().clear();
        size = (Integer)fromServer.readObject();
        if(flag == 'U') {
            for(int i = 0; i < size; i++){
                pane.getChildren().add(new UserFoodTile((Food) fromServer.readObject(), (Integer) fromServer.readObject(), restaurant.getName()));
            }
        }
        else if(flag == 'A') {
            for(int i = 0; i < size; i++){
                pane.getChildren().add(new AdminFoodTile((Food) fromServer.readObject(), (Integer) fromServer.readObject(), restaurant.getName()));
            }
        }
    }

    public static boolean checkForEmptyTextField(TextField...fields) {
        for(TextField tf : fields) {
            if(tf.getText().equals(""))
                return false;
        }
        return true;
    }

    public static void changeOpacity(String id, VBox menu){
        String selectedOpacity = "-fx-opacity: 60%;";
        String defaultOpacity = "-fx-opacity: 100%;";
        for(Node label : menu.getChildren()){
            if(label.getId().equals(id)){
                label.setStyle(selectedOpacity);
            }
            else{
                label.setStyle(defaultOpacity);
            }
        }
    }

    public static void addRestaurantsToTilePane(TilePane pane, char flag, ObjectOutputStream toServer, ObjectInputStream fromServer) throws IOException, ClassNotFoundException {
        int size;
        String request = "Get Restaurants";
        toServer.writeObject(request);
        pane.getChildren().clear();
        size = (Integer)fromServer.readObject();
        if(flag == 'A'){
            for(int i = 0; i < size; i++){
                pane.getChildren().add(new AdminRestaurantTile((Restaurant)fromServer.readObject()));
            }
        }
        else if(flag == 'U') {
            for(int i = 0; i < size; i++){
                pane.getChildren().add(new UserRestaurantTile((Restaurant)fromServer.readObject()));
            }
        }
    }
}
