package userPanel;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import server.src.DataBase;
import server.src.Order;

public class OrderTile extends AnchorPane {
    private Order order;
    private HBox layout;
    private ImageView imageView;
    private Label foodNameLabel, restaurantNameLabel, costLabel,
    quantityLabel, imageLabel;
    private Button cancelButton;

    public OrderTile(Order order) {
        this.order = order;
        layout = new HBox(15);
        layout.setAlignment(Pos.CENTER);
        // layout.setStyle("-fx-padding:10px;-fx-background-color:#f8e9f2;"+
        // "-fx-margin:10px;-fx-border-radius:20;-fx-background-radius:20;");
        imageView = new ImageView();
        imageView.setFitHeight(150);
        imageView.setFitWidth(200);
        imageLabel = new Label();
        imageLabel.setGraphic(imageView);
        File file = new File(order.getFood().getFoodImagePath());
        imageView.setImage(new Image(DataBase.imageAbsolutePath+file.getName()));
        foodNameLabel = new Label(order.getFood().getFoodName());
        restaurantNameLabel = new Label(order.getRestaurantName());
        quantityLabel = new Label("quantity: "+order.getQuantity());
        costLabel = new Label("$"+order.getCost());
        cancelButton = new Button("cancel");
        cancelButton.setCursor(Cursor.HAND);
        VBox detailsBox = new VBox(15);
        detailsBox.getChildren().addAll(foodNameLabel, restaurantNameLabel,
        quantityLabel, costLabel, cancelButton);
        layout.getChildren().addAll(imageLabel, detailsBox);
        this.getChildren().add(layout);
        this.getStyleClass().add("tile");
        layout.getStyleClass().add("layout");
        imageLabel.getStyleClass().add("imageLabel");
        foodNameLabel.getStyleClass().add("nameLabel");
        this.getStylesheets().addAll("styles/variables.css", "styles/orderTileStyle.css");
    }
}
