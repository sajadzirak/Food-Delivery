package userPanel;

import java.io.File;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import server.src.DataBase;
import server.src.Restaurant;

public class RestaurantTile extends UserTile{

    private Label addressLabel;
    private Restaurant restaurant;

    public RestaurantTile(Restaurant r){
        super();
        restaurant = r;
        File file = new File(restaurant.getRestaurantImagePath());
        imageView.setImage(new Image(DataBase.imageAbsolutePath+file.getName()));
        nameLabel.setText(restaurant.getName());
        typeLabel.setText(restaurant.getrestaurantType().name());
        addressLabel = new Label(restaurant.getRestaurantAddress());
        addressLabel.setStyle(textLabelStyle);
        layout.getChildren().addAll(imageLabel, nameLabel, typeLabel, addressLabel);
    }
}
