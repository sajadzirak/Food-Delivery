package main.userPanel.others;

import main.classes.Order;
import java.io.File;
import main.server.DataBase;
import main.userPanel.UserClient;
import main.userPanel.controllers.cartPageController;

import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OrderTile extends AnchorPane {
    private Alert alert;
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

        cancelButton.setOnAction(
            e -> {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Do you really want to cancel the order?");
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().equals(ButtonType.OK)) {
                    UserClient.currentUser.getCart().getOrders().remove(order);
                    cartPageController.refresh();
                }
            }
        );
    }

    public Order getOrder() {
        return order;
    }
}
