package server.src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.image.Image;

public class Restaurant implements Serializable{

    public static enum restaurantType{fastfood, Iranian, Chinese, Italian};
    private String name, restaurantAddress;
    private restaurantType restaurantType;
    private ArrayList<Food> foodList;
    private HashMap<Food, Integer> foodQuantity;
    private boolean outdoor, disable;
    private String restaurantImagePath;
    private int chairNumber, deliveryNumber;
    // implement score for every restaurnat

    
    public Restaurant(String name, String restaurantAddress, Restaurant.restaurantType restaurantType,
            ArrayList<Food> foodList, HashMap<Food, Integer> foodQuantity, boolean outdoor,
            String restaurantImagePath, int chairNumber, int deliveryNumber) {
        this.name = name;
        this.restaurantAddress = restaurantAddress;
        this.restaurantType = restaurantType;
        this.foodList = foodList;
        this.foodQuantity = foodQuantity;
        this.outdoor = outdoor;
        this.restaurantImagePath = restaurantImagePath;
        this.chairNumber = chairNumber;
        this.deliveryNumber = deliveryNumber;
        disable = false;
    }

    public Restaurant(String name, String restaurantAddress, Restaurant.restaurantType restaurantType,
    boolean outdoor, String restaurantImagePath, int chairNumber, int deliveryNumber) {
        this.name = name;
        this.restaurantAddress = restaurantAddress;
        this.restaurantType = restaurantType;
        this.outdoor = outdoor;
        this.restaurantImagePath = restaurantImagePath;
        this.chairNumber = chairNumber;
        this.deliveryNumber = deliveryNumber;
        disable = false;
        foodList = new ArrayList<>();
        foodQuantity = new HashMap<>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public restaurantType getrestaurantType() {
        return restaurantType;
    }

    public void setrestaurantType(restaurantType restaurantType) {
        this.restaurantType = restaurantType;
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    public HashMap<Food, Integer> getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(HashMap<Food, Integer> foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public boolean isOutdoor() {
        return outdoor;
    }

    public void setOutdoor(boolean outdoor) {
        this.outdoor = outdoor;
    }

    public String getRestaurantImagePath() {
        return restaurantImagePath;
    }

    public void setRestaurantImagePath(String restaurantImagePath) {
        this.restaurantImagePath = restaurantImagePath;
    }

    public int getChairNumber() {
        return chairNumber;
    }

    public void setChairNumber(int chairNumber) {
        if(chairNumber >= 0){
            this.chairNumber = chairNumber;
        }
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(int deliveryNumber) {
        if(deliveryNumber >= 0){
            this.deliveryNumber = deliveryNumber;
        }
    }

    public boolean isDisable(){
        return disable;
    }

    public void setDisable(boolean status){
        disable = status;
    }

    @Override
    public boolean equals(Object o){
        Restaurant r = (Restaurant) o;
        if(this.getName().equals(r.getName())){
            return true;
        }
        return false;
    }

    // public String toString(){
    //     return name;
    // }
}