import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server{
    
    static String stringRespond = "";

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        String request;
        ArrayList<String> requestList = new ArrayList<String>();
        ServerSocket serverSocket = new ServerSocket(8000);
        ObjectOutputStream output;
        ObjectInputStream input;
        Socket socket;
        
        do{
            socket = serverSocket.accept();
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            request = (String) input.readObject();    
            requestList = seperateDetails(request);
            // System.out.println(requestList);
            callAppropriateMethod(requestList);
            output.writeObject(stringRespond);
            stringRespond = "";
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
            if(requestList.get(1).equals("Admin")){
                adminLogin(requestList.get(2));
            }
        }
    }

    private static void adminLogin(String password){
        if(password.equals(DataBase.getPassword())){
            stringRespond = "true";
        }
        else{
            stringRespond = "false";
        }
    }

}
