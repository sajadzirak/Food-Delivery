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
    private static DataBase db;
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
        // StackPane layout = new StackPane();
        // listView = new ListView<>();
        // layout.getChildren().add(listView);
        // Scene scene = new Scene(layout, 420, 420);
        // primaryStage.setScene(scene);
        // primaryStage.show();
        db = new DataBase();
        System.out.println(db.getRestaurantList());
        serverSocket = new ServerSocket(8000);
        do{
            socket = serverSocket.accept();
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            request = (String) input.readObject(); 
            requestList = seperateDetails(request);
            System.out.println(requestList);
            // listView.getItems().add(request);
            callAppropriateMethod(requestList, output);
        }while(!request.equals("exit"));
        System.out.println("out");
        db.writeRestaurants();
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
                newRestaurant(requestList.subList(2, requestList.size()), output);
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
        File file = new File(parametersList.get(4));
        // SerializableImage image = new SerializableImage(file.toURI().toString());
        Restaurant nr = new Restaurant(parametersList.get(0), parametersList.get(1), 
        restaurantType.valueOf(parametersList.get(2)), Boolean.parseBoolean(parametersList.get(3)), file.toURI().toString(),
        Integer.parseInt(parametersList.get(5)), Integer.parseInt(parametersList.get(6)));
        boolean result = db.addRestaurant(nr);
        toClient.writeObject(result);
    }
}
