package main.adminPanel.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.adminPanel.AdminClient;
import main.adminPanel.others.FoodDetailsBox;
import main.classes.Food;
import main.classes.methods;
import main.classes.Food.foodType;

public class AddFoodBoxController extends FoodDetailsBox implements Initializable{

    @FXML
    void confirmButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        String request = "New Food";
        Food food;
        boolean respond, checkEmptyFields;
        checkEmptyFields = methods.checkForEmptyTextField(foodNameTextField, weightField,
        priceField, quantityField);

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
            File f = new File(selectedFile.getAbsolutePath());
            food = new Food(foodNameTextField.getText(), Double.parseDouble(weightField.getText()),
            Double.parseDouble(weightField.getText()), foodType.valueOf(typeChoiceBox.getValue()),
            f.toURI().toString());
            AdminClient.toServer.writeObject(request);
            AdminClient.toServer.writeObject(RestaurantFoodManagementPageController.restaurant.getName());
            AdminClient.toServer.writeObject(food);
            AdminClient.toServer.writeObject(Integer.parseInt(quantityField.getText()));
            respond = (Boolean) AdminClient.fromServer.readObject();
            if(respond){
                alert.setAlertType(AlertType.INFORMATION);
                alert.setContentText("Food added succesfully!");
                alert.showAndWait();
                Stage window = (Stage) confirmButton.getScene().getWindow();
                window.close();
            }
            else{
                alert.setAlertType(AlertType.ERROR);
                alert.setContentText("something went wrong!\nmaybe the food already exists");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeChoiceBox.setItems(types);
        alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Adding Food");
        
        methods.addListenerToNumericField(quantityField);
        methods.addListenerToDoublyFields(priceField);
        methods.addListenerToDoublyFields(weightField);
    }

}
