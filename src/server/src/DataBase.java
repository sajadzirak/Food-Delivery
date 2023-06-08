package server.src;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.ArrayList;

public class DataBase {
    
    public static String imageAbsolutePath = "file:/home/sajad/A/java/myProjects/restaurantManagement/src/DB/images/";
    private ArrayList<Restaurant> restaurantList;
    private static String password = "123";

    public DataBase() throws ClassNotFoundException, IOException{
        restaurantList = new ArrayList<>();
        readRestaurants();
    }
    
    public ArrayList<Restaurant> getRestaurantList() {
        System.out.println("from get Restaurant list : "+restaurantList);
        return restaurantList;
    }

    public void setRestaurantList(ArrayList<Restaurant> restaurantList) {
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
        ObjectInputStream input;
        try{
            input = new ObjectInputStream(restaurantF);
            while(true){
                restaurantList.add((Restaurant)input.readObject());
            }
        }catch (EOFException e){
        }
    }

    public void writeRestaurants() throws IOException{
        FileOutputStream restaurantF = new FileOutputStream("/home/sajad/A/java/myProjects/restaurantManagement/src/DB/files/restaurants.dat");
        ObjectOutputStream output = new ObjectOutputStream(restaurantF);
        for(Restaurant r : restaurantList){
            output.writeObject(r);
        }
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
