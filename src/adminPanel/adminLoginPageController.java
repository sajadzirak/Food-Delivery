package adminPanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class adminLoginPageController {

    public PasswordField passwordField;
    public Label reportLabel;
    
    public void loginButtonClicked() throws UnknownHostException, IOException, ClassNotFoundException{
        String password = passwordField.getText();
        String request = "Login Admin ";
        String respond;

        if(password.length() != 0){
            System.out.println(password);
            Socket socket = new Socket("127.0.0.1", 8000);
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
            request += password;
            toServer.writeObject(request);
            toServer.flush();
            respond = (String) fromServer.readObject();
            if(respond.equals("true")){
                reportLabel.setText("Logged in");
            }
            else{
                reportLabel.setText("Wrong password!!");
            }
            socket.close();
        }    
    }
}
