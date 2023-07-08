package main.adminPanel.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.adminPanel.AdminClient;
import main.adminPanel.others.RestaurantDetailsBox;
import main.classes.Restaurant;
import main.classes.methods;
import main.classes.Restaurant.restaurantType;

public class AddRestaurantBoxController extends RestaurantDetailsBox implements Initializable{

    public void confirmButtonClicked() throws UnknownHostException, IOException, ClassNotFoundException{
        boolean respond, checkEmptyFields;
        String request;
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
            request = "New Restaurant";
            Restaurant newRestaurant;
            File f = new File(selectedFile.getAbsolutePath());
            newRestaurant = new Restaurant(restaurantNameTextField.getText(), addressTextField.getText(), 
            restaurantType.valueOf(typeChoiceBox.getValue()), outdoorRadioButton.isSelected(), 
            f.toURI().toString(), outdoorRadioButton.isSelected()?0:Integer.parseInt(chairNumberTextField.getText()), 
            outdoorRadioButton.isSelected()?Integer.parseInt(deliveryNumberTextField.getText()):0);
            AdminClient.toServer.writeObject(request);
            AdminClient.toServer.writeObject(newRestaurant);
            respond = (boolean)AdminClient.fromServer.readObject();
            if(respond){
                alert.setAlertType(AlertType.INFORMATION);
                alert.setContentText("Restaurant added succesfully!");
                alert.showAndWait();
                Stage window = (Stage) confirmButton.getScene().getWindow();
                window.close();
            }
            else{
                alert.setAlertType(AlertType.ERROR);
                alert.setContentText("something went wrong!\nmaybe the restaurant already exists");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeChoiceBox.setItems(types);
        alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Adding restaurant");

        methods.addListenerToNumericField(chairNumberTextField);
        methods.addListenerToNumericField(deliveryNumberTextField);
    }
}


