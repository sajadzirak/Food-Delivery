import javafx.scene.image.Image;

public class Food {
    
    public static enum foodType{FASTFOOD, IRANIAN, CHINESE, ITALIAN};
    private String foodName;
    private double foodWeight, foodPrice;
    private foodType foodType;
    private Image foodImage;

    public Food(String foodName, double foodWeight, double foodPrice, foodType foodType){
        this.foodName = foodName;
        this.foodWeight = foodWeight;
        this.foodPrice = foodPrice;
        this.foodType = foodType;
    }

    public Food(String foodName, double foodWeight, double foodPrice, foodType foodType, Image foodImage){
        this.foodName = foodName;
        this.foodWeight = foodWeight;
        this.foodPrice = foodPrice;
        this.foodType = foodType;
        this.foodImage = foodImage;
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

    public Image getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(Image foodImage) {  // maybe it has to handle exceptions
        this.foodImage = foodImage;
    }

    
}
