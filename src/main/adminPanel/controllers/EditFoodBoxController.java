package main.adminPanel.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import main.adminPanel.AdminClient;
import main.adminPanel.others.FoodDetailsBox;
import main.classes.Food;
import main.classes.methods;
import main.classes.Food.foodType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EditFoodBoxController extends FoodDetailsBox implements Initializable{

    private Food food;
    private String previousName, restaurantName;
    private int quantity;
    private Alert alert;

    @FXML
    void confirmButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        boolean checkEmptyFields, respond;
        String request = "Edit Food";
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
            Food newFood;
            newFood = new Food(foodNameTextField.getText(), Double.parseDouble(weightField.getText()), 
            Double.parseDouble(priceField.getText()), foodType.valueOf(typeChoiceBox.getValue()),
            selectedFile==null?food.getFoodImagePath():new File(selectedFile.getAbsolutePath()).toURI().toString());
            AdminClient.toServer.writeObject(request);
            AdminClient.toServer.writeObject(restaurantName);
            AdminClient.toServer.writeObject(previousName);
            AdminClient.toServer.writeObject(newFood);
            AdminClient.toServer.writeObject(Integer.parseInt(quantityField.getText()));
            respond = (boolean) AdminClient.fromServer.readObject();
            if(respond) {
                alert.setAlertType(AlertType.INFORMATION);
                alert.setContentText("Food edited successfuly!");
                alert.showAndWait();
                Stage window = (Stage) confirmButton.getScene().getWindow();
                window.close();
            }      
            else {
                alert.setAlertType(AlertType.ERROR);
                alert.setContentText("something went wrong!!");
                alert.showAndWait();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        methods.addListenerToNumericField(quantityField);
        methods.addListenerToDoublyFields(priceField);
        methods.addListenerToDoublyFields(weightField);

        try{
            typeChoiceBox.setItems(types);
            restaurantName = (String) AdminClient.fromServer.readObject();
            food = (Food) AdminClient.fromServer.readObject();
            quantity = (Integer) AdminClient.fromServer.readObject();
            previousName = food.getFoodName();
            foodNameTextField.setText(food.getFoodName());
            typeChoiceBox.getSelectionModel().select(food.getFoodType().name());
            weightField.setText(food.getFoodWeight()+"");
            priceField.setText(food.getFoodPrice()+"");
            quantityField.setText(quantity+"");
            selectedImageView.setImage(new Image(food.getFoodImagePath()));
            selectedImageLabel.setVisible(false);
            alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Editing Food");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

}
