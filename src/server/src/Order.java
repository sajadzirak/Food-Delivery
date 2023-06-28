package server.src;

public class Order {
    
    private double cost;
    private String restaurantName;
    private Food food;
    private int quantity;
    
    public Order(String restaurantName, Food food, int quantity) {
        this.restaurantName = restaurantName;
        this.food = food;
        this.quantity = quantity;
        cost = food.getFoodPrice()*quantity;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    
}
