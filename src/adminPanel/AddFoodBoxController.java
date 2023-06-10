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
import server.src.Food;
import server.src.Food.foodType;

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
            adminClient.toServer.writeObject(request);
            adminClient.toServer.writeObject(RestaurantFoodManagementPageController.restaurant.getName());
            adminClient.toServer.writeObject(food);
            adminClient.toServer.writeObject(Integer.parseInt(quantityField.getText()));
            respond = (Boolean) adminClient.fromServer.readObject();
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
    }

}
