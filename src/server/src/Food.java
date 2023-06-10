package server.src;

import java.io.Serializable;

public class Food implements Serializable{
    
    public static enum foodType{fastfood, Iranian, Chinese, Italian};
    private String foodName;
    private double foodWeight, foodPrice;
    private foodType foodType;
    private String foodImagePath;

    public Food(String foodName, double foodWeight, double foodPrice, foodType foodType){
        this.foodName = foodName;
        this.foodWeight = foodWeight;
        this.foodPrice = foodPrice;
        this.foodType = foodType;
    }

    public Food(String foodName, double foodWeight, double foodPrice, foodType foodType, String imagePath){
        this.foodName = foodName;
        this.foodWeight = foodWeight;
        this.foodPrice = foodPrice;
        this.foodType = foodType;
        this.foodImagePath = imagePath;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodWeight() {
        return foodWeight;
    }

    public void setFoodWeight(double foodWeight) {
        if(foodWeight > 0){
            this.foodWeight = foodWeight;
        }
        else{
            // have to implement this section
        }
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        if(foodPrice >= 0){
            this.foodPrice = foodPrice;
        }
        else{
            // have to implement this section
        }
    }

    public foodType getFoodType(){
        return foodType;
    }

    public void setFoodType(foodType foodType){
        this.foodType = foodType;
    }

    public String getFoodImagePath() {
        return foodImagePath;
    }

    public void setFoodImagePath(String foodImagePath) {
        this.foodImagePath = foodImagePath;
    }
    
    @Override
    public boolean equals(Object o){
        Food f = (Food) o;
        if(this.getFoodName().equals(f.getFoodName())){
            return true;
        }
        return false;
    }
}
