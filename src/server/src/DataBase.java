package server.src;

import java.io.*;
import java.util.ArrayList;

public class DataBase {
    
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
        try(FileInputStream restaurantF = new FileInputStream("/home/sajad/A/java/myProjects/restaurantManagement/src/DB/files/restaurants.dat");){
            input = new ObjectInputStream(restaurantF);
            while(true){
                restaurantList.add((Restaurant)input.readObject());
            }
        }catch (Exception e){
        }
    }

    public void writeRestaurants() throws IOException{
        FileOutputStream restaurantF = new FileOutputStream("/home/sajad/A/java/myProjects/restaurantManagement/src/DB/files/restaurants.dat");
        ObjectOutputStream output = new ObjectOutputStream(restaurantF);
        for(Restaurant r : restaurantList){
            output.writeObject(r);
        }
        output.close();
    }

    public void readUsers() throws ClassNotFoundException, IOException{
        ObjectInputStream input;
        try(FileInputStream userF = new FileInputStream("/home/sajad/A/java/myProjects/restaurantManagement/src/DB/files/users.dat");){
            input = new ObjectInputStream(userF);
            while(true){
                userList.add((User)input.readObject());
            }
        }catch (Exception e){
        }
    }

    public void writeUsers() throws IOException{
        FileOutputStream userF = new FileOutputStream("/home/sajad/A/java/myProjects/restaurantManagement/src/DB/files/users.dat");
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
            System.out.println("contains true");
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
}
