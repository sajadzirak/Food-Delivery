package userPanel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PaymentPageController implements Initializable{


    @FXML
    private Button cancelButton;

    @FXML
    private TextField cardNumberField;

    @FXML
    private GridPane container;

    @FXML
    private PasswordField cvv2Field;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private TextField monthField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button payButton;

    @FXML
    private TextField yearField;

    @FXML
    void payButtonClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cancelButton.addEventHandler(ActionEvent.ACTION,
            new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                Button b = (Button) e.getSource();
                Stage window = (Stage) b.getScene().getWindow();
                window.close();
            }
        }
        );
        
        monthField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
            if (!newValue.matches("\\d*")) {
                monthField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if(newValue.length() > 2) {
                monthField.setText(oldValue);
            }
        }
        });

        yearField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
            if (!newValue.matches("\\d*")) {
                yearField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if(newValue.length() > 2) {
                yearField.setText(oldValue);
            }
        }
        });

        cvv2Field.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
            if (!newValue.matches("\\d*")) {
                cvv2Field.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });

        passwordField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
            if (!newValue.matches("\\d*")) {
                passwordField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
    
        // cardNumberField.textProperty().addListener(new ChangeListener<String>() {
        // @Override
        // public void changed(ObservableValue<? extends String> observable, String oldValue, 
        //     String newValue) {
        //     if (!newValue.matches("\\d*")) {
        //         cardNumberField.setText(newValue.replaceAll("[^\\d]", ""));
        //     }
        //     if(newValue.length() > 19) {
        //         cardNumberField.setText(oldValue);
        //     }
        //     // String cardNum = newValue.replaceAll(" ", "");
        //     // if(cardNum.length() > 0) {
        //     //     // System.out.println("#######################################"+cardNum);
        //     //     if((cardNum.length())%4 == 0) {
        //     //         cardNumberField.setText(newValue+" ");
        //     //     }
        //     // }
        //     String formattedValue = newValue.replaceAll("\\s", "");
        //     if (formattedValue.length() > 0) {
        //         formattedValue = formattedValue.replaceAll("(\\d{4})", "$1 ");
        //     }
        //     cardNumberField.setText(formattedValue);
        // }
        // });


        // cardNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
        //     if (!newValue.matches("\\d{0,16}")) { // Restrict to 16 digits
        //         cardNumberField.setText(oldValue);
        //     }
        //     // Format the input to match the credit card number format
        //     String formattedValue = newValue.replaceAll("\\s", "");
        //     if (formattedValue.length() > 0) {
        //         formattedValue = formattedValue.replaceAll("(\\d{4})", "$1 ");
        //     }
        //     cardNumberField.setText(formattedValue);
        // });
    }
}
