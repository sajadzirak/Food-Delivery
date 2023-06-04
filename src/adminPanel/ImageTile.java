package adminPanel;
import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import server.src.DataBase;
import server.src.Restaurant;
import server.src.Server;

public class ImageTile extends AnchorPane{
    VBox layout;
    ImageView imageView;
    Label imageLabel;
    Label nameLabel, typeLabel;
    Button disableButton;

    public ImageTile(Restaurant r){
        File file = new  File(r.getRestaurantImagePath());
        disableButton = new Button("Disable");
        layout = new VBox();
        imageView = new ImageView(new Image(DataBase.imageAbsolutePath+file.getName()));
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageLabel = new Label();
        imageLabel.setGraphic(imageView);
        nameLabel = new Label(r.getName());
        typeLabel = new Label(r.getrestaurantType().name());
        layout.getChildren().addAll(imageLabel, nameLabel, typeLabel, disableButton);
        this.setDisable(true);
        this.setPrefWidth(300);
        this.setPrefHeight(300);
        this.getChildren().add(layout);
    }
}
