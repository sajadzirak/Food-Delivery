package server.src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.image.Image;
import server.src.Restaurant.restaurantType;

public class Server{

    private static DataBase db;
    private static String request;
    private static ArrayList<String> requestList = new ArrayList<String>();
    private static ServerSocket serverSocket;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private static Socket socket;

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        db = new DataBase();
        serverSocket = new ServerSocket(8000);
        do{
            socket = serverSocket.accept();
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            request = (String) input.readObject(); 
            requestList = seperateDetails(request);
            System.out.println(requestList);
            callAppropriateMethod(requestList, output);
        }while(!request.equals("exit"));
        
    }

    private static ArrayList<String> seperateDetails(String str){
        Scanner scanner = new Scanner(str);
        ArrayList<String> list = new ArrayList<>();
        while(scanner.hasNext()){
            list.add(scanner.next());
        }
        scanner.close();
        return list;
    }

    private static void callAppropriateMethod(ArrayList<String> requestList, ObjectOutputStream output) throws IOException{
        if(requestList.get(0).equals("Login")){
            if(requestList.get(1).equals("Admin")){
                adminLogin(requestList.get(2), output);
            }
        }
        if(requestList.get(0).equals("New")){
            if(requestList.get(1).equals("Restaurant")){
                newRestaurant(requestList.subList(2, requestList.size()-1), output);
            }
        }
    }

    private static void adminLogin(String password, ObjectOutputStream toClient) throws IOException{
        String respond;
        if(password.equals(DataBase.getPassword())){
            respond = "true";
        }
        else{
            respond = "false";
        }
        toClient.writeObject(respond);
    }

    private static void newRestaurant(List<String> parametersList, ObjectOutputStream toClient) throws IOException{
        System.out.println("before");
        Restaurant nr = new Restaurant(parametersList.get(0), parametersList.get(1), 
        restaurantType.valueOf(parametersList.get(2)), Boolean.parseBoolean(parametersList.get(3)), new Image(parametersList.get(4)),
        Integer.parseInt(parametersList.get(5)), Integer.parseInt(parametersList.get(6)));
        System.out.println("object created");
        boolean result = db.addRestaurant(nr);
        System.out.println("result : "+result);
        toClient.writeObject(result);
    }
}
