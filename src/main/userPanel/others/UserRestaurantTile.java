package main.userPanel.others;

import main.classes.FxmlLoader;
import main.classes.Restaurant;
import main.server.DataBase;
import main.userPanel.UserClient;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class UserRestaurantTile extends UserTile{

    private Label addressLabel;
    private Restaurant restaurant;

    public UserRestaurantTile(Restaurant r){
        super();
        restaurant = r;
        imageView.setImage(new Image(restaurant.getRestaurantImagePath()));
        imageLabel.setCursor(Cursor.HAND);
        nameLabel.setText(restaurant.getName());
        typeLabel.setText(restaurant.getrestaurantType().name());
        addressLabel = new Label(restaurant.getRestaurantAddress());
        layout.getChildren().addAll(imageLabel, nameLabel, typeLabel, addressLabel);
        imageLabel.setOnMouseClicked(
                e -> {
                    try {
                        UserClient.toServer.writeObject("Get Restaurant");
                        UserClient.toServer.writeObject(restaurant.getName());
                        BorderPane pane = (BorderPane)super.getParent().getParent().getParent().getParent().getParent().getParent();
                        pane.setCenter(new FxmlLoader().getPage(DataBase.userFxmlLoaderPath+"restaurantFoodsPage.fxml"));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
    }
}
