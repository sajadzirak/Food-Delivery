package adminPanel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import server.src.Restaurant;
import server.src.Restaurant.restaurantType;

public class AddRestaurantBoxController extends RestaurantDetailsBoxController implements Initializable{

    public void confirmButtonClicked() throws UnknownHostException, IOException, ClassNotFoundException{
        boolean checkAnswer, respond;
        String request;
        checkAnswer = checkItems();
        if(checkAnswer){
        //     Socket socket = new Socket("127.0.0.1", 8000);
        //     ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
        //     ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            request = "New Restaurant";
            Restaurant newRestaurant;
            File f = new File("file:"+selectedFile.getAbsolutePath());
            newRestaurant = new Restaurant(restaurantNameTextField.getText(), addressTextField.getText(), 
            restaurantType.valueOf(typeChoiceBox.getValue()), outdoorRadioButton.isSelected(), 
            f.toURI().toString(), outdoorRadioButton.isSelected()?0:Integer.parseInt(chairNumberTextField.getText()), 
            outdoorRadioButton.isSelected()?Integer.parseInt(deliveryNumberTextField.getText()):0);
            adminClient.toServer.writeObject(request);
            adminClient.toServer.writeObject(newRestaurant);
            // adminClient.toServer.writeObject(restaurantNameTextField.getText());
            // adminClient.toServer.writeObject(addressTextField.getText());
            // adminClient.toServer.writeObject(typeChoiceBox.getValue());
            // adminClient.toServer.writeObject(outdoorRadioButton.isSelected());;
            // adminClient.toServer.writeObject("file:"+selectedFile.getAbsolutePath());
            // if(!outdoorRadioButton.isSelected()){
            //     adminClient.toServer.writeObject(Integer.parseInt(chairNumberTextField.getText()));
            //     adminClient.toServer.writeObject(0);
            // }
            // else{
            //     adminClient.toServer.writeObject(0);
            //     adminClient.toServer.writeObject(Integer.parseInt(deliveryNumberTextField.getText()));
            // }
            respond = (boolean)adminClient.fromServer.readObject();
            if(respond){
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Adding restaurant");
                alert.setHeaderText(null);
                alert.setContentText("Restaurant added succesfully!");
                alert.showAndWait();
                adminRestaurantManagementPageController.addBox.close();

            }
            else{
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Adding restaurant");
                alert.setHeaderText(null);
                alert.setContentText("something went wrong!\nmaybe the restaurant already exists");
                alert.showAndWait();
            }
            // socket.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeChoiceBox.setItems(types);
    }
}


