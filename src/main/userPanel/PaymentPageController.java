package main.userPanel;

import main.classes.methods;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PaymentPageController implements Initializable{

    private Alert alert;
    private double tax;

    @FXML
    private RadioButton bank1;

    @FXML
    private RadioButton bank2;

    @FXML
    private ToggleGroup bankToggle;

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
    private TextField moneyField;

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
        alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        // if(!methods.checkForEmptyTextField(cardNumberField, cvv2Field, moneyField, monthField, passwordField, yearField)) {
        //     alert.setContentText("Please fill all of the fields!");
        //     alert.showAndWait();
        // }
        // else if(monthField.getText().length() != 2 || yearField.getText().length() != 2) {
        //     alert.setContentText("Invalid month or year!!");
        //     alert.showAndWait();
        // }
        // else if(cardNumberField.getText().length() != 19) {
        //     alert.setContentText("Invalid card number!!");
        //     alert.showAndWait();
        // }
        // else if(bankToggle.getSelectedToggle() == null) {
        //     alert.setContentText("Please select a bank gate!!");
        //     alert.showAndWait();
        // }
        // else
            if(bankToggle.getSelectedToggle() == bank2) {
                tax = 0.05;
            }
            UserClient.currentUser.setBalance(UserClient.currentUser.getBalance()+(Double.parseDouble(moneyField.getText())*(1-tax)));
            Stage window = (Stage) payButton.getScene().getWindow();
            window.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tax = 0;

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

        moneyField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[0-9](\\.[0-9]*)?")) {
                    moneyField.setText(newValue.replaceAll("[^\\d.]", ""));
                    StringBuilder aus = new StringBuilder(newValue);
                    boolean firstPointFound = false;
                    for (int i = 0; i < aus.length(); i++){
                        if(aus.charAt(i) == '.') {
                            if(!firstPointFound)
                                firstPointFound = true;
                            else
                                aus.deleteCharAt(i);
                        }
                    }
                    newValue = aus.toString();
                    moneyField.setText(newValue);
                }
            }
        });
        
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

        cardNumberField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
            if(newValue.length() > 0) {
                if (!newValue.substring(newValue.length()-1).matches("\\d*")) {
                    cardNumberField.setText(oldValue+newValue.substring(newValue.length()-1).replaceAll("[^\\d]", ""));
                }
                else if(newValue.length() > 19) {
                    cardNumberField.setText(oldValue);
                }
                else if(newValue.length()%5 == 1){
                }
                else if(newValue.replaceAll(" ", "").length()%4 == 1) {
                    String cardNo;
                    cardNo = newValue.substring(0, newValue.length()-1)+" "+newValue.substring(newValue.length()-1);
                    cardNumberField.setText(cardNo);
                }
                }
        }
        });
    
        // cardNumberField.textProperty().addListener(new ChangeListener<String>() {
        // @Override
        // public void changed(ObservableValue<? extends String> observable, String oldValue, 
        //     String newValue) {
        //     if (!newValue.matches("\\d*") && newValue.length()%5!=0) {
        //         cardNumberField.setText(newValue.replaceAll("[^\\d]", ""));
        //     }
        //     if(newValue.length() > 19) {
        //         cardNumberField.setText(oldValue);
        //     }
        //     if(newValue.length()%5!=0){
        //         String cardNum = newValue.replaceAll(" ", "");
        //         System.out.println("newValue "+newValue);
        //         System.out.println("cardNum "+cardNum);
        //         if(cardNum.length() > 0) {
        //             // System.out.println("#######################################"+cardNum);
        //             if((cardNum.length())%4 == 0) {
        //                 cardNumberField.setText(newValue+" ");
        //                 // System.out.println(newValue+" .");
        //             }
        //         }
        //     }
            // String formattedValue = newValue.replaceAll("\\s", "");
            // if (formattedValue.length() > 0) {
            //     formattedValue = formattedValue.replaceAll("(\\d{4})", "$1 ");
            // }
            // cardNumberField.setText(formattedValue);
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
