package server.src;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;

public class DataBase {
    
    private ObservableList<Restaurant> restaurantList;
    private static String password = "123";

    public DataBase() throws ClassNotFoundException, IOException{
        restaurantList = FXCollections.observableArrayList();
        readRestaurants();
    }
    
    public ObservableList<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(ObservableList<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String newPassword) {
        password = newPassword;
    }
    

    private void readRestaurants() throws IOException, ClassNotFoundException{
        FileInputStream restaurantF = new FileInputStream("/home/sajad/A/java/myProjects/restaurantManagement/src/DB/files/restaurants.dat");
        try{
            ObjectInputStream input = new ObjectInputStream(restaurantF);
            restaurantList =  (ObservableList<Restaurant>) input.readObject();
            input.close();
        }catch (EOFException e){
            
        }
    }

    public void writeRestaurants() throws IOException{
        FileOutputStream restaurantF = new FileOutputStream("/home/sajad/A/java/myProjects/restaurantManagement/src/DB/files/restaurants.dat");
        ObjectOutputStream output = new ObjectOutputStream(restaurantF);
        output.writeObject(restaurantList);
        output.close();
    }

    public boolean addRestaurant(Restaurant newRestaurant){
        if(restaurantList.contains(newRestaurant))
            return false;
        else{
            restaurantList.add(newRestaurant);
            return true;
        }
    }



}
