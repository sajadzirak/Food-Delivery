package adminPanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class FoodDetailsBox {
    
    protected ObservableList<String> types = FXCollections.observableArrayList("fastfood", "Iranian", "Chinese", "Italian");
    private Alert alert;
    protected File selectedFile;

    @FXML
    protected Button confirmButton;

    @FXML
    protected TextField foodNameTextField;

    @FXML
    protected AnchorPane mainAnchor;

    @FXML
    protected GridPane mainGrid;

    @FXML
    protected TextField priceField;

    @FXML
    protected Label priceLabel;

    @FXML
    protected TextField quantityField;

    @FXML
    protected Label quantityLabel;

    @FXML
    protected Label reportLabel;

    @FXML
    protected Button selectImageButton;

    @FXML
    protected Label selectedImageLabel;

    @FXML
    protected ImageView selectedImageView;

    @FXML
    protected ChoiceBox<String> typeChoiceBox;

    @FXML
    protected TextField weightField;

    @FXML
    protected Label weightLabel;

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
        String errMessage;
        alert = new  Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error Message");

        if(foodNameTextField.getText().length() == 0){
            status = false;
            errMessage = "Please enter a name!";
        }
        else if(typeChoiceBox.getValue() == null){
            status = false;
            errMessage = "Please choose a type!";
        }
        else if(!checkNumberFields(weightField)){
            status = false;
            errMessage = "Please enter a valid weight!";
        }
        else if(!checkNumberFields(priceField)){
            status = false;
            errMessage = "Please enter a valid price!";
        }
        else if(!checkNumberFields(quantityField)){
            status = false;
            errMessage = "Please enter a valid quantity!";
        }
        else if(selectedImageView.getImage() == null){
            status = false;
            errMessage = "Please select an image!";
        }
        return status;
    }

    private boolean checkNumberFields(TextField tf){
        boolean status = true;
        String numberStr = tf.getText();
        if(numberStr.length() == 0){
               status = false;
        }
        else{
            try{
                double number = Double.parseDouble(numberStr);
                if(number < 0)
                    throw new NumberFormatException();
            } catch(NumberFormatException nfe){
                status = false;
            }
        }
        return status;
    }
}
