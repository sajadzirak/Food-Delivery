package adminPanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class AddRestaurantBoxController implements Initializable{
    
    private Alert alert;
    public File selectedFile;
    public ImageView selectedImageView;
    public Label selectedImageLabel, reportLabel, chairNumberLabel, deliveryNumberLabel;
    public GridPane mainGrid;
    public TextField restaurantNameTextField, chairNumberTextField, deliveryNumberTextField, addressTextField;
    public RadioButton outdoorRadioButton;
    public ChoiceBox<String> typeChoiceBox;
    ObservableList<String> types = FXCollections.observableArrayList("fastfood", "Iranian", "Chinese", "Italian");

    public void outdoorClicked(){
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

    public void selectImageButtonClicked() throws FileNotFoundException{
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

    public void confirmButtonClicked() throws UnknownHostException, IOException, ClassNotFoundException{
        boolean checkAnswer, respond;
        String request;
        checkAnswer = checkItems();
        if(checkAnswer){
            Socket socket = new Socket("127.0.0.1", 8000);
            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            request = "New Restaurant";
            toServer.writeObject(request);
            toServer.writeObject(restaurantNameTextField.getText());
            // System.out.println("1");
            toServer.writeObject(addressTextField.getText());
            // System.out.println("2");
            toServer.writeObject(typeChoiceBox.getValue());
            // System.out.println("3");
            toServer.writeObject(outdoorRadioButton.isSelected());
            // System.out.println("4");
            toServer.writeObject("file:"+selectedFile.getAbsolutePath());
            // System.out.println("5");
            if(!outdoorRadioButton.isSelected()){
                toServer.writeObject(Integer.parseInt(chairNumberTextField.getText()));
                toServer.writeObject(0);
            }
            else{
                toServer.writeObject(0);
                toServer.writeObject(Integer.parseInt(deliveryNumberTextField.getText()));
            }
            respond = (boolean)fromServer.readObject();
            if(respond){
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Adding restaurant");
                alert.setHeaderText(null);
                alert.setContentText("Restaurant added succesfully!");
                alert.showAndWait();
                adminRestaurantManagementPageController.addBox.close();

            }
            else{
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Adding restaurant");
                alert.setHeaderText(null);
                alert.setContentText("something went wrong!\nmaybe the restaurant already exists");
                alert.showAndWait();
            }
            socket.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeChoiceBox.setItems(types);
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


