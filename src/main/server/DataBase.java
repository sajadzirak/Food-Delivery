package main.server;

import main.classes.Food;
import main.classes.Restaurant;
import main.classes.User;
import java.io.*;
import java.util.ArrayList;

public class DataBase {
    
    public static String filePath = "src/resources/files/";
    public static String imageAbsolutePath = "file:/home/sajad/A/java/myProjects/restaurantManagement/src/DB/images/";
    private ArrayList<Restaurant> restaurantList;
    private ArrayList<User> userList;
    private static String password = "123";

    public DataBase() throws ClassNotFoundException, IOException{
        restaurantList = new ArrayList<>();
        userList = new ArrayList<>();
        readRestaurants();
        readUsers();
    }
    
    public ArrayList<Restaurant> getRestaurantList() {
        System.out.println("from get Restaurant list : "+restaurantList);
        return restaurantList;
    }

    public void setRestaurantList(ArrayList<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList){
        this.userList = userList;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String newPassword) {
        password = newPassword;
    }
    

    private void readRestaurants() throws IOException, ClassNotFoundException{
        ObjectInputStream input;
        try(FileInputStream restaurantF = new FileInputStream(filePath+"restaurants.dat");){
            input = new ObjectInputStream(restaurantF);
            while(true){
                restaurantList.add((Restaurant)input.readObject());
            }
        }catch(EOFException e){
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void writeRestaurants() throws IOException{
        FileOutputStream restaurantF = new FileOutputStream(filePath+"restaurants.dat");
        ObjectOutputStream output = new ObjectOutputStream(restaurantF);
        for(Restaurant r : restaurantList){
            output.writeObject(r);
        }
        output.close();
    }

    public void readUsers() throws ClassNotFoundException, IOException{
        ObjectInputStream input;
        try(FileInputStream userF = new FileInputStream(filePath+"users.dat");){
            input = new ObjectInputStream(userF);
            while(true){
                userList.add((User)input.readObject());
            }
        }catch(EOFException e){
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void writeUsers() throws IOException{
        FileOutputStream userF = new FileOutputStream(filePath+"users.dat");
        ObjectOutputStream output = new ObjectOutputStream(userF);
        for(User u : userList){
            output.writeObject(u);
        }
        output.close();
    }

    public boolean addRestaurant(Restaurant newRestaurant){
        if(restaurantList.contains(newRestaurant))
            return false;
        else{
            restaurantList.add(newRestaurant);
            return true;
        }
    }

    public int findRestaurant(String name){
        for(int i = 0; i < restaurantList.size(); i++){
            if(restaurantList.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public boolean replaceRestaurant(String previousName, Restaurant r){
        int index = findRestaurant(previousName);
        if(index != -1){
            restaurantList.remove(index);
            restaurantList.add(index, r);
            return true;
        }
        return false;
    }

    public boolean addFoodToRestaurant(String restaurantName, Food food){
        Restaurant restaurant;
        int index;

        index = findRestaurant(restaurantName);
        if(index != -1){
            restaurant = restaurantList.get(index);
            System.out.println("add food invoked");
            if(restaurant.getFoodList().contains(food)){
                return false;
            }
            else{
                restaurant.getFoodList().add(food);
                return true;
            }
        }
        return false;
    }

    public boolean setFoodQuantity(String restaurantName, Food food, int quantity){
        boolean result = false;
        Restaurant restaurant;
        int index;

        index = findRestaurant(restaurantName);
        System.out.println("set quantity invoked");
        if(index != -1 && quantity > 0){
            System.out.println("1");
            restaurant = restaurantList.get(index);
            System.out.println("2");
            if(restaurant.getFoodQuantity().containsKey(food)){
                System.out.println("3");
                restaurant.getFoodQuantity().replace(food, quantity);
                System.out.println("4");
                result = true;
            }
            else{
                restaurant.getFoodQuantity().put(food, quantity);
                System.out.println("5");
                result = true;
            }
        }
        return result;
    }

    public boolean addUser(User user){
        if(userList.contains(user)){
            return false;
        }
        else{
            userList.add(user);
            return true;
        }
    }

    public int findUser(String name){
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getUsername().equals(name))
                return i;
        }
        return -1;
    }

    public boolean checkUserPass(String username, String password){
        boolean answer = false;
        int index;
         
        index = findUser(username);
        if(index != -1){
            if(userList.get(index).getPassword().equals(password))
                answer = true;
        }
        return answer;
    }

    public boolean replaceUser(String previousName, User u){
        int index = findUser(previousName);
        if(index != -1){
            userList.remove(index);
            userList.add(index, u);
            return true;
        }
        return false;
    }

    public boolean replaceFood(String restaurantName, String previousName, Food food, int quantity) {
        int resIndex, foodIndex;
        Restaurant restaurant;
        boolean result = false;

        resIndex = findRestaurant(restaurantName);
        restaurant = restaurantList.get(resIndex);
        foodIndex = findFood(previousName, restaurant);
        if(foodIndex != -1) {
            restaurant.getFoodList().remove(foodIndex);
            restaurant.getFoodList().add(foodIndex, food);
            for(Food f : restaurant.getFoodQuantity().keySet()) {
                if(f.getFoodName().equals(previousName)) {
                    restaurant.getFoodQuantity().remove(f);
                    restaurant.getFoodQuantity().put(food, quantity);
                }
            }
            result = true;
        }
        return result;
    }

    private int findFood(String food, Restaurant restaurant) {
        for(int i = 0; i < restaurant.getFoodList().size(); i++) {
            if(restaurant.getFoodList().get(i).getFoodName().equals(food)) {
                return i;
            }
        }
        return -1;
    }
}
