package main.classes;

import java.io.Serializable;

public class Order implements Serializable{
    
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
        cost = food.getFoodPrice()*this.quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if(this.restaurantName.equals(((Order)other).getRestaurantName())) {
            if(this.food.equals(((Order) other).getFood())) {
                result = true;
            }
        }
        return result;
    }

    
}
