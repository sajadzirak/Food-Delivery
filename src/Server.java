import java.util.ArrayList;
import java.util.Scanner;

public class Server{
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Scanner commandScanner;
        String command;
        ArrayList<String> commandList = new ArrayList<>();
        do{
            command = input.nextLine();
            commandList = seperateDetails(command);
            System.out.println(commandList);
            callAppropriateMethod(commandList);
        }while(!command.equals("exit"));
        
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

    private static void callAppropriateMethod(ArrayList<String> commandList){
        if(commandList.get(0).equals("Login")){
            if(commandList.get(1).equals("admin")){
                adminLogin(commandList.get(2));
            }
        }
    }

    private static void adminLogin(String password){

    }

}
