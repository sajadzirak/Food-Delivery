package main.userPanel.others;

import main.adminPanel.controllers.AdminMainPageController;
import main.adminPanel.AdminClient;
import main.classes.FxmlLoader;
import main.classes.Restaurant;
import java.io.File;
import java.io.IOException;
import main.server.DataBase;
import main.userPanel.UserClient;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class UserRestaurantTile extends UserTile{

    private Label addressLabel;
    private Restaurant restaurant;

    public UserRestaurantTile(Restaurant r){
        super();
        restaurant = r;
        File file = new File(restaurant.getRestaurantImagePath());
        imageView.setImage(new Image(DataBase.imageAbsolutePath+file.getName()));
        imageLabel.setCursor(Cursor.HAND);
        nameLabel.setText(restaurant.getName());
        typeLabel.setText(restaurant.getrestaurantType().name());
        addressLabel = new Label(restaurant.getRestaurantAddress());
        addressLabel.setStyle(textLabelStyle);
        layout.getChildren().addAll(imageLabel, nameLabel, typeLabel, addressLabel);
        imageLabel.setOnMouseClicked(
                e -> {
                    try {
                        UserClient.toServer.writeObject("Get Restaurant");
                        UserClient.toServer.writeObject(restaurant.getName());
                        BorderPane pane = (BorderPane)super.getParent().getParent().getParent().getParent().getParent().getParent();
                        pane.setCenter(new FxmlLoader().getPage("../userPanel/restaurantFoodsPage.fxml"));
                        // System.out.println(super.getParent().getParent().getParent().getParent().getParent());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
    }
}
