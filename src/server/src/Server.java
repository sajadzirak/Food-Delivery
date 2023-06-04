package server.src;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import server.src.Restaurant.restaurantType;

public class Server extends Application{

    public static final int PORT = 8000;
    public static String IP = "127.0.0.1";
    public static DataBase db;
    private static String request;
    private static ArrayList<String> requestList = new ArrayList<String>();
    private static ServerSocket serverSocket;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private static Socket socket;
    private ListView<String> listView;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException{
        db = new DataBase();
        System.out.println(db.getRestaurantList());
        serverSocket = new ServerSocket(8000);
        do{
            socket = serverSocket.accept();
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            request = (String) input.readObject(); 
            new RequestHandler(request, output, input);
            // requestList = seperateDetails(request);
            // System.out.println(requestList);
            // callAppropriateMethod(requestList, input, output);
        }while(!request.equals("exit"));
        System.out.println("out");
        db.writeRestaurants();
    }


    // private static ArrayList<String> seperateDetails(String str){
    //     Scanner scanner = new Scanner(str);
    //     ArrayList<String> list = new ArrayList<>();
    //     while(scanner.hasNext()){
    //         list.add(scanner.next());
    //     }
    //     scanner.close();
    //     return list;
    // }

    // private static void callAppropriateMethod(ArrayList<String> requestList, ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException{
    //     if(requestList.get(0).equals("Login")){
    //         if(requestList.get(1).equals("Admin")){
    //             adminLogin(input, output);
    //         }
    //     }
    //     else if(requestList.get(0).equals("New")){
    //         if(requestList.get(1).equals("Restaurant")){
    //             newRestaurant(requestList.subList(2, requestList.size()), output);
    //         }
    //     }
    //     else if(requestList.get(0).equals("Get")){
    //         if(requestList.get(1).equals("Restaurants")){
    //             sendRestaurantsList(input, output);
    //         }
    //     }
    // }

    // private static void adminLogin(ObjectInputStream fromClient, ObjectOutputStream toClient) throws IOException, ClassNotFoundException{
    //     String respond;
    //     String password = (String) fromClient.readObject();
    //     if(password.equals(DataBase.getPassword())){
    //         respond = "true";
    //     }
    //     else{
    //         respond = "false";
    //     }
    //     toClient.writeObject(respond);
    // }

    private static void newRestaurant(List<String> parametersList, ObjectOutputStream toClient) throws IOException{
        File file = new File(parametersList.get(4));
        // SerializableImage image = new SerializableImage(file.toURI().toString());
        Restaurant nr = new Restaurant(parametersList.get(0), parametersList.get(1), 
        restaurantType.valueOf(parametersList.get(2)), Boolean.parseBoolean(parametersList.get(3)), file.toURI().toString(),
        Integer.parseInt(parametersList.get(5)), Integer.parseInt(parametersList.get(6)));
        boolean result = db.addRestaurant(nr);
        toClient.writeObject(result);
    }

    private static void sendRestaurantsList(ObjectInputStream fromServer, ObjectOutputStream toServer) throws IOException{
        toServer.writeObject(db.getRestaurantList());
        System.out.println("after sending object");
    }
}
