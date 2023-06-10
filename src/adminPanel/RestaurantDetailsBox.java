package adminPanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class RestaurantDetailsBox {

    @FXML
    protected TextField addressTextField;

    @FXML
    protected Label chairNumberLabel;

    @FXML
    protected TextField chairNumberTextField;

    @FXML
    protected Button confirmButton;

    @FXML
    protected Label deliveryNumberLabel;

    @FXML
    protected TextField deliveryNumberTextField;

    @FXML
    protected AnchorPane mainAnchor;

    @FXML
    protected GridPane mainGrid;

    @FXML
    protected RadioButton outdoorRadioButton;

    @FXML
    protected Label reportLabel;

    @FXML
    protected TextField restaurantNameTextField;

    @FXML
    protected Button selectImageButton;

    @FXML
    protected Label selectedImageLabel;

    @FXML
    protected ImageView selectedImageView;

    @FXML
    protected ChoiceBox<String> typeChoiceBox;

    protected Alert alert;
    protected ObservableList<String> types = FXCollections.observableArrayList("fastfood", "Iranian", "Chinese", "Italian");
    public File selectedFile;

    @FXML
    private void outdoorClicked(ActionEvent event) {
        if(outdoorRadioButton.isSelected()){
            chairNumberTextField.setText("");
            chairNumberTextField.setDisable(true);
            chairNumberLabel.setStyle("-fx-text-fill: #04030f;");
            chairNumberLabel.setText("chair number:");
            deliveryNumberTextField.setDisable(false);
        }
        else{
            deliveryNumberTextField.setText("");
            deliveryNumberTextField.setDisable(true);
            deliveryNumberLabel.setStyle("-fx-text-fill: #04030f;");
            deliveryNumberLabel.setText("delivery number:");
            chairNumberTextField.setDisable(false);
        }
    }
    
    @FXML
    private void selectImageButtonClicked() throws FileNotFoundException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
        fileChooser.setInitialDirectory(new File("/~"));
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("jpg", "*.jpg"),
        new ExtensionFilter("png", "*.png"), new ExtensionFilter("jpeg", "*.jpeg"));
        selectedFile = fileChooser.showOpenDialog(null);
        InputStream inputStream = new FileInputStream(selectedFile.getAbsolutePath());
        selectedImageLabel.setVisible(false);
        selectedImageView.setImage(new Image(inputStream));
    }

    protected boolean checkItems(){
        boolean status = true;
        // turn back normal text for labels if their values are valid
        if(!outdoorRadioButton.isSelected() && checkNumberFields(chairNumberTextField)){
            chairNumberLabel.setStyle("-fx-text-fill: #04030f");
            chairNumberLabel.setText("chair number:");
        }
        if(outdoorRadioButton.isSelected() && checkNumberFields(deliveryNumberTextField)){
            deliveryNumberLabel.setStyle("-fx-text-fill: #04030f");
            deliveryNumberLabel.setText("delivery number:");
        }
        // restaurant name field should fill
        if(restaurantNameTextField.getText().length() == 0){
            status = false;
            reportLabel.setText("enter a name!");
        }
        // you should have a type
        else if(typeChoiceBox.getValue() == null){
            status = false;
            reportLabel.setText("select a type!");
        }
        // should enter a chair number
        else if(!outdoorRadioButton.isSelected() && !checkNumberFields(chairNumberTextField)){
            status = false;
            reportLabel.setText("Enter a positive number!");
            chairNumberLabel.setStyle("-fx-text-fill: rgb(255, 21, 21)");
            chairNumberLabel.setText("*chair number:");
        }
        // should enter a delivery number
        else if(outdoorRadioButton.isSelected() && !checkNumberFields(deliveryNumberTextField)){
            status = false;
            reportLabel.setText("Enter a positive number!");
            deliveryNumberLabel.setStyle("-fx-text-fill: rgb(255, 21, 21)");
            deliveryNumberLabel.setText("*delivery number:");
        }
        // should enter an address
        else if(addressTextField.getText().length() == 0){
            status = false;
            reportLabel.setText("Enter an address!");
        }
        // should select an image
        else if(selectedImageView.getImage() == null){
            status = false;
            reportLabel.setText("select an image!");
        }
        return status;
    }

    // check if a numeric textfield have filled properly
    private boolean checkNumberFields(TextField tf){
        boolean status = true;
        String numberStr = tf.getText();
        if(numberStr.length() == 0){
               status = false;
        }
        else{
            try{
                int number = Integer.parseInt(numberStr);
                if(number < 0)
                    throw new NumberFormatException();
            } catch(NumberFormatException nfe){
                status = false;
            }
        }
        return status;
    }
}
