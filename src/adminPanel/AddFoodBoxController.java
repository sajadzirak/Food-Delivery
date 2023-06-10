package adminPanel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import server.src.Food;
import server.src.Food.foodType;

public class AddFoodBoxController extends FoodDetailsBox implements Initializable{

    @FXML
    void confirmButtonClicked(ActionEvent event) throws IOException {
        String request = "New Food";
        Food food;
        boolean checkAnswer;
        checkAnswer = checkItems();
        if(checkAnswer){
            File f = new File("file:"+selectedFile.getAbsolutePath());
            food = new Food(foodNameTextField.getText(), Double.parseDouble(weightField.getText()),
            Double.parseDouble(weightField.getText()), foodType.valueOf(typeChoiceBox.getValue()),
            f.toURI().toString());
            adminClient.toServer.writeObject(request);
            adminClient.toServer.writeObject(RestaurantFoodManagementPageController.restaurant.getName());
            adminClient.toServer.writeObject(food);
            adminClient.toServer.writeObject(Integer.parseInt(quantityField.getText()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeChoiceBox.setItems(types);
    }

}
