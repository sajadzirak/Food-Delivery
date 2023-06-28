package server.src;

import java.util.ArrayList;

public class Cart {
    
    private ArrayList<Order> orders;
    private String username;
    private double cost;
    
    public Cart(ArrayList<Order> orders, String username, double cost) {
        this.orders = orders;
        this.username = username;
        this.cost = cost;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }    
}
