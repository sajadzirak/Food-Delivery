import javafx.scene.image.Image;

public class Food {
    
    public static enum foodType{FASTFOOD, IRANIAN, FORIEGN};
    private String foodName;
    private double foodWeight, foodPrice;
    private Image foodImage;

    public Food(String foodName, double foodWeight, double foodPrice, Image foodImage){
        this.foodName = foodName;
        this.foodWeight = foodWeight;
        this.foodPrice = foodPrice;
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

    public Image getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(Image foodImage) {  // maybe it has to handle exceptions
        this.foodImage = foodImage;
    }

    
}
