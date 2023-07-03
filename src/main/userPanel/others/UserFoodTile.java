package main.userPanel.others;

import main.classes.Food;
import main.classes.Order;
import main.userPanel.UserClient;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class UserFoodTile extends UserTile {
    
    private int quantity;
    private Label quantityLabel, priceLabel;
    private Food food;
    private HBox hbox;
    private TextField quantityField;
    private Button plusButton, minusButton, addButton;
    private String restaurantName;

    public UserFoodTile(Food f, int quantity, String restaurantName) {
        super();
        food = f;
        this.quantity = quantity;
        this.restaurantName = restaurantName;
        quantityLabel = new Label("/"+quantity);
        // File file = new File(food.getFoodImagePath());
        // imageView.setImage(new Image(DataBase.imageAbsolutePath+file.getName()));
        imageView.setImage(new Image(food.getFoodImagePath()));
        nameLabel.setText(food.getFoodName());
        priceLabel = new Label("$ "+food.getFoodPrice());
        quantityField = new TextField();
        quantityField.setText("0");
        quantityField.setEditable(false);
        quantityField.setPrefWidth(50);
        quantityField.getStyleClass().add("textField");
        plusButton = new Button("+");
        plusButton.setCursor(Cursor.HAND);
        minusButton = new Button("-");
        minusButton.setCursor(Cursor.HAND);
        addButton = new Button("Add to cart");
        addButton.setDisable(true);
        addButton.setCursor(Cursor.HAND);
        hbox = new HBox(minusButton, plusButton, quantityField, quantityLabel, addButton);
        hbox.setSpacing(12);
        hbox.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(imageLabel, nameLabel, priceLabel, hbox);

        plusButton.setOnAction(
            e -> {
                int oldValue = Integer.parseInt(quantityField.getText());
                if(oldValue < quantity)
                quantityField.setText((oldValue+1)+"");
                int newValue = Integer.parseInt(quantityField.getText());
                if(newValue > 0)
                    addButton.setDisable(false);
            }
        );
        minusButton.setOnAction(
            e -> {
                int oldValue = Integer.parseInt(quantityField.getText());
                if(oldValue > 0)
                    quantityField.setText((oldValue-1)+"");
                int newValue = Integer.parseInt(quantityField.getText());
                if(newValue == 0)
                    addButton.setDisable(true);
            }
        );
        addButton.setOnAction(
            e -> {
                boolean exist = false;
                Order newOrder = new Order(restaurantName, food, Integer.parseInt(quantityField.getText()));
                for(Order o : UserClient.currentUser.getCart().getOrders()) {
                    if(o.equals(newOrder)) {
                        if(o.getQuantity()+newOrder.getQuantity() > quantity)
                            o.setQuantity(quantity);
                        else
                            o.setQuantity(o.getQuantity()+newOrder.getQuantity());
                        exist = true;
                    }
                }
                if(!exist)
                    UserClient.currentUser.getCart().addOrder(newOrder);
            }
        );
    }
}
