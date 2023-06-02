package server.src;

import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Restaurant{

    public static enum restaurantType{fastfood, Iranian, Chinese, Italian};
    private String name, restaurantAddress;
    private restaurantType restaurantType;
    private ObservableList<Food> foodList;
    private HashMap<Food, Integer> foodQuantity;
    private boolean outdoor;
    private Image restaurantImage;
    private int chairNumber, deliveryNumber;
    // implement score for every restaurnat

    
    public Restaurant(String name, String restaurantAddress, Restaurant.restaurantType restaurantType,
            ObservableList<Food> foodList, HashMap<Food, Integer> foodQuantity, boolean outdoor,
            Image restaurantImage, int chairNumber, int deliveryNumber) {
        this.name = name;
        this.restaurantAddress = restaurantAddress;
        this.restaurantType = restaurantType;
        this.foodList = foodList;
        this.foodQuantity = foodQuantity;
        this.outdoor = outdoor;
        this.restaurantImage = restaurantImage;
        this.chairNumber = chairNumber;
        this.deliveryNumber = deliveryNumber;
    }

    public Restaurant(String name, String restaurantAddress, Restaurant.restaurantType restaurantType,
    boolean outdoor, Image restaurantImage, int chairNumber, int deliveryNumber) {
        this.name = name;
        this.restaurantAddress = restaurantAddress;
        this.restaurantType = restaurantType;
        this.outdoor = outdoor;
        this.restaurantImage = restaurantImage;
        this.chairNumber = chairNumber;
        this.deliveryNumber = deliveryNumber;
    }

    public Restaurant(String name, String restaurantAddress, Restaurant.restaurantType restaurantType,
    boolean outdoor, int chairNumber, int deliveryNumber) {
        this.name = name;
        this.restaurantAddress = restaurantAddress;
        this.restaurantType = restaurantType;
        this.outdoor = outdoor;
        this.chairNumber = chairNumber;
        this.deliveryNumber = deliveryNumber;
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

    public ObservableList<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(ObservableList<Food> foodList) {
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

    public Image getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(Image restaurantImage) {
        this.restaurantImage = restaurantImage;
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

    @Override
    public boolean equals(Object o){
        Restaurant r = (Restaurant) o;
        if(this.getName().equals(r.getName())){
            return true;
        }
        return false;
    }
}