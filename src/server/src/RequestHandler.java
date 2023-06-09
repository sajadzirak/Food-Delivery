package server.src;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
        // System.out.println("from sender list: "+list);
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
        // System.out.println("prev name recieved: "+previousName);
        edited = (Restaurant)fromClient.readObject();
        // System.out.println("res recieved: "+edited);
        boolean result = Server.db.replaceRestaurant(previousName, edited);
        toClient.writeObject(result);
    }


}
