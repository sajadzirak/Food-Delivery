package userPanel;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import server.src.DataBase;
import server.src.Food;

public class FoodTile extends UserTile {
    
    private int quantity;
    private Label quantityLabel, priceLabel;
    private Food food;
    private HBox hbox;
    private TextField amountField;
    private Button plusButton, minusButton, addButton;
    private String labelStyle = "-fx-font-family: Ubuntu;-fx-text-fill:#04030f;";
    private String plusButtonStyle = "-fx-background-color: #f02;-fx-text-fill:#fff;";
    private String radius = "-fx-border-radius:20;-fx-background-radius:20;";
    private String shadow = "-fx-effect: dropshadow(three-pass-box, -fx-grey, 10, 0, 0, 0);";

    public FoodTile(Food f, int quantity) {
        super();
        food = f;
        this.quantity = quantity;
        quantityLabel = new Label("/"+quantity);
        quantityLabel.setStyle(labelStyle);
        File file = new File(food.getFoodImagePath());
        imageView.setImage(new Image(DataBase.imageAbsolutePath+file.getName()));
        nameLabel.setText(food.getFoodName());
        priceLabel = new Label("$ "+food.getFoodPrice());
        priceLabel.setStyle(labelStyle+"-fx-font-weight: 600;-fx-font-size: 16px;");
        amountField = new TextField();
        amountField.setText("0");
        amountField.setEditable(false);
        amountField.setStyle(radius+"-fx-background-color:#fff;"+shadow);
        amountField.setPrefWidth(50);
        plusButton = new Button("+");
        plusButton.setStyle(plusButtonStyle+shadow);
        plusButton.setCursor(Cursor.HAND);
        minusButton = new Button("-");
        minusButton.setStyle(plusButtonStyle+shadow);
        minusButton.setCursor(Cursor.HAND);
        addButton = new Button("Add to cart");
        addButton.setDisable(true);
        addButton.setStyle(plusButtonStyle+radius+shadow);
        addButton.setCursor(Cursor.HAND);
        hbox = new HBox(minusButton, plusButton, amountField, quantityLabel, addButton);
        hbox.setSpacing(12);
        hbox.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(imageLabel, nameLabel, priceLabel, hbox);

        plusButton.setOnAction(
            e -> {
                int oldValue = Integer.parseInt(amountField.getText());
                if(oldValue == 0)
                    addButton.setDisable(false);
                if(oldValue < quantity)
                    amountField.setText((oldValue+1)+"");
            }
        );
        minusButton.setOnAction(
            e -> {
                int oldValue = Integer.parseInt(amountField.getText());
                if(oldValue == 1)
                    addButton.setDisable(true);
                if(oldValue > 0)
                    amountField.setText((oldValue-1)+"");
            }
        );
    }
}
