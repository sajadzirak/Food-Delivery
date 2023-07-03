package main.adminPanel.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import main.adminPanel.AdminClient;
import main.adminPanel.others.RestaurantDetailsBox;
import main.classes.Restaurant;
import main.classes.methods;
import main.classes.Restaurant.restaurantType;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        boolean checkEmptyFields, respond;
        String request = "Edit Restaurant";
        checkEmptyFields = methods.checkForEmptyTextField(restaurantNameTextField, 
        outdoorRadioButton.isSelected()?deliveryNumberTextField:chairNumberTextField, addressTextField);
        if(!checkEmptyFields) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Please fill all the fields!!");
            alert.showAndWait();
        }
        else if(typeChoiceBox.getValue() == null) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Please select a type!!");
            alert.showAndWait();
        }
        else if(selectedImageView.getImage() == null) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Please select an image!!");
            alert.showAndWait();
        }
        else {
            Restaurant newRestaurant;
            newRestaurant = new Restaurant(restaurantNameTextField.getText(), addressTextField.getText(), 
            restaurantType.valueOf(typeChoiceBox.getValue()), restaurant.getFoodList(), restaurant.getFoodQuantity(), outdoorRadioButton.isSelected(), 
            selectedFile==null?restaurant.getRestaurantImagePath():new File(selectedFile.getAbsolutePath()).toURI().toString(), outdoorRadioButton.isSelected()?0:Integer.parseInt(chairNumberTextField.getText()), 
            outdoorRadioButton.isSelected()?Integer.parseInt(deliveryNumberTextField.getText()):0);
            AdminClient.toServer.writeObject(request);
            AdminClient.toServer.writeObject(previousName);
            AdminClient.toServer.writeObject(newRestaurant);
            respond = (boolean)AdminClient.fromServer.readObject();
            if(respond){
                alert.setAlertType(AlertType.INFORMATION);
                alert.setContentText("Restaurant edited succesfully!");
                alert.showAndWait();
                Stage window = (Stage) confirmButton.getScene().getWindow();
                window.close();
            }
            else{
                alert.setAlertType(AlertType.ERROR);
                alert.setContentText("something went wrong!!");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Editing restaurant");
        chairNumberTextField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
            if (!newValue.matches("\\d*")) {
                chairNumberTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        deliveryNumberTextField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
            if (!newValue.matches("\\d*")) {
                deliveryNumberTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });

        try{
            typeChoiceBox.setItems(types);
            restaurant = (Restaurant)AdminClient.fromServer.readObject();
            previousName = restaurant.getName();
            restaurantNameTextField.setText(restaurant.getName());
            if(restaurant.isOutdoor()) {
                outdoorRadioButton.setSelected(true);
            }
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
            selectedImageView.setImage(new Image(restaurant.getRestaurantImagePath()));
            selectedImageLabel.setVisible(false);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
