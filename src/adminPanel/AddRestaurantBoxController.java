package adminPanel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AddRestaurantBoxController implements Initializable{
    
    public TextField restaurantNameTextField, chairNumberTextField, deliveryNumberTextField;
    public RadioButton outdoorRadioButton;
    public ChoiceBox<String> typeChoiceBox;
    ObservableList<String> types = FXCollections.observableArrayList("fastfood", "Iranian", "Chinese", "Italian");

    public void outdoorClicked(){
        if(outdoorRadioButton.isSelected()){
            chairNumberTextField.setText("");
            chairNumberTextField.setDisable(true);
            deliveryNumberTextField.setDisable(false);
        }
        else{
            deliveryNumberTextField.setText("");
            deliveryNumberTextField.setDisable(true);
            chairNumberTextField.setDisable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeChoiceBox.setItems(types);
    }


}
