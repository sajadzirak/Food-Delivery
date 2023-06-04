package server.src;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.naming.spi.DirStateFactory.Result;

import server.src.Restaurant.restaurantType;

public class RequestHandler {
    
    private String request;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public RequestHandler(String request, ObjectOutputStream output, ObjectInputStream input) throws ClassNotFoundException, IOException{
        this.request = request;
        this.output = output;
        this.input = input;
        determineRequest(input);
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

    public void determineRequest(ObjectInputStream input) throws ClassNotFoundException, IOException{
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
        toClient.writeObject(Server.db.getRestaurantList());
    }

    private void newRestaurant(ObjectInputStream fromClient, ObjectOutputStream toClient) throws IOException, ClassNotFoundException{
        int chairNumber, deliveryNumber;
        String name, address, imagePath;
        restaurantType type;
        boolean outdoor;

        name = (String)fromClient.readObject();
        // System.out.println("1");
        address = (String) fromClient.readObject();
        // System.out.println("2");
        type = restaurantType.valueOf((String) fromClient.readObject());
        // System.out.println("3");
        outdoor = (Boolean) fromClient.readObject();
        // System.out.println("4");
        imagePath = (String) fromClient.readObject();
        // System.out.println("5");
        chairNumber = (Integer) fromClient.readObject();
        // System.out.println("6");
        deliveryNumber = (Integer) fromClient.readObject();
        // System.out.println("7");
        File file = new File(imagePath);

        Restaurant nr = new Restaurant(name, address, type, outdoor, file.toURI().toString(), chairNumber, deliveryNumber);
        System.out.println("before adding");
        boolean result = Server.db.addRestaurant(nr);
        toClient.writeObject(result);
        // fromClient.readObject();
        // sendRestaurantsList(fromClient, toClient);
        System.out.println("end of new res result: "+result);
    }
    
    
    

}
