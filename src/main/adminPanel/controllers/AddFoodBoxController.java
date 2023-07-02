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
import main.adminPanel.AdminClient;
import main.adminPanel.others.FoodDetailsBox;
import main.classes.Food;
import main.classes.Food.foodType;

public class AddFoodBoxController extends FoodDetailsBox implements Initializable{

    @FXML
    void confirmButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        String request = "New Food";
        Food food;
        boolean checkAnswer, respond;
        checkAnswer = checkItems();
        if(checkAnswer){
            File f = new File("file:"+selectedFile.getAbsolutePath());
            food = new Food(foodNameTextField.getText(), Double.parseDouble(weightField.getText()),
            Double.parseDouble(weightField.getText()), foodType.valueOf(typeChoiceBox.getValue()),
            f.toURI().toString());
            AdminClient.toServer.writeObject(request);
            AdminClient.toServer.writeObject(RestaurantFoodManagementPageController.restaurant.getName());
            AdminClient.toServer.writeObject(food);
            AdminClient.toServer.writeObject(Integer.parseInt(quantityField.getText()));
            respond = (Boolean) AdminClient.fromServer.readObject();
            if(respond){
                alert.setContentText("Food added succesfully!");
                alert.showAndWait();
                RestaurantFoodManagementPageController.addBoxCopy.close();
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
        alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Adding Food");
        // monthField.textProperty().addListener(new ChangeListener<String>() {
        // @Override
        // public void changed(ObservableValue<? extends String> observable, String oldValue, 
        //     String newValue) {
        //     if (!newValue.matches("\\d*")) {
        //         monthField.setText(newValue.replaceAll("[^\\d]", ""));
        //     }
        //     if(newValue.length() > 2) {
        //         monthField.setText(oldValue);
        //     }
        // }
        // });
    }

}
