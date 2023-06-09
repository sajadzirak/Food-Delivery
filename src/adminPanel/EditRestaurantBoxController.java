package adminPanel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import server.src.DataBase;
import server.src.Restaurant;
import server.src.Restaurant.restaurantType;

public class EditRestaurantBoxController extends RestaurantDetailsBoxController implements Initializable{

    private Restaurant restaurant;
    private String previousName;

    @FXML
    void confirmButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        boolean checkAnswer, respond;
        String request = "Edit Restaurant";
        checkAnswer = checkItems();
        if(checkAnswer){
            Restaurant newRestaurant;
            File f = new File("file:"+selectedFile.getAbsolutePath());
            newRestaurant = new Restaurant(restaurantNameTextField.getText(), addressTextField.getText(), 
            restaurantType.valueOf(typeChoiceBox.getValue()), outdoorRadioButton.isSelected(), 
            f.toURI().toString(), outdoorRadioButton.isSelected()?0:Integer.parseInt(chairNumberTextField.getText()), 
            outdoorRadioButton.isSelected()?Integer.parseInt(deliveryNumberTextField.getText()):0);
            adminClient.toServer.writeObject(request);
            adminClient.toServer.writeObject(previousName);
            adminClient.toServer.writeObject(newRestaurant);
            respond = (boolean)adminClient.fromServer.readObject();
            if(respond){
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Editing restaurant");
                alert.setHeaderText(null);
                alert.setContentText("Restaurant edited succesfully!");
                alert.showAndWait();
                RestaurantTile.editBox.close();
    
            }
            else{
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Editing restaurant");
                alert.setHeaderText(null);
                alert.setContentText("something went wrong!\nmaybe the restaurant already exists");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            typeChoiceBox.setItems(types);
            restaurant = (Restaurant)adminClient.fromServer.readObject();
            previousName = restaurant.getName();
            restaurantNameTextField.setText(restaurant.getName());
            typeChoiceBox.getSelectionModel().select(restaurant.getrestaurantType().name());
            if(restaurant.isOutdoor()){
                deliveryNumberTextField.setDisable(false);
                chairNumberTextField.setDisable(true);
                deliveryNumberTextField.setText(String.valueOf(restaurant.getDeliveryNumber()));
            }
            else{
                deliveryNumberTextField.setDisable(true);
                chairNumberTextField.setText(String.valueOf(restaurant.getChairNumber()));
            }
            addressTextField.setText(restaurant.getRestaurantAddress());
            // File file = new File(restaurant.getRestaurantImagePath());
            selectedFile = new File(restaurant.getRestaurantImagePath());
            selectedImageView.setImage(new Image(DataBase.imageAbsolutePath+selectedFile.getName()));
            selectedImageLabel.setVisible(false);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
