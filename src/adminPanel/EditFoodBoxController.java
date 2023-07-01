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
import javafx.stage.Stage;
import server.src.DataBase;
import server.src.Food;
import server.src.Food.foodType;

public class EditFoodBoxController extends FoodDetailsBox implements Initializable{

    private Food food;
    private String previousName, restaurantName;
    private int quantity;
    private Alert alert;

    @FXML
    void confirmButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        boolean checkAnswer, respond;
        String request = "Edit Food";
        checkAnswer = checkItems();
        if(checkAnswer) {
            Food newFood;
            File f = new File("file:"+selectedFile.getAbsolutePath());
            newFood = new Food(foodNameTextField.getText(), Double.parseDouble(weightField.getText()), 
            Double.parseDouble(priceField.getText()), foodType.valueOf(typeChoiceBox.getValue()), f.toURI().toString());
            adminClient.toServer.writeObject(request);
            adminClient.toServer.writeObject(restaurantName);
            adminClient.toServer.writeObject(previousName);
            adminClient.toServer.writeObject(newFood);
            adminClient.toServer.writeObject(Integer.parseInt(quantityField.getText()));
            respond = (boolean) adminClient.fromServer.readObject();
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
        try{
            typeChoiceBox.setItems(types);
            restaurantName = (String) adminClient.fromServer.readObject();
            food = (Food) adminClient.fromServer.readObject();
            quantity = (Integer) adminClient.fromServer.readObject();
            previousName = food.getFoodName();
            foodNameTextField.setText(food.getFoodName());
            typeChoiceBox.getSelectionModel().select(food.getFoodType().name());
            weightField.setText(food.getFoodWeight()+"");
            priceField.setText(food.getFoodPrice()+"");
            quantityField.setText(quantity+"");
            selectedFile = new File(food.getFoodImagePath());
            selectedImageView.setImage(new Image(DataBase.imageAbsolutePath+selectedFile.getName()));
            selectedImageLabel.setVisible(false);
            alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Editing Food");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

}
