package server.src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Application;
import javafx.stage.Stage;


public class Server extends Application{

    public static final int PORT = 8000;
    public static String IP = "127.0.0.1";
    public static DataBase db;
    private static String request;
    private static ServerSocket serverSocket;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private static Socket socket;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException{
        db = new DataBase();
        System.out.println(db.getRestaurantList());
        serverSocket = new ServerSocket(Server.PORT);
        socket = serverSocket.accept();
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        do{
            request = (String) input.readObject(); 
            new RequestHandler(request, output, input);
        }while(!request.equals("exit"));
        System.out.println("out");
        db.writeRestaurants();
        db.writeUsers();
    }
}
