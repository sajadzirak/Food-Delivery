package userPanel;

import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import server.src.DataBase;
import server.src.Food;

public class FoodTile extends UserTile {
    
    private Food food;
    private HBox hbox;
    private TextField amountField;
    private Button plusButton, minusButton, addButton;
    private String plusButtonStyle = "-fx-background-color: #f02;-fx-text-fill:#fff;";
    private String radius = "-fx-border-radius:20;-fx-background-radius:20;";
    private String shadow = "-fx-effect: dropshadow(three-pass-box, -fx-grey, 10, 0, 0, 0);";

    public FoodTile(Food f) {
        super();
        food = f;
        File file = new File(food.getFoodImagePath());
        imageView.setImage(new Image(DataBase.imageAbsolutePath+file.getName()));
        nameLabel.setText(food.getFoodName());
        typeLabel.setText(food.getFoodType().name());
        amountField = new TextField();
        amountField.setEditable(false);
        amountField.setStyle(radius+"-fx-background-color:#fff;"+shadow);
        amountField.setPrefWidth(80);
        plusButton = new Button("+");
        plusButton.setStyle(plusButtonStyle+shadow);
        minusButton = new Button("-");
        minusButton.setStyle(plusButtonStyle+shadow);
        addButton = new Button("Add to cart");
        addButton.setStyle(plusButtonStyle+radius+shadow);
        hbox = new HBox(minusButton, plusButton, amountField, addButton);
        hbox.setSpacing(10);
        layout.getChildren().addAll(imageLabel, nameLabel, typeLabel, hbox);
    }
}
