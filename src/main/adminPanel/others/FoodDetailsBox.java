package main.adminPanel.others;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public abstract class FoodDetailsBox extends DetailsBox{
    
    protected ObservableList<String> types = FXCollections.observableArrayList("fastfood", "Iranian", "Chinese", "Italian");
    protected Alert alert;

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

}
