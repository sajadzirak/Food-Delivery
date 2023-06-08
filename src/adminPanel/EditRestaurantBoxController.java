package adminPanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import server.src.DataBase;
import server.src.Restaurant;
import server.src.Restaurant.restaurantType;

public class EditRestaurantBoxController implements Initializable{

    private Restaurant restaurant;
    private File selectedFile;
    private Alert alert;
    private String previousName;

    @FXML
    private TextField addressTextField;

    @FXML
    private Label chairNumberLabel;

    @FXML
    private TextField chairNumberTextField;

    @FXML
    private Button confirmButton;

    @FXML
    private Label deliveryNumberLabel;

    @FXML
    private TextField deliveryNumberTextField;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private GridPane mainGrid;

    @FXML
    private RadioButton outdoorRadioButton;

    @FXML
    private Label reportLabel;

    @FXML
    private TextField restaurantNameTextField;

    @FXML
    private Button selectImageButton;

    @FXML
    private Label selectedImageLabel;

    @FXML
    private ImageView selectedImageView;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    void confirmButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        boolean checkAnswer, respond;
        String request = "Edit Restaurant";
        checkAnswer = checkItems();
        if(checkAnswer){
            Restaurant newRestaurant;
            File f = new File("file:"+selectedFile.getAbsolutePath());
            newRestaurant = new Restaurant(restaurantNameTextField.getText(), addressTextField.getText(), 
            restaurantType.valueOf(typeChoiceBox.getValue()), outdoorRadioButton.isSelected(), 
            f.toURI().toString(), outdoorRadioButton.isSelected()?0:Integer.parseInt(chairNumberTextField.getText()), 
            outdoorRadioButton.isSelected()?Integer.parseInt(deliveryNumberTextField.getText()):0);
            adminClient.toServer.writeObject(request);
            adminClient.toServer.writeObject(previousName);
            adminClient.toServer.writeObject(newRestaurant);
        }
        respond = (boolean)adminClient.fromServer.readObject();
        if(respond){
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Editing restaurant");
            alert.setHeaderText(null);
            alert.setContentText("Restaurant edited succesfully!");
            alert.showAndWait();
            adminRestaurantManagementPageController.addBox.close();

        }
        else{
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Editing restaurant");
            alert.setHeaderText(null);
            alert.setContentText("something went wrong!\nmaybe the restaurant already exists");
            alert.showAndWait();
        }
    }

    @FXML
    void outdoorClicked(ActionEvent event) {
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
    void selectImageButtonClicked(ActionEvent event) throws FileNotFoundException {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            restaurant = (Restaurant)adminClient.fromServer.readObject();
            previousName = restaurant.getName();
            restaurantNameTextField.setText(restaurant.getName());
            typeChoiceBox.getSelectionModel().select(restaurant.getrestaurantType().name());
            if(restaurant.isOutdoor()){
                deliveryNumberTextField.setDisable(false);
                chairNumberTextField.setDisable(true);
                deliveryNumberTextField.setText(String.valueOf(restaurant.getDeliveryNumber()));
            }
            else{
                deliveryNumberTextField.setDisable(true);
                chairNumberTextField.setText(String.valueOf(restaurant.getChairNumber()));
            }
            addressTextField.setText(restaurant.getRestaurantAddress());
            // File file = new File(restaurant.getRestaurantImagePath());
            selectedFile = new File(restaurant.getRestaurantImagePath());
            selectedImageView.setImage(new Image(DataBase.imageAbsolutePath+selectedFile.getName()));
            selectedImageLabel.setVisible(false);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    private boolean checkItems(){
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
