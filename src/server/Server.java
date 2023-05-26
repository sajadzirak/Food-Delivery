package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server{
    
    public static void main(String[] args) throws IOException{
        String request;
        ArrayList<String> requestList = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(8000);
        ObjectOutputStream output;
        ObjectInputStream input;
        Socket socket;

        do{
            socket = serverSocket.accept();
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            request = input.readUTF();    
            System.out.println(request);
            // requestList = seperateDetails(request);
            // System.out.println(requestList);
            // callAppropriateMethod(requestList);
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

    private static void callAppropriateMethod(ArrayList<String> requestList){
        if(requestList.get(0).equals("Login")){
            if(requestList.get(1).equals("admin")){
                adminLogin(requestList.get(2));
            }
        }
    }

    private static void adminLogin(String password){

    }

}
