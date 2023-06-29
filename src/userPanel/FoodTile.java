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
import server.src.Order;

public class FoodTile extends UserTile {
    
    private int quantity;
    private Label quantityLabel, priceLabel;
    private Food food;
    private HBox hbox;
    private TextField quantityField;
    private Button plusButton, minusButton, addButton;
    private String restaurantName;
    private String labelStyle = "-fx-font-family: Ubuntu;-fx-text-fill:#04030f;";
    private String plusButtonStyle = "-fx-background-color: #f02;-fx-text-fill:#fff;";
    private String radius = "-fx-border-radius:20;-fx-background-radius:20;";
    private String shadow = "-fx-effect: dropshadow(three-pass-box, -fx-grey, 10, 0, 0, 0);";

    public FoodTile(Food f, int quantity, String restaurantName) {
        super();
        food = f;
        this.quantity = quantity;
        this.restaurantName = restaurantName;
        quantityLabel = new Label("/"+quantity);
        quantityLabel.setStyle(labelStyle);
        File file = new File(food.getFoodImagePath());
        imageView.setImage(new Image(DataBase.imageAbsolutePath+file.getName()));
        nameLabel.setText(food.getFoodName());
        priceLabel = new Label("$ "+food.getFoodPrice());
        priceLabel.setStyle(labelStyle+"-fx-font-weight: 600;-fx-font-size: 16px;");
        quantityField = new TextField();
        quantityField.setText("0");
        quantityField.setEditable(false);
        quantityField.setStyle(radius+"-fx-background-color:#fff;"+shadow);
        quantityField.setPrefWidth(50);
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
        hbox = new HBox(minusButton, plusButton, quantityField, quantityLabel, addButton);
        hbox.setSpacing(12);
        hbox.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(imageLabel, nameLabel, priceLabel, hbox);

        plusButton.setOnAction(
            e -> {
                int oldValue = Integer.parseInt(quantityField.getText());
                if(oldValue == 0)
                    addButton.setDisable(false);
                if(oldValue < quantity)
                    quantityField.setText((oldValue+1)+"");
            }
        );
        minusButton.setOnAction(
            e -> {
                int oldValue = Integer.parseInt(quantityField.getText());
                if(oldValue == 1)
                    addButton.setDisable(true);
                if(oldValue > 0)
                    quantityField.setText((oldValue-1)+"");
            }
        );
        addButton.setOnAction(
            e -> {
                UserClient.currentUser.getCart().addOrder(new Order(restaurantName, food, Integer.parseInt(quantityField.getText())));
                System.out.println(UserClient.currentUser.getCart().getOrders());
            }
        );
    }
}
