package main.adminPanel.others;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class RestaurantDetailsBox extends DetailsBox{

    @FXML
    protected TextField addressTextField;

    @FXML
    protected Label chairNumberLabel;

    @FXML
    protected TextField chairNumberTextField;

    @FXML
    protected Label deliveryNumberLabel;

    @FXML
    protected TextField deliveryNumberTextField;

    @FXML
    protected RadioButton outdoorRadioButton;

    @FXML
    protected TextField restaurantNameTextField;

    @FXML
    protected ChoiceBox<String> typeChoiceBox;

    protected Alert alert;
    protected ObservableList<String> types = FXCollections.observableArrayList("fastfood", "Iranian", "Chinese", "Italian");

    @FXML
    private void outdoorClicked(ActionEvent event) {
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
}
