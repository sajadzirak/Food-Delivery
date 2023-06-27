package userPanel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

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
    void cancelButtonClicked(ActionEvent event) {

    }

    @FXML
    void payButtonClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        monthField.textProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d*")) {
            monthField.setText(newValue.replaceAll("[^\\d]", ""));
        }
    }
});
    }
    
}
