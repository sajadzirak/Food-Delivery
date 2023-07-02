package main.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable{
    
    private ArrayList<Order> orders;
    private String username;
    private double cost;
    
    public Cart(String username) {
        this.username = username;
        orders = new ArrayList<Order>();
        cost = 0;
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

    public boolean addOrder(Order order) {
        boolean result;
        result = orders.add(order);
        return result;
    }
}
