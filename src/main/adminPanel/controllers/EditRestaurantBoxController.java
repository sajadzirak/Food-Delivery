package main.adminPanel.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import main.adminPanel.AdminClient;
import main.adminPanel.others.RestaurantDetailsBox;
import main.adminPanel.others.RestaurantTile;
import main.classes.Restaurant;
import main.classes.Restaurant.restaurantType;
import main.server.DataBase;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EditRestaurantBoxController extends RestaurantDetailsBox implements Initializable{

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
            restaurantType.valueOf(typeChoiceBox.getValue()), restaurant.getFoodList(), restaurant.getFoodQuantity(), outdoorRadioButton.isSelected(), 
            f.toURI().toString(), outdoorRadioButton.isSelected()?0:Integer.parseInt(chairNumberTextField.getText()), 
            outdoorRadioButton.isSelected()?Integer.parseInt(deliveryNumberTextField.getText()):0);
            AdminClient.toServer.writeObject(request);
            AdminClient.toServer.writeObject(previousName);
            AdminClient.toServer.writeObject(newRestaurant);
            respond = (boolean)AdminClient.fromServer.readObject();
            if(respond){
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Editing restaurant");
                alert.setHeaderText(null);
                alert.setContentText("Restaurant edited succesfully!");
                alert.showAndWait();
                Stage window = (Stage) confirmButton.getScene().getWindow();
                window.close();
    
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
            restaurant = (Restaurant)AdminClient.fromServer.readObject();
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
