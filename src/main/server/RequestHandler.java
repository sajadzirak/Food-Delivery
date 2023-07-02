package main.server;

import main.classes.Food;
import main.classes.Restaurant;
import main.classes.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class RequestHandler {
    
    private String request;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public RequestHandler(String request, ObjectOutputStream output, ObjectInputStream input) throws ClassNotFoundException, IOException{
        this.request = request;
        this.output = output;
        this.input = input;
        determineRequest(input, output);
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public void setInput(ObjectInputStream input) {
        this.input = input;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }

    public void determineRequest(ObjectInputStream input, ObjectOutputStream output) throws ClassNotFoundException, IOException{
        System.out.println("first request: "+request);
        if(request.equals("Login Admin")){
            adminLogin(input, output);
        }
        else if(request.equals("Get Restaurants")){
            sendRestaurantsList(input, output);
        }
        else if(request.equals("New Restaurant")){
            newRestaurant(input, output);
        }
        else if(request.equals("Get Restaurant")){
            sendRestaurant(input, output);
        }
        else if(request.equals("Edit Restaurant")){
            editRestaurant(input, output);
        }
        else if(request.equals("New Food")){
            newFood(input, output);
        }
        else if(request.equals("Get Foods")){
            sendFoodList(input, output);
        }
        else if(request.equals("Delete Restaurant")){
            deleteRestaurant(input, output);
        }
        else if(request.equals("Set Disable")){
            disableRestaurant(input, output);
        }
        else if(request.equals("New User")){
            newUser(input, output);
        }
        else if(request.equals("Login User")){
            userLogin(input, output);
        }
        else if(request.equals("Get User")){
            sendUser(input, output);
        }
        else if(request.equals("Update User")) {
            updateUser(input, output);
        }
        else if(request.equals("Buy")) {
            buy(input, output);
        }
        else if(request.equals("Get Food")) {
            sendFood(input, output);
        }
        else if(request.equals("Edit Food")) {
            editFood(input, output);
        }
    }

    private void adminLogin(ObjectInputStream fromClient, ObjectOutputStream toClient) throws IOException, ClassNotFoundException{
        String respond;
        String password = (String) fromClient.readObject();
        if(password.equals(DataBase.getPassword())){
            respond = "true";
        }
        else{
            respond = "false";
        }
        toClient.writeObject(respond);
    }

    private void sendRestaurantsList(ObjectInputStream fromClient, ObjectOutputStream toClient) throws IOException{
        ArrayList<Restaurant> list = Server.db.getRestaurantList();
        toClient.writeObject(list.size());
        for(int i = 0; i < list.size(); i++){
            toClient.writeObject(list.get(i));
        }
    }

    private void newRestaurant(ObjectInputStream fromClient, ObjectOutputStream toClient) throws IOException, ClassNotFoundException{
        // int chairNumber, deliveryNumber;
        // String name, address, imagePath;
        // restaurantType type;
        // boolean outdoor;

        // name = (String)fromClient.readObject();
        // address = (String) fromClient.readObject();
        // type = restaurantType.valueOf((String) fromClient.readObject());
        // outdoor = (Boolean) fromClient.readObject();
        // imagePath = (String) fromClient.readObject();
        // chairNumber = (Integer) fromClient.readObject();
        // deliveryNumber = (Integer) fromClient.readObject();
        // File file = new File(imagePath);

        // Restaurant nr = new Restaurant(name, address, type, outdoor, file.toURI().toString(), chairNumber, deliveryNumber);
        Restaurant nr = (Restaurant) fromClient.readObject();
        System.out.println("before adding");
        boolean result = Server.db.addRestaurant(nr);
        toClient.writeObject(result);
        System.out.println("end of new res result: "+result);
    }
    
    private void sendRestaurant(ObjectInputStream fromClient, ObjectOutputStream toClient) throws ClassNotFoundException, IOException{
        String name;
        name = (String) fromClient.readObject();
        int index;
        index = Server.db.findRestaurant(name);
        toClient.writeObject(Server.db.getRestaurantList().get(index));
    }
    
    private void editRestaurant(ObjectInputStream fromClient, ObjectOutputStream toClient) throws ClassNotFoundException, IOException{
        Restaurant edited;
        String previousName;
        previousName = (String)fromClient.readObject();
        edited = (Restaurant)fromClient.readObject();
        boolean result = Server.db.replaceRestaurant(previousName, edited);
        toClient.writeObject(result);
    }

    private void newFood(ObjectInputStream fromClient, ObjectOutputStream toClient) throws ClassNotFoundException, IOException{
        Food food;
        String restaurantName;
        int quantity;

        restaurantName = (String) fromClient.readObject();
        food = (Food) fromClient.readObject();
        quantity = (Integer) fromClient.readObject();
        boolean addResult = Server.db.addFoodToRestaurant(restaurantName, food);       
        boolean quantityResult = Server.db.setFoodQuantity(restaurantName, food, quantity);
        if(addResult && quantityResult){
            toClient.writeObject(true);
        }
        else{
            toClient.writeObject(false);
        }
    }

    private void sendFoodList(ObjectInputStream fromClient, ObjectOutputStream toClient) throws ClassNotFoundException, IOException{
        String restaurantName;
        int index;
        ArrayList<Food> foodArrayList;
        Object[] foodList, quantities;
        HashMap<Food, Integer> foodHash;

        restaurantName = (String)fromClient.readObject();
        index = Server.db.findRestaurant(restaurantName);
        foodHash = Server.db.getRestaurantList().get(index).getFoodQuantity();
        foodList = foodHash.keySet().toArray();
        quantities = foodHash.values().toArray();
        toClient.writeObject(foodHash.size());
        for(int i = 0; i < foodHash.size(); i++){
            toClient.writeObject(foodList[i]);
            toClient.writeObject(quantities[i]);
        }  
    }

    private void deleteRestaurant(ObjectInputStream fromClient, ObjectOutputStream toClient) throws ClassNotFoundException, IOException{
        Restaurant restaurant;

        restaurant = (Restaurant)fromClient.readObject();
        Server.db.getRestaurantList().remove(restaurant);
    }

    private void disableRestaurant(ObjectInputStream fromClient, ObjectOutputStream toClient) throws ClassNotFoundException, IOException{
        boolean status;
        String name;
        int index;

        name = (String) fromClient.readObject();
        status = (Boolean) fromClient.readObject();
        index = Server.db.findRestaurant(name);
        Server.db.getRestaurantList().get(index).setDisable(status);
    }

    private void newUser(ObjectInputStream fromClient, ObjectOutputStream toClient) throws ClassNotFoundException, IOException{
        User user;
        user = (User) fromClient.readObject();
        boolean result = Server.db.addUser(user);
        toClient.writeObject(result);
    }

    private void userLogin(ObjectInputStream fromClient, ObjectOutputStream toClient) throws ClassNotFoundException, IOException{
        String username, password;
        boolean respond;

        username = (String) fromClient.readObject();
        password = (String) fromClient.readObject();
        respond = Server.db.checkUserPass(username, password);
        toClient.writeObject(respond);
    }

    private void sendUser(ObjectInputStream fromClient, ObjectOutputStream toClient) throws ClassNotFoundException, IOException{
        String username;
        int index;

        username = (String) fromClient.readObject();
        index = Server.db.findUser(username);
        toClient.writeObject(Server.db.getUserList().get(index));
    }

    private void updateUser(ObjectInputStream fromClient, ObjectOutputStream toClient) throws ClassNotFoundException, IOException {
        User newUser = (User) fromClient.readObject();
        Server.db.replaceUser(newUser.getUsername(), newUser);
    }

    private void buy(ObjectInputStream fromClient, ObjectOutputStream toClient) throws ClassNotFoundException, IOException {
        int size = (Integer) fromClient.readObject();
        int quantity, resIndex;
        String restaurantName;
        Food food;
        Restaurant restaurant;
        for(int i = 0; i < size; i++) {
            restaurantName = (String) fromClient.readObject();
            food = (Food) fromClient.readObject();
            quantity = (Integer) fromClient.readObject();
            resIndex = Server.db.findRestaurant(restaurantName);
            restaurant = Server.db.getRestaurantList().get(resIndex);
            // System.out.println(restaurant.getFoodQuantity());
            // System.out.println(food);
            // System.out.println(restaurant.getFoodQuantity().get(food));
            // System.out.println(restaurant.getFoodQuantity().containsKey(food));
            for (Food f: restaurant.getFoodQuantity().keySet()) {
                if(f.equals(food)) {
                    // System.out.println(restaurant.getFoodQuantity().get(f));  
                    restaurant.getFoodQuantity().replace(f, restaurant.getFoodQuantity().get(f)-quantity);     
                }
            }
            // I don't know why below .get(food) returns null even it exists
            // restaurant.getFoodQuantity().replace(food, restaurant.getFoodQuantity().get(food)-quantity);
        }
    }

    private void sendFood(ObjectInputStream fromClient, ObjectOutputStream toClient) throws ClassNotFoundException, IOException {
        String restaurantName;
        int resIndex;
        Food food;
        Restaurant restaurant;

        restaurantName = (String) fromClient.readObject();
        food = (Food) fromClient.readObject();
        resIndex = Server.db.findRestaurant(restaurantName);
        restaurant = Server.db.getRestaurantList().get(resIndex);
        for(Food f : restaurant.getFoodQuantity().keySet()) {
            if(f.equals(food)) {
                toClient.writeObject(restaurantName);
                toClient.writeObject(f);
                toClient.writeObject(restaurant.getFoodQuantity().get(f));
            }
        }
    }

    private void editFood(ObjectInputStream fromClient, ObjectOutputStream toClient) throws ClassNotFoundException, IOException {
        String restaurantName, previousName;
        Food newFood;
        int newQuantity;
        boolean respond;

        restaurantName = (String) fromClient.readObject();
        previousName = (String) fromClient.readObject();
        newFood = (Food) fromClient.readObject();
        newQuantity = (Integer) fromClient.readObject();
        respond = Server.db.replaceFood(restaurantName, previousName, newFood, newQuantity);
        toClient.writeObject(respond);
    }
}
